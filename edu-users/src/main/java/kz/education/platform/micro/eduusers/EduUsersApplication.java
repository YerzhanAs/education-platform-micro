package kz.education.platform.micro.eduusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"kz.education.platform.micro"})
@EnableJpaRepositories(basePackages = {"kz.education.platform.micro.eduusers"})
@RefreshScope
public class EduUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduUsersApplication.class, args);
    }

}
