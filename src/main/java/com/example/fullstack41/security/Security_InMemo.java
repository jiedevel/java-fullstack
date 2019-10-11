
package com.example.fullstack41.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.
AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.
WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@Configuration

public class Security_InMemo extends WebSecurityConfigurerAdapter {
        @Autowired
        protected void configureGlobal(AuthenticationManagerBuilder auth)
                        throws Exception {                auth.inMemoryAuthentication().
        						withUser("user").password("password")
                                .roles("USER");               
                        	auth.inMemoryAuthentication().withUser("admin").
                        	password("password")
                                .roles("USER", "ADMIN");
        }
        
    
        
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .httpBasic()
                            .realmName("User Registration System")
                    .and()
                    .authorizeRequests()
                             .antMatchers("/template/login.html", "/template/home.html","/js/**","/index.html",
"/","/favicon.ico","/css/app.css","/webjars/**","/login").permitAll()
                            .anyRequest().authenticated()
                            .and()
                            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }
        
        
        
        @SuppressWarnings("deprecation")
		@Bean
        public static NoOpPasswordEncoder passwordEncoder() {
          return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        }

        }