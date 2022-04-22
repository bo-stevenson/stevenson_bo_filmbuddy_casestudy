package com.teksystems.casestudy.database.dao;


import com.teksystems.casestudy.database.entity.Movie;

import com.teksystems.casestudy.database.entity.UserMovies;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MovieDAOTest {

    @Autowired
    MovieDAO movieDAO;
    @Autowired
    UserMoviesDAO userMoviesDao;

    @Test
    @Order(1)
    public void getMovieTest(){
        Movie expected = new Movie();
        expected.setId(1);


        Movie actual = movieDAO.findById(1);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    @Order(2)
    public void createMovieTest(){
        Movie expected = new Movie();

        expected.setId(1);
        expected.setTitle("Fake movie");
        expected.setDescription("really fake");
        expected.setGenre("Fake");
        expected.setReleaseDate(LocalDate.parse("2022-04-22"));

        movieDAO.save(expected);
        Assertions.assertTrue(expected.getId()>0);

    }

    @Test
    public void updateMovieTest(){

        Movie actual = movieDAO.findById(1);
        actual.setDescription("updated");
        Assertions.assertEquals("updated", actual.getDescription());
    }

    @Test
    public void deleteMovieTest(){

        Movie actual = movieDAO.findById(1);
        List<UserMovies> userMovies = userMoviesDao.findByMovieId(1);

        for (UserMovies um : userMovies){
            userMoviesDao.delete(um);
        }
        movieDAO.delete(actual);
        Assertions.assertNull(movieDAO.findById(1));
    }

}
