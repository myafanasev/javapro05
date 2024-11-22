package ru.innotech.dao;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innotech.annotation.ColumnName;
import ru.innotech.annotation.TableName;
import ru.innotech.dto.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO extends ParentDAO {

    @SneakyThrows
    public List<User> findAll() { // получение всех пользователей
        Statement statement = connection.createStatement();

        String query = "select " + columns.get("id") + ", " + columns.get("user") + " from " + tableName ;
        ResultSet resultSet = statement.executeQuery(query);

        List<User> userDataList = new ArrayList<>();
        while (resultSet.next()) {
            long id = resultSet.getInt(columns.get("id"));
            String name = resultSet.getString(columns.get("user"));
            userDataList.add(new User(id, name));
        }

        statement.close();
        return userDataList;
    }

    @SneakyThrows
    public User findId(long ident) { // получение пользователя по ID
        Statement statement = connection.createStatement();

        String query = "select " + columns.get("id") + ", " + columns.get("user") + " from " + tableName + " where " + columns.get("id") + " = " + ident;
        ResultSet resultSet = statement.executeQuery(query);

        User userData = new User();
        while (resultSet.next()) {
            userData.setId(resultSet.getInt(columns.get("id")));
            userData.setUser(resultSet.getString(columns.get("user")));
            break;
        }

        statement.close();
        if (userData.getId() == 0) return null;
        return userData;
    }

    @SneakyThrows
    public void delete(User user) { // удаление пользователя
        Statement statement = connection.createStatement();
        String query = "delete from " + tableName + " where " + columns.get("id") + " = " + user.getId();
        statement.execute(query);
        statement.close();
    }

    @SneakyThrows
    public User insert(User user) { // добавление нового пользователя
        long newId = 0;
        String query = "insert into " + tableName + "(" + columns.get("user") + ") values (?)";
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getUser());
        if (statement.executeUpdate() > 0) { // insert выполнился успешно
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) newId = generatedKeys.getLong(1);
        }
        statement.close();
        return findId(newId);

    }
    @SneakyThrows
    public User update(User user) { // изменение пользователя
        Statement statement = connection.createStatement();
        String query = "update " + tableName + " set " + columns.get("user") + " = '" + user.getUser() + "' where " + columns.get("id") + " = " + user.getId();
        statement.execute(query);
        statement.close();
        return user;
    }
}