package theshy.serviceregistry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
// make Spring Boot project as a Eureka Server
@EnableEurekaServer
public class ServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceRegistryApplication.class, args);
	}

}
