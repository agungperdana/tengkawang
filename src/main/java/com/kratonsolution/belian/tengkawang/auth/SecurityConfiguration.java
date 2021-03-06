package com.kratonsolution.belian.tengkawang.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @since 1.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled=true,proxyTargetClass=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationService authenticationService;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
        .antMatchers("/assets/**").permitAll()
        .antMatchers("/iclock/**").permitAll()
        .anyRequest().authenticated();
        
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
    protected void configure(AuthenticationManagerBuilder auth)throws Exception
    {
        auth.authenticationProvider(authProvider());
    }
    
    @Bean
    public DaoAuthenticationProvider authProvider()
    {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new PasswordEncoderImpl());
        provider.setUserDetailsService(authenticationService);
        
        return provider;
    }
}
