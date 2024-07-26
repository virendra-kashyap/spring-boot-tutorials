package com.authorization.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.authorization.jwt.filter.JwtAuthenticationFilter;
import com.authorization.jwt.service.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class SecuritConfig {

	@Autowired
	private UserServiceImpl userDetailsImp;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Autowired
	private LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		return http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(req -> req.requestMatchers("/auth/**").permitAll()
						.requestMatchers("/admin_only/**").hasAuthority("ADMIN").anyRequest().authenticated())
				.userDetailsService(userDetailsImp)
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(e -> e
						.accessDeniedHandler((request, response, accessDeniedException) -> response.setStatus(403))
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.logout(l -> l.logoutUrl("/logout").addLogoutHandler(logoutHandler).logoutSuccessHandler(
						(request, response, authentication) -> SecurityContextHolder.clearContext()))
				.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

}
