package br.com.martines_dev.MyFandon.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers( HttpMethod.GET ,  "/api/**").permitAll()
				.antMatchers( HttpMethod.POST ,"/api/usuario").permitAll()
				.antMatchers( HttpMethod.GET ,"/api/usuario").permitAll()
			.anyRequest()
			.authenticated()
		.and()
			.httpBasic();
		
			
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.authenticationProvider( daoAuthenticationProvider() );
	}

	
	 @Bean
	 protected DaoAuthenticationProvider daoAuthenticationProvider(){
		 DaoAuthenticationProvider provider =  new DaoAuthenticationProvider(  );
		 
		 provider.setPasswordEncoder(passwordEncoder());
		 provider.setUserDetailsService(userDetailsService);
		 return provider;
	 }
		
	
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		auth.inMemoryAuthentication()
//				.withUser("lucas").password("123456").roles("ADMIN")
//			.and()
//				.withUser("junior").password("123456").roles("USER")
//			.and()
//				.withUser("vitoria").password("123456").roles("USER");
//				
//	}
}
