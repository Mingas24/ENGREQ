package com.backend_happibee.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoInstalacaoDTO {
    private Long id;
    private Long apiarioId;
    private boolean autorizado;
    private String observacoes;
}

