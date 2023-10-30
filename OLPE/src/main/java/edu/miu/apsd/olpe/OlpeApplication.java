package edu.miu.apsd.olpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OlpeApplication {

    public static void main(String[] args) {
        System.out.println("Hello OLPE app, APSD Project");
        SpringApplication.run(OlpeApplication.class, args);
    }

}
