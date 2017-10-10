package com.config;

import com.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import sun.security.provider.MD5;

import javax.xml.bind.DatatypeConverter;


@SpringBootApplication
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = {"com.service","com.controller","com.config"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }


}
