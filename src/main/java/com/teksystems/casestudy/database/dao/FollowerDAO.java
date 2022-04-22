package com.teksystems.casestudy.database.dao;

import com.teksystems.casestudy.database.entity.Follower;
import com.teksystems.casestudy.database.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerDAO extends JpaRepository<Follower, Long> {
}
