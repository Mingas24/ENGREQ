package com.backend_happibee.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend_happibee.model.entities.InspecaoApiario;

@Repository
public interface InspecaoApiarioRepository extends JpaRepository<InspecaoApiario, Long> {
    
    @Query("SELECT a FROM InspecaoApiario a WHERE a.apiario.id = :apiarioId")
    List<InspecaoApiario> getInspecoesByApiarioId(Long apiarioId);
}
