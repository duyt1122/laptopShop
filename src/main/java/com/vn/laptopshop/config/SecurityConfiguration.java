package com.vn.laptopshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import com.vn.laptopshop.service.CustomUserDetailsService;
import com.vn.laptopshop.service.UserService;

import jakarta.servlet.DispatcherType;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
    private final UserService userService;

    public SecurityConfiguration(UserService userService){
          this.userService = userService;
    }

    @Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	  @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new CustomUserDetailsService(userService);
    }

    @Bean
    public DaoAuthenticationProvider authProvider(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setHideUserNotFoundExceptions(false);
        return authProvider;
    }
    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
        return new CustomSuccessHandler(this.userService);
    }

    @Bean
public SpringSessionRememberMeServices rememberMeServices() {
	SpringSessionRememberMeServices rememberMeServices =
			new SpringSessionRememberMeServices();
	// optionally customize
	rememberMeServices.setAlwaysRemember(true);
	return rememberMeServices;
}


    @Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable()) // CSRF là một cơ chế bảo vệ dành cho form login POST trong ứng dụng web, Bạn
									// PHẢI disable CSRF, vì API không dùng cookies để tự động đăng nhập và chặn mọi
									// request.
				// .cors(Customizer.withDefaults())
				.authorizeHttpRequests(authz -> authz
					 .dispatcherTypeMatchers(DispatcherType.FORWARD,
                                DispatcherType.INCLUDE).permitAll()

					.requestMatchers("/login","/access-deny","/product/**","/client/**","/css/**","/js/**","/images/**","/").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated())
                    .sessionManagement((sessionManagement) -> sessionManagement
			        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
	                .invalidSessionUrl("/logout?expired")
				    .maximumSessions(1)
	                .maxSessionsPreventsLogin(false))

	                .logout(logout->logout.deleteCookies("JSESSIONID").invalidateHttpSession(true))


                    .rememberMe(r -> r.rememberMeServices(rememberMeServices()))
				   .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler())
                        .failureUrl("/login?error")
                        .permitAll())
                    .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-deny"));

				
						
				// .exceptionHandling(
				// exceptions -> exceptions
				// .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint()) //401
				// .accessDeniedHandler(new BearerTokenAccessDeniedHandler())) //403

				// .formLogin(f -> f.disable())
				// .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}
    
}
