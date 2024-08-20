package com.example.demo.dto;

import com.example.demo.models.Roles;
import com.example.demo.models.TaskEntity;
import com.example.demo.models.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    private Roles roles = Roles.USER;

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public UserDTO(Long id,String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public UserDTO(String username, String password, String email,Roles roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roles = roles;
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

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
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
