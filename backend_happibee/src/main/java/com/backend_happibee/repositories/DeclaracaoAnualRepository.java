package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.DeclaracaoAnual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclaracaoAnualRepository extends JpaRepository<DeclaracaoAnual, Long> {
}
