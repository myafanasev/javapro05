package ru.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innotech.dao.UserDAO;
import ru.innotech.dto.User;

import java.util.List;

@Component
public class UserService {
    UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findId(long ident) {
        return userDAO.findId(ident);
    }

    public void delete(long ident) {
        userDAO.delete(ident);
    }

    public void create(String name) {
        userDAO.create(name);
    }
}
