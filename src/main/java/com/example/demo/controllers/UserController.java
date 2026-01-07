package com.example.demo.controllers;

import com.example.demo.AppConstants;
import com.example.demo.Service.AuthService;
import com.example.demo.Service.ChatProducer;
import com.example.demo.Service.NotificationService;
import com.example.demo.Service.TaskService;
import com.example.demo.models.ChatMessage;
import com.example.demo.models.Notification;
import com.example.demo.models.Task;
import com.example.demo.models.TaskComplete;
import com.example.demo.repositories.ChatMessageRepository;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(AppConstants.API_BASE_URL + "/User")
public class UserController {

    private final TaskService taskService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private ChatProducer chatProducer;
    @Autowired
    private NotificationService notificationService;

    public UserController(TaskService taskService) {
        this.taskService = taskService;
    }

    // LOGIN
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password) {
        boolean valid = authService.authenticate(email, password);
        return valid ? "Authentication successful!" : "Invalid email or password.";
    }

    // Update a user
    @PutMapping("/update/{id}")
    public com.example.demo.models.User updateUser(@PathVariable Integer id, @RequestBody com.example.demo.models.User updatedUser) {
        Optional<com.example.demo.models.User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            com.example.demo.models.User user = optionalUser.get();
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setSecretPassword(updatedUser.getSecretPassword());
            return userRepository.save(user);
        }
        return null;
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

    @PostMapping("/logout")
    public String logout() {
        return "Logout successful";
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

    @PostMapping("/message/admin")
    public String userToAdmin(
            @RequestParam Integer userId,
            @RequestParam String message
    ) {
        // Verify user exists
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create chat message
        ChatMessage chat = new ChatMessage(
                userId,

                1, // admin ID
                "USER",
                message,
                LocalDateTime.now()
        );
        // Save message so user can see it later
        chatMessageRepository.save(chat);
        // Send to RabbitMQ
        chatProducer.sendToAdmin(chat);

        return "Message sent to admin";
    }

    @GetMapping("/messages/{userId}")
    public List<ChatMessage> getUserMessages(@PathVariable Integer userId) {
        return chatMessageRepository.findBySenderIdOrderByCreatedAtAsc(userId);
    }

    @GetMapping("/messages/inbox/{userId}")
    public List<ChatMessage> getUserInbox(@PathVariable Integer userId) {
        return chatMessageRepository.findByReceiverIdOrderByCreatedAtAsc(userId);
    }






}

