package com.teksystems.casestudy.database.dao;

import com.teksystems.casestudy.database.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDAO extends JpaRepository<Movie, Long> {

    public Movie findByTitle(@Param("title") String title);

    public Movie findById(@Param("id") Integer id);

    public List<Movie> findByTitleIgnoreCaseContaining(@Param("title") String title);

}
