//package edu.northeastern.cs4500;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
///**
// * Created by t on 4/4/18.
// */
//@SpringBootApplication
//public class Log4jDemo implements ApplicationRunner{
//
//  // Create an Logger object.
//  private static final Logger logger = LogManager.getLogger(Log4jDemo.class);
//
//  public static void main(String[] args) {
//    SpringApplication.run(Log4jDemo.class, args);
//  }
//
//  @Override
//  public void run(ApplicationArguments applicationArguments) throws Exception {
//
//    // here are an example of logging methods
//    logger.debug("Debugging log");
//    logger.info("Info log");
//    logger.warn("Hey, This is a warning!");
//    logger.error("Oops! We have an Error. OK");
//    logger.fatal("Damn! Fatal error. Please fix me.");
//  }
//}