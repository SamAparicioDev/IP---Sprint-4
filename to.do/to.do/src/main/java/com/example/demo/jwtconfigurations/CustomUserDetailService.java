package com.example.demo.jwtconfigurations;

import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserExceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/*Clase con JWT que se encarga de crear el usuario vasandose en la implementacion de la clase
UserDetails Service
 */
@Configuration
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new  UserNotFoundException("UserNotFound"));
        return new User(userEntity.getUsername(),userEntity.getPassword(), AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
    }}
