package com.smoothstack.lms.librarianservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LibrarianServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarianServiceApplication.class, args);
    }

}
