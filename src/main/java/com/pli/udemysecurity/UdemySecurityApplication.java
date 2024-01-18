package com.pli.udemysecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pli.udemysecurity.controller")
public class UdemySecurityApplication {

  public static void main(String[] args) {
    SpringApplication.run(UdemySecurityApplication.class, args);
  }
}
