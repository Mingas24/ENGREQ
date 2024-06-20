package com.backend_happibee.repositories;

import com.backend_happibee.model.entities.PedidoInstalacao;
import com.backend_happibee.model.entities.PedidoTrans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoTransRepository extends JpaRepository<PedidoTrans,Long> {

}
