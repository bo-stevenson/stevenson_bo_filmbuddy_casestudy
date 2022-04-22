package com.teksystems.casestudy.database.dao;

import com.teksystems.casestudy.database.entity.Follower;
import com.teksystems.casestudy.database.entity.Movie;
import com.teksystems.casestudy.database.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerDAO extends JpaRepository<Follower, Long> {

    List<Follower> findByUserFollowed(@Param("userFollowed") Integer userFollowed);
    List<Follower> findByFollower(@Param("follower") Integer follower);
}
