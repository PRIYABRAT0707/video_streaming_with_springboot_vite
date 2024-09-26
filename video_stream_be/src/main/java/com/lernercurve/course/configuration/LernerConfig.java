package com.lernercurve.course.configuration;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
@EnableAsync
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class LernerConfig {

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable()).formLogin(f -> f.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						req -> req.requestMatchers("videos/**", "/api-docs/**", "/swagger-ui/**").permitAll())
				.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("password")).roles("USER").build();

		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("USER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:80/course");
		server.setDescription("Development");

		Contact myContact = new Contact();
		myContact.setName("Priyabrat Panda");
		myContact.setEmail("pandapriyabrat0707@gmail.com");

		Info information = new Info().title("Video Stream").version("1.0")
				.description("This API exposes endpoints to stream videos.").contact(myContact);
		return new OpenAPI().info(information).servers(List.of(server));
	}

}
