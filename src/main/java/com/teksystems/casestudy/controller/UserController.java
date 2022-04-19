package com.teksystems.casestudy.controller;

import com.teksystems.casestudy.database.dao.UserRoleDAO;
import com.teksystems.casestudy.database.entity.UserRole;
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
import com.teksystems.casestudy.database.entity.User;
import com.teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
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
    private PasswordEncoder passwordEncoder;

    /**
     * this is the controller method for the entry point of the
     * user registration page.   It does not do anything really
     * other than provide a route to the register.jsp page
     * <p>
     * This method is the entry point for the create user - it sets up the empty form
     */
    @RequestMapping(value = "/user/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");

        // all these 2 lines of code are doing is seeding the model with an
        // empty form bean so that the JSP page substitutions will not error out
        // in this case spring is being nice enough not to throw errors but these
        // 2 lines are safety.
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    /**
     * when the user submits the form it will call into this method
     * 1) the action on the form itself must match the value here in the request mapping
     * 2) method on the form must match the method here
     * otherwise spring MVC will not be able to respond to the request
     * <p>
     * In this case the @PostMapping and @RequestMapping are the same with the @PostMapping
     * being a shorthand.   This works the same for @GetMapping
     * <p>
     * This method now becomes a create and an edit based on if the id is populated in
     * the RegisterFormBean.
     */
    //@PostMapping( "/user/registerSubmit")
    @RequestMapping(value = "/user/registerSubmit", method = { RequestMethod.POST, RequestMethod.GET})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info( ((FieldError)error).getField() + " " +  error.getDefaultMessage());
            }

            // add the form back to the model so we can fill up the input fields
            // so the user can correct the input and does not have type it all again
            response.addObject("form", form);

            // add the error list to the model
            response.addObject("bindingResult", bindingResult);

            // because there is 1 or more error we do not want to process the logic below
            // that will create a new user in the database.   We want to show the register.jsp
            response.setViewName("user/register");
            return response;
        }

        // we first assume that we are going to try to load the user from
        // the database using the incoming id on the form
        User user = userDao.findById(form.getId());

        // if the user is not null the know it is an edit
        if (user == null) {
            // now, if the user from the database is null then it means we did not
            // find this user.   Therefore, it is a create.
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

        // here instaed of showing a view, we want to redirect to the edit page
        // the edit page will then be responsible for loading the user from the
        // database and dynamically creating the page
        // when you use redirect: as part of the view name it triggers spring to tell the
        // browser to do a redirect to the URL after the :    The big piece here to
        // recognize that redirect: uses an actual URL rather than a view name path.
        response.setViewName("redirect:/user/edit/" + user.getId());

        return response;
    }

    /**
     * This method is for editing a user. There is a path parameter being used
     * to pass the userid for the user that is to be editied.
     * <p>
     * In this case the @GetMapping is equivlant to the @RequestMapping
     */
    //@RequestMapping(value = "/user/edit/{userId}", method = RequestMethod.GET)
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

        // in this case we are adding the RegisterFormBean to the model
        response.addObject("form", form);

        return response;
    }

    // create a form on the user search page that action submits to this route using a get method
    // make an input box for the user to enter a search term for first name
    // add a @RequestParam to take in a search value from the input box - use required = false in the annotation
    // use the search value in the query
    // add the search value to the model and make it display in the input box when the page reloads
    // add error checking to make sure that the incoming search value is not null and is not empty.
    // find apache string utils on maven central and add it to your pom file - very high recommendation
    // research the StringUtils.isEmpty function and use for error checking
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/user/search", method= RequestMethod.GET )
    public ModelAndView search(@RequestParam(value = "firstName", required = false) String firstName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        List<User> users = new ArrayList<>();

        // very basic example of error checking
        if (!StringUtils.isEmpty(firstName)) {
            users = userDao.findByFirstNameIgnoreCaseContaining(firstName);
        }

        // this line puts the list of users that we just queried into the model
        // the model is a map ( key value store )
        // any object of any kind can go into the model using this key value
        // in this case it is a list of Users
        response.addObject("usersModelKey", users);
        response.addObject("firstName", firstName);

        return response;
    }
}