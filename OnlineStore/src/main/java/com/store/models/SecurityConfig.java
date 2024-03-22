package com.store.models;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.store.services.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{

	/*
	 * public AuthenticationProvider daoAuthenticationProvider() {
	 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	 * provider.setPasswordEncoder(passwordEncoder()); //
	 * provider.setUserDetailsService(this.databaseUserDetailsService); return
	 * provider; }
	 */
	@Autowired
	private DataSource dataSource;

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	UserDetailsService userDetailsService()
	{
		return new CustomUserDetailsService();
	}

	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Bean
	public AuthenticationManagerBuilder confi(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService());
		return auth;
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
	{
		return configuration.getAuthenticationManager();
	}

	/*
	 * @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	 * throws Exception { auth.userDetailsService(userDetailsService());
	 * 
	 * auth.jdbcAuthentication()
	 * .dataSource(dataSource).passwordEncoder(passwordEncoder());
	 * 
	 * }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsManager() {
	 * 
	 * UserDetails user =
	 * org.springframework.security.core.userdetails.User.builder()
	 * .username("susan") .password("{noop}test123") .roles("EMPLOYEE", "MANAGER",
	 * "ADMIN") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user); }
	 */

	@Bean
	static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeHttpRequests((requests) -> requests.requestMatchers("/product", "/loginResults").permitAll()
				.anyRequest().authenticated()).formLogin((form) -> form.defaultSuccessUrl("/orderView").permitAll())
				.logout((logout) -> logout.permitAll());

		return http.build();

		/*
		 * http.authorizeHttpRequests((authorize) ->
		 * authorize.anyRequest().authenticated()).formLogin( form ->
		 * form.loginPage("/login/").loginProcessingUrl("/index.html").defaultSuccessUrl
		 * ("/index.html").permitAll()) .logout(logout ->
		 * logout.logoutRequestMatcher(new
		 * AntPathRequestMatcher("/logout")).permitAll());
		 * 
		 * return http.build();
		 */
	}

	/*
	 * @Bean SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception
	 * { http.csrf(null) .authorizeHttpRequests((authorize) ->
	 * authorize.anyRequest().authenticated() ).formLogin( form -> form
	 * .loginPage("/login") .loginProcessingUrl("/login") .defaultSuccessUrl("/")
	 * .permitAll() ).logout( logout -> logout .logoutRequestMatcher(new
	 * AntPathRequestMatcher("/logout")) .permitAll() ); return http.build(); }
	 */
}
