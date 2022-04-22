package com.teksystems.casestudy.controller;

import com.teksystems.casestudy.database.dao.MovieDAO;
import com.teksystems.casestudy.database.dao.UserDAO;
import com.teksystems.casestudy.database.dao.UserMoviesDAO;
import com.teksystems.casestudy.database.entity.Movie;
import com.teksystems.casestudy.database.entity.User;
import com.teksystems.casestudy.database.entity.UserMovies;
import com.teksystems.casestudy.formbean.CreateMovieFormBean;
import com.teksystems.casestudy.formbean.RegisterFormBean;
import com.teksystems.casestudy.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class MovieController {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserMoviesDAO userMoviesDAO;

    @Autowired
    private SecurityService securityService = new SecurityService();

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/movie/search", method = RequestMethod.GET)
    public ModelAndView searchMovies(@RequestParam(value = "title", required = false) String title) {

        ModelAndView response = new ModelAndView();

        response.setViewName("movie/movieSearch");

        List<Movie> movies = new ArrayList<>();

        if (!StringUtils.isEmpty(title)) {
            movies = movieDAO.findByTitleIgnoreCaseContaining(title);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

        response.addObject("moviesModelKey", movies);

        response.addObject("title", title);

        return response;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/movie/favorite/{movieId}")
    public ModelAndView addMovieToFavorite(@PathVariable("movieId") Integer movieId) throws Exception {
        ModelAndView response = new ModelAndView();

        boolean found = false;
        String errorMessage;

        Movie movie = movieDAO.findById(movieId);
        User loggedInUser = securityService.getLoggedInUser();
        UserMovies userMovies = new UserMovies();

        for (UserMovies m : loggedInUser.getUserMovies()){
            if (m.getMovie().getId() == movieId){
                found = true;
                errorMessage = "Movie already exists within favorites";
                response.addObject("errorMessage",errorMessage);
                response.setViewName("movie/movieSearch");
                return response;
            }

        }
        userMovies.setMovie(movie);
        userMovies.setUser(loggedInUser);
        userMoviesDAO.save(userMovies);
        String successMessage = "Movie has been added successfully!";

        response.addObject("successMessage", successMessage);
        response.setViewName("movie/movieSearch");
        return response;
    }

    @GetMapping("/movie/add")
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("movie/createMovie");


        CreateMovieFormBean form = new CreateMovieFormBean();
        response.addObject("form", form);

        return response;
    }

    @PostMapping("/movie/movieSubmit")
    public ModelAndView registerSubmit(@Valid CreateMovieFormBean form, BindingResult bindingResult) throws Exception {
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
            // that will create a new user in the database. We want to show the register.jsp
            response.setViewName("movie/add");
            return response;
        }

        // we first assume that we are going to try to load the user from
        // the database using the incoming id on the form
        Movie movie = movieDAO.findById(form.getId());
        movieDAO.delete(movie);
        // if the user is not null the know it is an edit
        if (movie == null) {
            // now, if the user from the database is null then it means we did not
            // find this user.   Therefore, it is a create.
            movie = new Movie();
        }


        movie.setTitle(form.getTitle());
        movie.setDescription(form.getDescription());
        movie.setGenre(form.getGenre());
        movie.setReleaseDate(form.getReleaseDate());

        movieDAO.save(movie);
        log.info(form.toString());
        String successMessage = "Movie has been added successfully!";

        response.addObject("successMessage", successMessage);
        response.setViewName("redirect:/movie/search/");

        return response;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/movie/edit/{movieId}")
    public ModelAndView editMovie(@PathVariable("movieId") Integer movieId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("movie/createMovie");


        Movie movie = movieDAO.findById(movieId);
        log.info(movie.toString());
        CreateMovieFormBean form = new CreateMovieFormBean();

        form.setId(movie.getId());
        form.setTitle(movie.getTitle());
        form.setDescription(movie.getDescription());
        form.setGenre(movie.getGenre());
        form.setReleaseDate(movie.getReleaseDate());

        log.info(form.toString());
        // in this case we are adding the RegisterFormBean to the model
        response.addObject("form", form);

        return response;
    }


    @GetMapping("/movie/delete/{movieId}")
    public ModelAndView deleteMovieFromFavorite(@PathVariable("movieId") Integer movieId) throws Exception {
        ModelAndView response = new ModelAndView();


        User loggedInUser = securityService.getLoggedInUser();
        loggedInUser.getUserMovies();

        for(UserMovies m : loggedInUser.getUserMovies()){
            if(m.getMovie().getId() == movieId){
                userMoviesDAO.delete(m);
            }
        }

        response.setViewName("redirect:/user/profile");
        return response;
    }

}
