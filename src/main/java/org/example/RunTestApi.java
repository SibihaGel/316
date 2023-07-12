package org.example;

import org.example.config.myConfig;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class RunTestApi {
    public static void main(String[] args) {

            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(myConfig.class);
            UserService userService = context.getBean("userService", UserService.class);

            User newUser = new User(3L, "James", "Brown", (byte) 33);
            User editUser = new User(3L, "Thomas", "Shelby", (byte) 33);

            userService.getAllUsers();

            System.out.println(userService.newUser(newUser).getBody() + userService.editUser(editUser).getBody() + userService.deleteUser().getBody());

        }

    }

