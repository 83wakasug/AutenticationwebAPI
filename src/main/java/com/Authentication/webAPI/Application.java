package com.Authentication.webAPI;

import com.Authentication.webAPI.security.entity.Role;
import com.Authentication.webAPI.security.entity.Roles;
import com.Authentication.webAPI.security.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository){
		return args ->{

			if(roleRepository.findByAuthority(Role.ROLE_ADMIN).isPresent()) return;
			saveRoles(roleRepository,Role.ROLE_ADMIN);
			saveRoles(roleRepository,Role.ROLE_USER);
		};
	}

	public void saveRoles(RoleRepository repository, Role role){
		Roles roles = new Roles();
		try {
			roles.setAuthority(role);
		}catch (Exception e){

		}
		repository.save(roles);
	}

}
