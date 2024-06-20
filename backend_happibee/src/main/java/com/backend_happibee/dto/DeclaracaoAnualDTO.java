package com.backend_happibee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DeclaracaoAnualDTO {
    private String apicultorNIF;
    private int apicultorCodigoResidencia;
    private int apicultorCodigoFreguesia;
    private boolean apicultorCulturaIntensiva;
    private boolean transumancia;
    private String registoInicioAtividade;
    private String fechoAtividade;
    private String pedidoAlteracao;
    private boolean zonaControlada;
    private String aprovacao;
}
