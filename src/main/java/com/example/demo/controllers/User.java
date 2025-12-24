package com.example.demo.controllers;

import com.example.demo.Service.AuthService;
import com.example.demo.Service.NotificationService;
import com.example.demo.Service.TaskService;
import com.example.demo.models.Notification;
import com.example.demo.models.Task;
import com.example.demo.models.TaskComplete;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/User")
public class User {  // ✅ renamed to avoid conflict with User model

    private final TaskService taskService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;
    @Autowired
    private NotificationService notificationService;
    public User(TaskService taskService) {
        this.taskService = taskService;
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        boolean valid = authService.authenticate(email, password);
        return valid ? "Authentication successful!" : "Invalid email or password.";
    }

    // TEST
    @GetMapping("/test")
    public String test() {
        return "AUTH OK";
    }

    // REGISTER
    @PostMapping("/register")
    public com.example.demo.models.User register(@RequestBody com.example.demo.models.User user) {
        user.setCreatedAt(LocalDateTime.now());
        user.setUserActive(false);
        return userRepository.save(user);
    }

    // VIEW NOTIFICATIONS
    @GetMapping("/not/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Integer userId) {
        return notificationService.getUserNotifications(userId);
    }

    // MARK NOTIFICATION AS READ
    @PutMapping("/read/{id}")
    public String markAsRead(@PathVariable Integer id) {
        notificationService.markAsRead(id);
        return "Notification marked as read";
    }
/*
    // SEND MESSAGE TO ADMIN
    @GetMapping("/send")
    public String sendMessage(@RequestParam String username,
                              @RequestParam String msg) {
        return userService.sendToAdmin(username, msg);
    }

 */


    // VIEW TASKS
    @GetMapping("task/{userId}")
    public List<Task> getTasksForUser(@PathVariable Integer userId) {
        return taskService.getTasksForUser(userId);
    }

    @PostMapping("/complete")
    public Task completeTask(@RequestBody TaskComplete taskComplete) {
        return taskService.completeTask(taskComplete);
    }


}

