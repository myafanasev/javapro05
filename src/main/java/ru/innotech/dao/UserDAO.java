package ru.innotech.dao;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.innotech.annotation.ColumnName;
import ru.innotech.annotation.TableName;
import ru.innotech.dto.User;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO {
    Map<String, String> columns = new HashMap<>();  // список полей таблицы
    String tableName;   // наименование таблицы
    Connection connection;

    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public UserDAO() {
        Class<?> cls = User.class;

        // получим имя таблицы
        if (cls.isAnnotationPresent(TableName.class)) // если есть аннотация с именем таблицы
            tableName = cls.getAnnotation(TableName.class).value();
        else tableName = cls.getSimpleName();

        // получим информацию о колонках таблицы
        for (Field f : cls.getDeclaredFields()) {
            String nameColumn;
            if (f.isAnnotationPresent(ColumnName.class)) // если есть аннотация с именем столбца таблицы
                nameColumn = f.getAnnotation(ColumnName.class).value();
            else nameColumn = f.getName();
            columns.put(f.getName(), nameColumn);
        }
    }

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
        return userData;
    }
    @SneakyThrows
    public void delete(long ident) { // удаление пользователя по ID
        Statement statement = connection.createStatement();
        String query = "delete from " + tableName + " where " + columns.get("id") + " = " + ident;
        statement.execute(query);
        statement.close();
    }

    @SneakyThrows
    public void create(String name) { // создание пользователя
        Statement statement = connection.createStatement();
        String query = "insert into " + tableName + "(" + columns.get("user") + ")values('" + name + "')";
        statement.execute(query);
        statement.close();
    }
}