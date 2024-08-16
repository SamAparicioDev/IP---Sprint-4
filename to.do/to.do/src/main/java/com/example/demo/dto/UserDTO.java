package com.example.demo.dto;

import com.example.demo.models.TaskEntity;
import com.example.demo.models.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
