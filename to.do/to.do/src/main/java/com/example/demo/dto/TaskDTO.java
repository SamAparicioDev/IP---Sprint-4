package com.example.demo.dto;

import com.example.demo.enums.TaskStatusEnum;
import com.example.demo.models.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String title;
    @JsonProperty
    private String description;
    @JsonProperty
    private TaskStatusEnum status;
    @JsonProperty
    private UserEntity user_id;

    public TaskDTO(String description, UserEntity user_id, TaskStatusEnum status, String title, Long id) {
        this.description = description;
        this.user_id = user_id;
        this.status = status;
        this.title = title;
        this.id = id;
    }

    public TaskDTO(String title, String description, TaskStatusEnum status, UserEntity user_id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.user_id = user_id;
    }

    public TaskDTO() {

    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
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

    public UserEntity getUser_id() {
        return user_id;
    }

    public void setUser_id(UserEntity user_id) {
        this.user_id = user_id;
    }

    public TaskStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TaskStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", user_id=" + user_id+
                '}';
    }
}
