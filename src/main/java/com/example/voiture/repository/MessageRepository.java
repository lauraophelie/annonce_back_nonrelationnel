package com.example.voiture.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Message;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findByConversation(Conversation conversation);
}

