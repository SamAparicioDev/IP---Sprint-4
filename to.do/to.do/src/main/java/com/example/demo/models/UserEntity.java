package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "user_id" , fetch = FetchType.LAZY)
    private Set<TaskEntity> task = new HashSet<>();

    public UserEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public UserEntity(Long id) {
        this.id = id;
    }

    public UserEntity() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Set<TaskEntity> getTask() {
        return task;
    }

    public void setTask(Set<TaskEntity> task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", task='" + getTask() + '\'' +
                '}';
    }
}
