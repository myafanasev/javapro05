package ru.innotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.Connection;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Main.class);
        //System.out.println(ctx.getBean(DispatcherServlet));
        //        ApplicationContext ctx = new AnnotationConfigApplicationContext("ru.innotech");
        TransformApplication transformApplication = ctx.getBean(TransformApplication.class);
//        Connection connection = ctx.getBean(Connection.class);
      transformApplication.run();
    }
}

