package com.teksystems.casestudy.service;

import com.teksystems.casestudy.database.entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    boolean containsId(final List<Movie> userMovies, final int movieId){
        return userMovies.stream().filter(o -> o.getId().equals(movieId)).findFirst().isPresent();
    }


}
