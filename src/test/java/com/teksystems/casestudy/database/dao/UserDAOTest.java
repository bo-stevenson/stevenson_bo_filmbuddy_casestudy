package com.teksystems.casestudy.database.dao;


import com.teksystems.casestudy.database.entity.Follower;
import com.teksystems.casestudy.database.entity.UserRole;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.teksystems.casestudy.database.entity.User;

import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    @Autowired
    UserRoleDAO userRoleDAO;

    @Autowired
    FollowerDAO followerDAO;

    @Test
    @Order(1)
    public void getUserTest() {
        User expected = new User();
        expected.setId(1);


        User actual = userDAO.findById(1);
        Assertions.assertEquals(expected.getId(), actual.getId());
    }

    @Test
    @Order(2)
    public void createUserTest() {
        User expected = new User();

        expected.setId(1);
        expected.setFirstName("goofy");
        expected.setLastName("guy");
        expected.setEmail("fellasmella@gmail.com");
        expected.setPassword("password");
        expected.setCreateDate(new Date());

        userDAO.save(expected);
        Assertions.assertTrue(expected.getId() > 0);
    }

    @Test
    public void updateUserTest() {

        User actual = userDAO.findById(1);
        actual.setFirstName("updated");
        Assertions.assertEquals("updated", actual.getFirstName());
    }

}

