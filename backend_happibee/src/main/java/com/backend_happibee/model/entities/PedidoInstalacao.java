package com.backend_happibee.model.entities;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.backend_happibee.dto.InspecaoApiarioDTO;
import com.backend_happibee.dto.PedidoInstalacaoDTO;
import com.backend_happibee.model.valueObjects.Localizacao;

@Entity
@Table(name = "pedido_instalacao")
public class PedidoInstalacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    Long apiarioId;

    @Embedded
    Localizacao localizacao;

    @Column
    boolean autorizado;

    @Column
    String observacoes;

    public PedidoInstalacao(){}
    // Construtor
    public PedidoInstalacao(Long apiarioId, Localizacao localizacao, boolean autorizado, String observacoes) {
        this.apiarioId = apiarioId;
        this.localizacao = localizacao;
        this.autorizado = autorizado;
        this.observacoes = observacoes;
    }

    // Métodos Getters
    public Long getId() {
        return id;
    }

    public Long getApiarioId(){
        return apiarioId;
    }

    public Localizacao getLocalizacao(){return  localizacao;}

    public boolean isAutorizado() {
        return autorizado;
    }

    public String getObservacoes() {
        return observacoes;
    }

    // Métodos Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setApiarioId(Long id) {
        this.id = id;
    }

    public void setAutorizado(boolean autorizado) {
        this.autorizado = autorizado;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public void setLocalizacao(Localizacao localizacao){this.localizacao = localizacao;}

    public PedidoInstalacaoDTO toDTO() {
        return new PedidoInstalacaoDTO(this.getId(),this.getApiarioId(),this.isAutorizado(),this.getObservacoes());
    }
}
