package kz.education.platform.micro.eduentity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EduEntityApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduEntityApplication.class, args);
    }

}
