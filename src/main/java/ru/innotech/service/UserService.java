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

    public List<User> findAll() { return userDAO.findAll(); }

    public User findId(long ident) { return userDAO.findId(ident); }

    public void delete(User user) {userDAO.delete(user);}

    public User save(User user) {
        if (userDAO.findId(user.getId()) != null)  // если это update
            return userDAO.update(user);
        else // значит iinsert
            return userDAO.insert(user);
    }
}
