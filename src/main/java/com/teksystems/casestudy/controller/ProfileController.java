package com.teksystems.casestudy.controller;

import com.teksystems.casestudy.database.entity.Follower;
import com.teksystems.casestudy.database.entity.Movie;
import com.teksystems.casestudy.database.entity.User;
import com.teksystems.casestudy.database.entity.UserMovies;
import com.teksystems.casestudy.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private SecurityService securityService = new SecurityService();

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ModelAndView showProfile() throws Exception {
        ModelAndView response = new ModelAndView();

        User loggedInUser = securityService.getLoggedInUser();
        String firstName = loggedInUser.getFirstName();
        String lastName = loggedInUser.getLastName();

        //Get User Movies
        loggedInUser.getUserMovies();
        log.info(loggedInUser.getUserMovies().toString());
        List<Movie> userMovies = new ArrayList<>();
        for(UserMovies m : loggedInUser.getUserMovies()){
            userMovies.add(m.getMovie());
        }
        log.info(userMovies.toString());


        //Get Followers
        List<User> followers = new ArrayList<>();
        for(Follower f : loggedInUser.getFollowing()){
            followers.add(f.getFollower());
        }
        log.info(followers.toString());






        response.addObject("userMovies", userMovies);
        response.addObject("firstName", firstName);
        response.addObject("lastName", lastName);
        response.addObject("followers",followers);

        response.setViewName("user/profile");
        return response;
    }




}
