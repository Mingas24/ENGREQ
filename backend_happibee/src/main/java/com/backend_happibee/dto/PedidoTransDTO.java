package com.backend_happibee.dto;

import com.backend_happibee.model.valueObjects.Aprovacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoTransDTO {

    Long id;

    Long apiarioId;

    LocalizacaoDTO localizacaoDTO;

    Aprovacao aprovado;

}
