package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.Alca;
import com.backend_happibee.model.entities.Colmeia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcaRepository extends JpaRepository<Alca, Long> {

    @Query(value = "SELECT o FROM Alca o WHERE o.colmeia.id = ?1")
    List<Alca> getAlcasByColmeiaId(Long id);
}
