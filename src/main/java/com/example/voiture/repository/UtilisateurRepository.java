package com.example.voiture.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.voiture.entity.Utilisateur;


@Repository
public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {

    Utilisateur findByEmailAndPassword(String email, String password);
    Utilisateur findByEmail(String email);
    Optional<Utilisateur> findById(String id);
}
