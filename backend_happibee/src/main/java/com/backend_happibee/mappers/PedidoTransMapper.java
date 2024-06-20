package com.backend_happibee.mappers;

import com.backend_happibee.dto.LocalizacaoDTO;
import com.backend_happibee.dto.PedidoTransDTO;
import com.backend_happibee.model.entities.PedidoTrans;

public class PedidoTransMapper {

    public static PedidoTransDTO toDTO(PedidoTrans pedidoTrans){
        return new PedidoTransDTO(pedidoTrans.getId(), pedidoTrans.getApiarioId(),LocalizacaoMapper.toDTO(pedidoTrans.getLocalizacao()),pedidoTrans.getAprovado());
    }
}
