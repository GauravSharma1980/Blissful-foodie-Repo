package com.blissful.foodie.config;

import com.blissful.foodie.security.JwtAuthenticationFilter;
import com.blissful.foodie.service.JwtAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class SecurityConfigJwtToken {

    //private JwtAuthenticationFilter jwtAuthenticationFilter;
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception{
            httpSecurity.csrf(csrf->csrf.disable());
            httpSecurity
                    .authorizeHttpRequests(request->
                            //request.requestMatchers(HttpMethod.GET,"/users/**").permitAll()
                            request.requestMatchers(HttpMethod.POST,"/api/v1/auth/**").permitAll());

                    httpSecurity
                            .sessionManagement(httpSecuritySessionManagementConfigurer ->
                                    httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                    httpSecurity.exceptionHandling(session -> session.authenticationEntryPoint(jwtAuthenticationEntryPoint));

                    //we need to call our filter before UsernamePasswordAuthenticationFilter hence doing following.
                    httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                    return httpSecurity.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 =
                User.builder().username("user").password("{noop}user").build();
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1);
        return inMemoryUserDetailsManager;
    }
}
