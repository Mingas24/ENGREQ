package com.backend_happibee.repositories;


import com.backend_happibee.model.entities.Cresta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrestaRepository extends JpaRepository<Cresta, Long> {
    @Query(value = "SELECT c FROM Cresta c WHERE c.colmeia.id = ?1")
    List<Cresta> getCrestasByColmeiaId(Long id);
}
