package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.Colmeia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColmeiaRepository extends JpaRepository<Colmeia, Long> {
    @Query(value = "SELECT o FROM Colmeia o WHERE o.id = ?1")
    Colmeia getColmeiaById(Long id);

    @Query(value = "SELECT o FROM Colmeia o WHERE o.apiario.id = ?1")
    List<Colmeia> getColmeiasByApiarioId(Long id);
}
