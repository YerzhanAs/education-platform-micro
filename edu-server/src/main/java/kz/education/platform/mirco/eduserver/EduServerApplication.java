package kz.education.platform.mirco.eduserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EduServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduServerApplication.class, args);
	}

}
