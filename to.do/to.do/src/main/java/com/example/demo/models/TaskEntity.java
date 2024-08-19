package com.example.demo.models;

import com.example.demo.enums.TaskStatusEnum;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private TaskStatusEnum status;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user_id;

    public TaskEntity(){

    }

    public TaskEntity(String title, String description, TaskStatusEnum status, UserEntity user_id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", user_id=" + user_id +
                ", user_password=" + user_id.getUsername()+
                ", user_email=" + user_id.getEmail()+
                ", user_username" + user_id.getUsername()+
                '}';
    }
}
