//package com.example.demo.configurations;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.WebAttributes;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableWebSecurity
//public class WebSecurity {
//
//        /*Definir las reglas que tendra la aplicacion web*/
//    @Bean
//   public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
//
//       http.authorizeHttpRequests( authorizationManagerRequestMatcherRegistry ->
//                authorizationManagerRequestMatcherRegistry
//                        .requestMatchers(HttpMethod.DELETE, "/api/user/delete").hasAuthority("USER")
//                       .requestMatchers("/index.html").authenticated())
//                .headers( httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.frameOptions(
//                        HeadersConfigurer.FrameOptionsConfig::sameOrigin
//                ))
//                .exceptionHandling( httpSecurityExceptionHandlingConfigurer ->
//                        httpSecurityExceptionHandlingConfigurer
//                              .authenticationEntryPoint( (request, response, authException) -> response.sendError(403) ))
//                .formLogin( httpSecurityFormLoginConfigurer ->
//                        httpSecurityFormLoginConfigurer
//                                .loginPage("/index.html")
//                                .loginProcessingUrl("/api/login")
//                                .usernameParameter("email")
//                                .passwordParameter("password")
//                                .successHandler((request, response, authentication) -> clearAuthenticationAtributtes(request))
//                                .permitAll()
//                                )
//               .csrf(AbstractHttpConfigurer::disable).rememberMe(Customizer.withDefaults())
//               .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
//                        .logoutUrl("/api/logout")
//                        .deleteCookies("JSESSIONID"));
//        return http.build();
//    }
//
//   public void clearAuthenticationAtributtes(HttpServletRequest request){
//       HttpSession session = request.getSession(false);
//        if (session != null){
//            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
//        }
//   }
//}
