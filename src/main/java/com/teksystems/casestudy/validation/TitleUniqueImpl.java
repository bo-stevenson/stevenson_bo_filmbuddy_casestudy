package com.teksystems.casestudy.validation;


import com.teksystems.casestudy.database.dao.MovieDAO;
import com.teksystems.casestudy.database.entity.Movie;
import com.teksystems.casestudy.database.entity.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class TitleUniqueImpl implements ConstraintValidator<TitleUnique, String> {

    public static final Logger LOG = LoggerFactory.getLogger(TitleUniqueImpl.class);

    @Autowired
    private MovieDAO movieDao;

    @Override
    public void initialize(TitleUnique constraintAnnotation) {

    }
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( StringUtils.isEmpty(value) ) {
            return true;
        }
        Movie movie = movieDao.findByTitle(value);

        if(movie == null){
            return ( movie == null );
        }
        if (movie.getTitle().equals(value)){
            return true;
        } else{

        return ( movie == null );
        }
    }


}
