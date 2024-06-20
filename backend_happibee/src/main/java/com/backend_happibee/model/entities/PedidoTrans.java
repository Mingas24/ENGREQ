package com.backend_happibee.model.entities;

import com.backend_happibee.model.valueObjects.Aprovacao;
import com.backend_happibee.model.valueObjects.Localizacao;

import javax.persistence.*;

@Entity
@Table(name="pedido")
public class PedidoTrans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long apiarioId;

    private Localizacao localizacao;

    @Column
    private Aprovacao aprovado;

    public Long getApiarioId() {
        return apiarioId;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setAprovado(Aprovacao aprovado) {
        this.aprovado = aprovado;
    }

    public Aprovacao getAprovado() {
        return aprovado;
    }

    public PedidoTrans() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PedidoTrans(Long apiarioId, Localizacao localizacao){
        this.apiarioId = apiarioId;
        this.localizacao = localizacao;
        this.aprovado = Aprovacao.ND;
    }

}
