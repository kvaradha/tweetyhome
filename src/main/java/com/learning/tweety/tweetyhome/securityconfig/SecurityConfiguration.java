package com.learning.tweety.tweetyhome.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfiguration {
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        .csrf()
        	.disable()
        .formLogin()
        	.disable()
        .httpBasic()
        	.disable()
        	.exceptionHandling()
            .and()
        .authorizeRequests()
            .antMatchers("/tweetyhome/**")
                .permitAll();
        return http.build();
    }
}
