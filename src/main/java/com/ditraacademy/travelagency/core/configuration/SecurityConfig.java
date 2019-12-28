package com.ditraacademy.travelagency.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_ENDPOINTS = {
            "/hotel**/**",
            "/voyage**/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //disable csrf pas nessecaire maintenant
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // pas d'utliser les session on jwt y3awdhoha jwt = session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS).permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic(); //authentification basic pour te5dem feel poste man basic auth n7otou
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
