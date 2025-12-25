package com.example.demo.repositories;

import com.example.demo.models.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Integer> {

    // User or Admin messages SENT
    List<ChatMessage> findBySenderIdOrderByCreatedAtAsc(Integer senderId);

    // User or Admin messages RECEIVED
    List<ChatMessage> findByReceiverIdOrderByCreatedAtAsc(Integer receiverId);
}
