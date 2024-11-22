package ru.innotech;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import ru.innotech.dto.User;
import ru.innotech.service.UserService;

import java.util.List;

public class TestUser {
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine").withInitScript("scheme.sql");

    @BeforeAll
    static void beforeAll() {postgres.start();}

    @AfterAll
    static void afterAll() {postgres.stop();}

    @Test
    @DisplayName("Проверка добавления пользователя")
    void testAddUser() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.innotech");
        UserService userService = ctx.getBean(UserService.class);
        List<User> listBefore = List.of(new User(1,"bot"));
        userService.create("bot");
        List<User> listAfter = userService.findAll();

        Assertions.assertEquals(listBefore, listAfter);
    }
}
