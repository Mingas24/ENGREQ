package com.backend_happibee.model.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.backend_happibee.dto.InspecaoApiarioDTO;
import com.backend_happibee.dto.InspecaoApiarioDetailsDTO;

@Entity
@Table(name = "inspecaoApiario")
public class InspecaoApiario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "apiario_id")
    private Apiario apiario;

    @NotNull
    private String dataHora; 
   
    //Informações sobre o clima, como temperatura, umidade e condições do tempo (isso pode influenciar o comportamento das abelhas).
    private String condicoesMetereologicas;

    //Anotações de qualquer sinal de doença, parasitas ou outros problemas de saúde nas abelhas e de medidas tomadas para o controle.
    private String controlePragasDoencas;

    //Registar se doença tem de ser participada
    @Column(columnDefinition = "TINYINT(1)")
    private boolean  participarDoenca;
   
    //Verficar se há sinais de produção ativa de mel
    @Column(columnDefinition = "TINYINT(1)")
    private boolean  producaoMel;

    //Estado em que se encontram os quadros
    private String estadoQuadros;
    
    //Registo de comportamento da rainha
    private String comportamentoRainha;
    
    //Alimentação suplementar, se estiver sendo fornecida, e a quantidade de alimentos disponíveis.
    private String alimentacao;


    //Anotações sobre as condições gerais do apiário, como a presença de predadores, níveis de atividade das abelhas, comportamento geral das colônias e ações tomadas durante a inspeção
    private String observacoesGerais;

    //#region construtores
    public InspecaoApiario(){

    }

    public InspecaoApiario(Apiario apiario, String dataHora, String condicoesMeteorologicas, String controlePragas, boolean participarDoenca, boolean producaoMel, 
    String estadoQuadros, String comportamentoRainha, String alimentacao, String observacoes){
        this.apiario = apiario;
        this.dataHora = dataHora;
        this.condicoesMetereologicas = condicoesMeteorologicas;
        this.controlePragasDoencas = controlePragas;
        this.participarDoenca = participarDoenca;
        this.producaoMel = producaoMel;
        this.estadoQuadros = estadoQuadros;
        this.comportamentoRainha = comportamentoRainha;
        this.alimentacao = alimentacao;
        this.observacoesGerais = observacoes;
    }
    //#endregion


    //#region Getters e Setters
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apiario getApiario() {
        return apiario;
    }

    public void setApiario(Apiario apiario) {
        this.apiario = apiario;
    }
    

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getCondicoesMetereologicas() {
        return condicoesMetereologicas;
    }

    public void setCondicoesMetereologicas(String condicoesMetereologicas) {
        this.condicoesMetereologicas = condicoesMetereologicas;
    }

    public String getControlePragasDoencas() {
        return controlePragasDoencas;
    }

    public void setControlePragasDoencas(String controlePragasDoencas) {
        this.controlePragasDoencas = controlePragasDoencas;
    }

    public boolean isDoencaParticipada() {
        return participarDoenca;
    }

    public void setParticiparDoenca(boolean value) {
        this.participarDoenca = value;
    }

    public boolean isProducaoMel() {
        return producaoMel;
    }

    public void setProducaoMel(boolean producaoMel) {
        this.producaoMel = producaoMel;
    }

    public String getEstadoQuadros() {
        return estadoQuadros;
    }

    public void setEstadoQuadros(String estadoQuadros) {
        this.estadoQuadros = estadoQuadros;
    }

    public String getComportamentoRainha() {
        return comportamentoRainha;
    }

    public void setComportamentoRainha(String comportamentoRainha) {
        this.comportamentoRainha = comportamentoRainha;
    }

    public String getAlimentacao() {
        return alimentacao;
    }

    public void setAlimentacao(String alimentacao) {
        this.alimentacao = alimentacao;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }
    //#endregion

    
    public InspecaoApiarioDTO toDTO() {
        return new InspecaoApiarioDTO(this.apiario.getId(), this.getDataHora(), this.getCondicoesMetereologicas(), this.getControlePragasDoencas(), this.isDoencaParticipada(), this.isProducaoMel(),this.getEstadoQuadros(),
        this.getComportamentoRainha(), this.getAlimentacao(),this.getObservacoesGerais());
    }

    public InspecaoApiarioDetailsDTO toDetailsDTO(){
        return new InspecaoApiarioDetailsDTO(this.getId(),this.apiario.getId(), this.getDataHora(), this.getCondicoesMetereologicas(), this.getControlePragasDoencas(),this.isDoencaParticipada(), this.isProducaoMel(),this.getEstadoQuadros(),
        this.getComportamentoRainha(), this.getAlimentacao(),this.getObservacoesGerais());
    }


}
