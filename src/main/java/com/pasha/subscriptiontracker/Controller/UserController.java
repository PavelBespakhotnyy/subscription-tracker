package com.pasha.subscriptiontracker.Controller;

import com.pasha.subscriptiontracker.Database.DAO.UserDAO;
import com.pasha.subscriptiontracker.Model.User;

public class UserController {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.login(username, password);
    }

    public boolean register(User user) {
        return userDAO.register(user);
    }
}
