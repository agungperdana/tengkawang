package com.kratonsolution.tengkawang.backoffice.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfigs {
	
    @Autowired
    private AuthenticationService authenticationService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
    	
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new PasswordEncoderImpl());
        provider.setUserDetailsService(authenticationService);
        
        return provider;
    }
    
	@Configuration
	@Order(1)
	public static class BasicHttpSecurityConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private DaoAuthenticationProvider provider;
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        
	    	http.antMatcher("/api/v1/**")
	    		.csrf()
	    		.disable()
	    		.authorizeRequests()
	    		.anyRequest()
	    		.authenticated()
	    		.and()
	    		.httpBasic();
	    }
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth)throws Exception {
	        auth.authenticationProvider(provider);
	    }
	}
	
	@Order(2)
	@Configuration
	public static class FormBasedSecurityConfiguration extends WebSecurityConfigurerAdapter
	{
		@Autowired
		private DaoAuthenticationProvider provider;
		
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {

	    	http.authorizeRequests()
	        	.antMatchers("/assets/**").permitAll()
	        	.antMatchers("/iclock/**").permitAll()
	        	.antMatchers("/backoffice/**").authenticated();
	        
	        http.formLogin()
	        	.loginPage("/login").permitAll()
	        	.defaultSuccessUrl("/backoffice/home", true).permitAll()
	        	.failureForwardUrl("/login?error=true").permitAll()
	        	.and()
	        	.logout().logoutUrl("/logout").permitAll();
	        
	        http.csrf().disable();
	        http.headers().frameOptions().sameOrigin();
	    }
	    
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth)throws Exception {
	        auth.authenticationProvider(provider);
	    }
	}
}
