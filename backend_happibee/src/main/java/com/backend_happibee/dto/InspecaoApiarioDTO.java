package com.backend_happibee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InspecaoApiarioDTO {
    //private Long id;
    private Long apiarioId;
    private String dataHora;
    private String condicoesMetereologicas;
    private String controlePragasDoencas;
    private boolean participarDoenca;
    private boolean producaoMel;
    private String estadoQuadros;
    private String comportamentoRainha;
    private String alimentacao;
    private String observacoesGerais;

}
