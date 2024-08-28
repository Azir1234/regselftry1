package com.azir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class RegselftryApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegselftryApplication.class, args);
    }

}
