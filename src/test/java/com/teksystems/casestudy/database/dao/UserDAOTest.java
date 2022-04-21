package teksystems.casestudy.database.dao;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;
import teksystems.casestudy.database.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired UserDAO userDAO;


    @Test
    @Order(1)
    public void getUserTest(){
    User expected = new User();
    expected.setId(1);


    User actual = userDAO.findById(1);
    Assertions.assertEquals(expected.getId(), actual.getId());
    }
}
