package com.back.catchmate;

import com.back.catchmate.global.error.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(GlobalExceptionHandler.class)
@SpringBootApplication
public class CatchmateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CatchmateApplication.class, args);
    }
}
