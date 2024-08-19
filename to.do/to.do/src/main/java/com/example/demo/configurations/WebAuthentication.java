///* package com.example.demo.configurations;
//
//import com.example.demo.dto.UserDTO;
//import com.example.demo.models.UserEntity;
//import com.example.demo.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.ArrayDeque;
//import java.util.ArrayList;
///*Nos permite crear una clase que extiende de GlobalAutheticationConfigurerAdapter, es decir, vamos a crear un usuario para poder logearnos*/
//@Configuration
//public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public void init(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(authAuthentication->{
//            UserEntity userEntity = userRepository.findByUsername(authAuthentication).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//            return  new User(userEntity.getUsername(),userEntity.getPassword(), new ArrayList<>());
//        });
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
