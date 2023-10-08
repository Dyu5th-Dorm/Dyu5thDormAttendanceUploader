package org.dyu5thdorm.dyu5thdormattendanceuploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Dyu5thDormAttendanceUploaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Dyu5thDormAttendanceUploaderApplication.class, args);
    }

}
