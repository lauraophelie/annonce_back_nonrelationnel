package com.example.voiture.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;
import com.example.voiture.entity.Conversation;
import com.example.voiture.entity.Utilisateur;

@Repository
public interface ConversationRepository extends MongoRepository<Conversation, String> {
    @Query("{ $or: [ { 'utilisateur1': ?0 }, { 'utilisateur2': ?0 } ] }")
    List<Conversation> findByUtilisateur(Utilisateur utilisateur);

    Conversation findByUtilisateur1AndUtilisateur2(Utilisateur utilisateur1, Utilisateur utilisateur2);

    Optional<Conversation> findById(String id);
}
