package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.Roles;
import com.example.demo.repositories.UserRepository;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@OpenAPIDefinition
public class Application {


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Bean
	CommandLineRunner commandLineRunner(UserRepository userRepository){
		return args -> {
			UserEntityAndUserDTO userEntityAndUserDTO = new UserEntityAndUserDTO();
			UserDTO userAdmin = new UserDTO("samuel", passwordEncoder.encode("sam12"),"aparici@gmail.com",Roles.ADMIN);
			UserDTO userDTO = new UserDTO("diana", passwordEncoder.encode("dian12"),"dian@gmail.com");
			userRepository.save(userEntityAndUserDTO.userDTOToUserEntity(userDTO));
			userRepository.save(userEntityAndUserDTO.userDTOToUserEntity(userAdmin));
		};
	}
}
