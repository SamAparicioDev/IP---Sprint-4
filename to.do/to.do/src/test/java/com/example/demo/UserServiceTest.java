package com.example.demo;

import com.example.demo.dto.UserDTO;
import com.example.demo.models.UserEntity;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.exceptions.UserNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;

    static UserEntity userEntity = new UserEntity();

    static UserDTO userDTO = new UserDTO();

    @BeforeAll
    public static void  createEntityUser(){
          userEntity = new UserEntity(1L,"Samuel","ola","aparic1onsss4@gmail.com");
    }

    @BeforeAll
    public static void createDTOUser(){
        userDTO = new UserDTO(1L,"Samuel","ola","samusamu@gmail.com");
    }

    @Test
    public void getUserByIdTest(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        UserDTO userDTO = userService.getUserById(1L);
        assertEquals("Samuel",userDTO.getUsername());
    }
    @Test
   public void createUserTest(){
        when(userRepository.save(any())).thenReturn(userEntity);
        userDTO = userService.createUser(userDTO);
        assertEquals("Samuel",userDTO.getUsername());
    }

  /*  @Test
    public void deleteUserByIdTest(){
        when(userRepository.findById(any())).thenReturn(Optional.of(userEntity));
        UserDTO userDTO1 = userService.getUserById(userEntity.getId());
        userService.deleteUserById(userDTO1.getId());
        assertEquals(null,userDTO);

    }*/

    @Test
    public void updateUserTest(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        UserDTO userDTOOne = userService.getUserById(1L);
        UserDTO userDTOTwo = userService.updateUser(1L,userDTO);
        assertNotEquals(userDTOOne,userDTOTwo);
    }

}