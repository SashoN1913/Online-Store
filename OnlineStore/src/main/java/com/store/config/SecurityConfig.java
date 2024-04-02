package com.store.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
//	@Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//            .authorizeHttpRequests(auth -> {
//            	auth.requestMatchers("/**").permitAll();
//            	//auth.anyRequest().authenticated();
//            })
//            .build();
//    }
	
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/signup",
			"/signupSave",
			"/login",
			"/store",
			"/admin/**",
			"/article-detail"				
	};
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		http
			.authorizeHttpRequests((requests) -> requests
				//.requestMatchers("/", "/home").permitAll()
				.requestMatchers(PUBLIC_MATCHERS).permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
			)
			.logout((logout) -> logout.permitAll());

		return http.build();
	}
	
	@Bean 
	public PasswordEncoder passwordEncoder()
	{ 
	    return new BCryptPasswordEncoder(); 
	}
	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService()
//	{
//        UserDetails user1 = User.withUsername("user1")
//            .password(passwordEncoder().encode("user1Pass"))
//            .roles("USER")
//            .build();
//        UserDetails user2 = User.withUsername("user2")
//            .password(passwordEncoder().encode("user2Pass"))
//            .roles("USER")
//            .build();
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder().encode("adminPass"))
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }
	
//	@Bean
//	UserDetailsManager users(DataSource dataSource) {
//		UserDetails u = User.builder()
//			.username("user")
//			.password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
//			.roles("USER")
//			.build();
//		UserDetails admin = User.builder()
//			.username("admin")
//			.password(passwordEncoder().encode("adminPass"))
//			.roles("USER", "ADMIN")
//			.build();
//		JdbcUserDetailsManager user = new JdbcUserDetailsManager(dataSource);
//		user.createUser(u);
//		user.createUser(admin);
//		return user;
//	}
	
	
}

