package com.teksystems.casestudy.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.teksystems.casestudy.database.dao.UserDAO;
import com.teksystems.casestudy.database.entity.User;
import com.teksystems.casestudy.validation.EmailUniqueImpl;

import java.util.List;

@Slf4j
@Controller
public class IndexController {


    @Autowired
    private UserDAO userDao;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDao.findByEmail(currentPrincipalName);

        if (loggedInUser == null) {
            log.debug("Not logged in");
        } else {
            log.debug("User logged in " + loggedInUser);
        }

        response.setViewName("index");

        return response;
    }


}