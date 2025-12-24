/*
package com.example.demo.Service;

import org.springframework.stereotype.Service;
import rabbit.AdminSender;
@Service
public class AdminService {

    private final AdminSender adminSender;

    // Constructor injection
    public AdminService(AdminSender adminSender) {
        this.adminSender = adminSender;
    }

    // Add this method
    public void broadcast(String message) {
        // Delegate to AdminSender to send to all users
        adminSender.send(message);
    }
}

 */