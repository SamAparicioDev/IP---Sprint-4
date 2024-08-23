package com.example.demo.services;

import com.example.demo.dto.UserDTO;
import com.example.demo.mappers.UserEntityAndUserDTO;
import com.example.demo.models.UserEntity;
import com.example.demo.models.UserRole;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.exceptions.UserExceptions.*;
import com.example.demo.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static com.example.demo.models.UserRole.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class UserServicesTestClass {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserEntityAndUserDTO userEntityAndUserDTO;

    public static UserEntity userEntity;

    public static UserDTO userDTO;

    public static UserDTO userDTOEmptyField;

    public static UserDTO userEmptyRol;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userDTO = new UserDTO();
        userDTO.setId(2L);
        userDTO.setEmail("test@example.com");
        userDTO.setUsername("testuser");
        userDTO.setPassword("password");
        userDTO.setRoles(UserRole.USER);

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEmail("test@example.com");
        userEntity.setUsername("testuser");
        userEntity.setPassword("password");
        userEntity.setRole(UserRole.USER);

        when(userEntityAndUserDTO.userDTOToUserEntity(userDTO)).thenReturn(userEntity);
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityAndUserDTO.userEntityToUserDTO(userEntity)).thenReturn(userDTO);
    }

    @BeforeAll
    public static void createUserDTO() {
        userDTO = new UserDTO( 1L,"Sam", "123", "mail@mail.com", UserRole.USER);
        userDTOEmptyField = new UserDTO(2L);
        userEmptyRol = new UserDTO( "Sam", "123", "mail@mail.com");
    }

    @Test
    public void getUserByIdUserNotFoundTest() {
        assertThrows(UserNotFoundException.class, () -> {
            userServiceImpl.getUserById(1L);
        }, "User Not Found");
    }

    @Test
    public void getUserByIdUserTest() {
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userRepository.findById(1L)).thenReturn(Optional.of(userEntity));
        userRepository.save(userEntity);
        assertEquals(userEntity, userRepository.findById(userEntity.getId()).get());
    }

    @Test
    public void UserTestPasswordFieldIsEmpty() {
        assertThrows(IncompleteUserException.class, () -> {
            userServiceImpl.createUser(userDTOEmptyField);
        }, "Password field is empty");

    }
    @Test
    public void UserTestUsernameFieldIsEmpty() {
        assertThrows(IncompleteUserException.class, () -> {
            userServiceImpl.createUser(userDTOEmptyField);
        },"Username field is empty");
    }

    @Test
    public void UserTestEmailFieldIsEmpty() {
        assertThrows(IncompleteUserException.class, () -> {
            userServiceImpl.createUser(userDTOEmptyField);
        },"Email field is empty");
    }

    @Test
    public void createUserSuccessTest(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        userServiceImpl.validateNotRepeatedFieldsInBody(userDTO);
        userServiceImpl.validateUserNotEmptyFieldsInBody(userDTO);
        userServiceImpl.createUser(userDTO);
    }
    @Test
    public void updateUserSuccessTest(){
        UserDTO newUserDTO = new UserDTO(4l,"Sam", "123", "mail@mail.com", UserRole.USER);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        userServiceImpl.validateNotRepeatedFieldsInBody(userDTO);
        userServiceImpl.validateUserNotEmptyFieldsInBody(userDTO);
        UserDTO userDtonew = userServiceImpl.updateUser(userDTO.getId(), newUserDTO);
        assertEquals(userDTO,userDtonew);

    }
    @Test
    public void deleteUserById(){
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        UserEntity userEntitySaved = userRepository.save(userEntity);
        userRepository.deleteById(1L);
        assertThrows(UserNotFoundException.class,()->{
            userServiceImpl.deleteUserById(1L);
        });

    }
    @Test
    public void changeRolTest(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);
        when(userEntityAndUserDTO.userEntityToUserDTO(userEntity)).thenReturn(userDTO);
        userEntity.setRole(ADMIN);
        UserDTO result = userServiceImpl.changeRol(userEntity.getId(), ADMIN);
        result.setRoles(ADMIN);
        userRepository.save(userEntity);
        assertNotNull(result);
        assertEquals(result.getRoles(), userEntity.getRole());
    }
    @Test
    public void UserTestRoleIsEmpty(){
        userRepository.save(userEntity);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        userEntity.setRole(null);
        assertThrows(UserRoleIsEmpty.class, () -> {
            userServiceImpl.validateRolIsNotEmpty(userEntity.getId());
        });
    }
}
