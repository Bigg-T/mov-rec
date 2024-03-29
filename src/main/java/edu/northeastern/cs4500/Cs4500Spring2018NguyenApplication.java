package edu.northeastern.cs4500;

//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class Cs4500Spring2018NguyenApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(Cs4500Spring2018NguyenApplication.class, args);
//	}
//}

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000/*", maxAge = 3600)
@SpringBootApplication
public class Cs4500Spring2018NguyenApplication extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder
  configure(SpringApplicationBuilder application) {
    return application.sources(Cs4500Spring2018NguyenApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(Cs4500Spring2018NguyenApplication.class, args);
  }
}