package edu.uc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("edu.uc.dao")
public class SbmUcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbmUcApplication.class, args);
    }

}
