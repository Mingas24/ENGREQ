package com.backend_happibee.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.backend_happibee.model.entities.PedidoInstalacao;

@Repository
public interface PedidoInstalacaoRepository  extends JpaRepository<PedidoInstalacao, Long> {
    @Query("SELECT a FROM PedidoInstalacao a WHERE a.id = :id")
    PedidoInstalacao getPedidoInstalacaoById(@Param("id") Long id);
}
