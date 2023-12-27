package kz.education.platform.micro.educonfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EduConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduConfigApplication.class, args);
    }

}
