package ru.innotech;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.innotech.dao.UserDAO;
import ru.innotech.service.UserService;

@Component
public class TransformApplication {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void run() {
        System.out.println("Список всех пользователей:");
        System.out.println(userService.findAll());

        System.out.println("Пользователь с ID = 1:");
        System.out.println(userService.findId(1));

        System.out.println("Удаление пользователя с ID = 1, список пользователей после:");
        userService.delete(1);
        System.out.println(userService.findAll());

        System.out.println("Создание нового пользователя, список пользователей после:");
        userService.create("vasilev");
        System.out.println(userService.findAll());
    }
}
