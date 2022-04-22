package com.teksystems.casestudy.controller;

import com.teksystems.casestudy.database.dao.FollowerDAO;
import com.teksystems.casestudy.database.dao.UserRoleDAO;
import com.teksystems.casestudy.database.entity.*;
import com.teksystems.casestudy.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.teksystems.casestudy.database.dao.UserDAO;
import com.teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private UserRoleDAO userRoleDao;

    @Autowired
    private FollowerDAO followerDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SecurityService securityService = new SecurityService();



    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");


        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }


    @RequestMapping(value = "/user/registerSubmit", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info(((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);

            // add the error list to the model
            response.addObject("bindingResult", bindingResult);

            // because there is 1 or more error we do not want to process the logic below
            // that will create a new user in the database.   We want to show the register.jsp
            response.setViewName("user/register");
            return response;
        }


        User user = userDao.findById(form.getId());


        if (user == null) {
            user = new User();
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setCreateDate(new Date());

        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);

        userDao.save(user);

        // create and save the user role object
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setUserRole("USER");

        userRoleDao.save(userRole);

        log.info(form.toString());

        response.setViewName("redirect:/login/login");

        return response;
    }

    //@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/user/edit/{userId}")
    //public ModelAndView editUser(@RequestParam("userId") Integer userId) throws Exception {
    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        User user = userDao.findById(userId);

        RegisterFormBean form = new RegisterFormBean();



        form.setId(user.getId());
        form.setEmail(user.getEmail());
        form.setFirstName(user.getFirstName());
        form.setLastName(user.getLastName());
        form.setPassword(user.getPassword());
        form.setConfirmPassword(user.getPassword());


        response.addObject("form", form);

        return response;
    }


    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    public ModelAndView search(@RequestParam(value = "firstName", required = false) String firstName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/userSearch");

        List<User> users = new ArrayList<>();


        if (!StringUtils.isEmpty(firstName)) {
            users = userDao.findByFirstNameIgnoreCaseContaining(firstName);
        }


        response.addObject("usersModelKey", users);
        response.addObject("firstName", firstName);

        return response;
    }

    @GetMapping("/user/follow/{userId}")
    public ModelAndView follow(@PathVariable("userId") Integer userId){
        ModelAndView response = new ModelAndView();

        boolean found = false;
        String errorMessage;

        User user = userDao.findById(userId);
        User loggedInUser = securityService.getLoggedInUser();
        Follower follower = new Follower();

        for (Follower f : loggedInUser.getFollowing()){
            if (f.getUserFollowed().getId() == userId){
                found = true;
                errorMessage = "You are already following that user";
                response.addObject("errorMessage",errorMessage);
                response.setViewName("user/userSearch");
                return response;
            }

        }

        follower.setUserFollowed(user);
        follower.setFollower(loggedInUser);
        followerDao.save(follower);
        String successMessage = "User has been followed!";

        response.addObject("successMessage", successMessage);

        response.setViewName("user/userSearch");
        return response;
    }
}