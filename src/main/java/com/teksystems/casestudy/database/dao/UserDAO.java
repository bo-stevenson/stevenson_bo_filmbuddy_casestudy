package com.teksystems.casestudy.database.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.teksystems.casestudy.database.entity.User;
import com.teksystems.casestudy.database.entity.UserRole;

import java.util.List;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {

    public User findById(@Param("id") Integer id);


    @Query(value = "select * from users where email = :email", nativeQuery = true)

    public User findByEmail(@Param("email") String email);

    // select * from user where upper(first_name) like '%:firstName%';
    public List<User> findByFirstNameIgnoreCaseContaining(@Param("firstName") String firstName);

    public List<User> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

}