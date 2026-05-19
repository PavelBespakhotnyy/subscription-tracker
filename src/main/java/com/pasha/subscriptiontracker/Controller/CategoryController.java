package com.pasha.subscriptiontracker.Controller;

import com.pasha.subscriptiontracker.Database.DAO.CategoryDAO;
import com.pasha.subscriptiontracker.Model.Category;

import java.util.List;

public class CategoryController {
    private CategoryDAO categoryDAO = new CategoryDAO();

    public boolean add(Category c) {
        return categoryDAO.add(c);
    }

    public List<Category> getByUserId(int userId) {
        return categoryDAO.getByUserId(userId);
    }

    public boolean delete(int id) {
        return categoryDAO.delete(id);
    }

}
