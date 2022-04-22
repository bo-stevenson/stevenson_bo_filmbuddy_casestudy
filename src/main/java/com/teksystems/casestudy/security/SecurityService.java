package com.teksystems.casestudy.security;

import com.teksystems.casestudy.database.dao.UserDAO;
import com.teksystems.casestudy.database.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {


    @Autowired
    private UserDAO userDAO;

    public User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User loggedInUser = userDAO.findByEmail(currentPrincipalName);

        return loggedInUser;
    }
}
