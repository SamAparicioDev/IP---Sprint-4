package com.example.demo.dto;

import com.example.demo.models.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;
public class UserDTO {

    public UserDTO(){

    }
    @JsonProperty
    private Long id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String password;
    @JsonProperty
    private String email;
    @JsonProperty
    private Set<TaskDTO> task = new HashSet<>();

    @JsonProperty
    private UserRole userRole = UserRole.USER;

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public UserDTO(String username, String password, String email, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    public UserDTO(Long id,String username, String password, String email, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }
    public UserDTO(Long id,String username,  String email, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.userRole = userRole;
    }
    public UserDTO(Long id){
        this.id = id;
    }



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<TaskDTO> getTask() {
        return task;
    }

    public void setTask(Set<TaskDTO> task) {
        this.task = task;
    }

    public UserRole getRoles() {
        return userRole;
    }

    public void setRoles(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", task='" + task + '\'' +
                '}';
    }

}
