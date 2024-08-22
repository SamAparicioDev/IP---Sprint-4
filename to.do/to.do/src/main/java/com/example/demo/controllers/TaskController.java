package com.example.demo.controllers;

import com.example.demo.dto.TaskDTO;
import com.example.demo.enums.TaskStatusEnum;
import com.example.demo.models.TaskEntity;
import com.example.demo.services.impl.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v2/task")
public class TaskController {
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskServiceImpl.getTaskById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO task) {
        return ResponseEntity.ok(taskServiceImpl.createTask(task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable("id") Long id) {
        if(taskServiceImpl.getTaskById(id) != null){
            taskServiceImpl.deleteTaskById(id);
            return ResponseEntity.ok("Se elimino correctamente");
        }
        else{
            return ResponseEntity.badRequest().body("El archivo ya se encuentra eliminado o no existe");
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable("id") Long id, @RequestBody TaskDTO task) {
        taskServiceImpl.updateTask(id,task);
        return ResponseEntity.ok(taskServiceImpl.getTaskById(id));
    }

    @PutMapping("/put/status/{id}")
    public ResponseEntity<TaskDTO> updateTaskStatus(@PathVariable("id") Long id, @RequestParam TaskStatusEnum status) {
        taskServiceImpl.changeStatus(id,status);
        return ResponseEntity.ok(taskServiceImpl.getTaskById(id));
    }
}
