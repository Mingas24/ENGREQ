package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.Colmeia;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiarioRepository extends JpaRepository<Apiario,Long> {
    
    @Query(value = "SELECT a FROM Apiario a WHERE a.id = :id")
    Apiario getApiarioById(@Param("id") Long id);

    @Query(value = "SELECT a FROM Apiario a WHERE a.apicultor.id = :id AND a.instalado=true")
    List<Apiario> getApiariosByApicultorId(@Param("id") Long id);

    @Query(value = "SELECT a FROM Apiario a WHERE a.apicultor.id = :id")
    List<Apiario> getAllApiariosByApicultorId(@Param("id") Long id);

    @Query(value = "SELECT a FROM Apiario a WHERE a.apicultor.id = :id AND a.instalado=false")
    List<Apiario> getApiariosNotInstalledByApicultorId(@Param("id") Long id);
}
