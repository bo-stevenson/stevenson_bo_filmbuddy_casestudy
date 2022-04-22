package com.teksystems.casestudy.database.dao;

import com.teksystems.casestudy.database.entity.Movie;
import com.teksystems.casestudy.database.entity.UserMovies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMoviesDAO extends JpaRepository<UserMovies, Long> {

}
