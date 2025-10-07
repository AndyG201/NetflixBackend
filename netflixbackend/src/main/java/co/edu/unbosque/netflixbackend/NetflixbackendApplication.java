package co.edu.unbosque.netflixbackend;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NetflixbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetflixbackendApplication.class, args);
	}
	
	@Bean
	ModelMapper getModelMapper () {
		return new ModelMapper();
	}

}
