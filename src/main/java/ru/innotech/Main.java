package ru.innotech;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.innotech");
        TransformApplication transformApplication = ctx.getBean(TransformApplication.class);
        Connection connection = ctx.getBean(Connection.class);
        transformApplication.run();
    }
}

