package com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.FormLoginDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityWeb {

    @Bean
    public SecurityFilterChain rules(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((defineRulesSecurityWeb)->
                defineRulesSecurityWeb.requestMatchers(
                        HttpMethod.DELETE, "api/v2/user/delete/"
                ).hasAuthority("ADMIN")
                        .requestMatchers("index.html").authenticated())
                .formLogin((formLoginEdit)->
                        formLoginEdit.loginPage("/index.html")
                                .loginProcessingUrl("/api/login")
                                .usernameParameter("email")
                                .passwordParameter("password"));
        return http.build();
    }
}
