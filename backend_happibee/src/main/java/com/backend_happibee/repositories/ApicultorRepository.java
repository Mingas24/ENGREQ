package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.Apicultor;
import com.backend_happibee.model.entities.Colmeia;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApicultorRepository extends JpaRepository<Apicultor, Long> {
    @Query(value = "SELECT o FROM Apicultor o WHERE o.nif = ?1")
    Apicultor findByNif(String nif);
    Optional<Apicultor> findByUsername(String username);
}
