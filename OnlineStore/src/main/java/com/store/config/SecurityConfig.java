package com.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;
    
	private static final String[] PUBLIC_MATCHERS = {
			"/css/**",
			"/js/**",
			"/image/**",
			"/",
			"/index",
			"/login",
			"/signup/**",
			"/store",
			"/productDetails",
			"/account/**"
	};
    
    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		//noinspection removal
		http
			.authorizeHttpRequests((requests) -> requests
					.shouldFilterAllDispatcherTypes(false)
					.requestMatchers(PUBLIC_MATCHERS).permitAll()
					.requestMatchers("/admin/**").permitAll()//hasRole("ADMIN")
			)
			.formLogin((form) -> form
				.failureUrl("/login?error")
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/account")
				.permitAll()
			)
			.logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());

		return http.build();
	}
    
    @Bean
    public static PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
