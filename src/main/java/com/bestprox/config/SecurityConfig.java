package com.bestprox.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.bestprox.config.OAuthAuthenticationSucessHandler;
import com.bestprox.service.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
public class SecurityConfig {

    @Autowired
    private OAuthAuthenticationSucessHandler handler;
    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Autowired
    private AuthFailureHandler authFailureHandler;

    // user create and login using java code with in memory service 
    //@Bean
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     // Create user 
    //     UserDetails user1 = User.withUsername("admin123").password("admin123")
    //     .roles("ADMIN","USER").build();

    //     UserDetails user2 = User.withUsername("admin1234").password("admin1234")
    //     .build();
        
    //     var inMemoryUserDetailsManager  = new InMemoryUserDetailsManager();
    //     return inMemoryUserDetailsManager;
    // }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        //user detail service ka object 
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password encoder  ka object 
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        // configuration , kaun se public rahenge or kaun se private rahenge
        httpSecurity.authorizeHttpRequests(authorize->{
            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });

        //form default login
        //agr kuch change krna hoga to yha aayenge form login se related
        // related

        httpSecurity.formLogin(formLogin -> {
            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successForwardUrl("/user/profile");
            // formLogin.failureForwardUrl("/login?error=true");

            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");

            formLogin.failureHandler(authFailureHandler);
            
         });


         httpSecurity.oauth2Login(oauth ->{
            oauth.loginPage("/login");
            oauth.successHandler(handler);
         });

         httpSecurity.csrf(AbstractHttpConfigurer :: disable);
         httpSecurity.logout(logoutForm->{
                logoutForm.logoutUrl("/do-logout");
                logoutForm.logoutSuccessUrl("/login?logout=true");
         });

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }




}
