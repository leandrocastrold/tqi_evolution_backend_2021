package com.leandro.tqibank;

import com.leandro.tqibank.pages.HomePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class TqibankApplication implements CommandLineRunner {

    @Autowired
    HomePage homePage;

    public static void main(String[] args) {
        SpringApplication.run(TqibankApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        homePage.showHomePage();
    }
}
