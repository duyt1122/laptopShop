package com.vn.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable()) // CSRF là một cơ chế bảo vệ dành cho form login POST trong ứng dụng web, Bạn
									// PHẢI disable CSRF, vì API không dùng cookies để tự động đăng nhập và chặn mọi
									// request.
				// .cors(Customizer.withDefaults())
				.authorizeHttpRequests(authz -> authz.anyRequest().permitAll());
						
				// .exceptionHandling(
				// exceptions -> exceptions
				// .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) //401
				// .accessDeniedHandler(new BearerTokenAccessDeniedHandler())) //403

				// .formLogin(f -> f.disable())
				// .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
    
}
