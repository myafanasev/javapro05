package ru.innotech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innotech.dao.UserProductDAO;
import ru.innotech.dto.UserProduct;

import java.util.List;

@Component
public class UserProductService {
    UserProductDAO userProductDAO;

    @Autowired
    public void setUserProductDAO(UserProductDAO userProductDAO) {
        this.userProductDAO = userProductDAO;
    }

    public List<UserProduct> findAll() { return userProductDAO.findAll(0);} //  все продукты всех клиентов

    public List<UserProduct> findAllProductClient(long id) { return userProductDAO.findAll(id);} //  все продукты клиента

    public UserProduct findId(long idProduct){ return userProductDAO.findId(idProduct);} // получить продукт по ID
}
