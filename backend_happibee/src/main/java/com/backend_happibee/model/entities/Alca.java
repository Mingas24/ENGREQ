package com.backend_happibee.model.entities;

import com.backend_happibee.model.valueObjects.QuantidadeMel;
import com.backend_happibee.model.valueObjects.QuantidadePolen;

import javax.persistence.*;

@Entity
@Table(name = "alca")
public class Alca {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "colmeia_id")
    private Colmeia colmeia;

    @Embedded
    private QuantidadeMel quantidadeMel;

    @Embedded
    private QuantidadePolen quantidadePolen;
    public QuantidadeMel getQuantidadeMel() {
        return quantidadeMel;
    }

    public void setQuantidadeMel(QuantidadeMel quantidadeMel) {
        this.quantidadeMel = quantidadeMel;
    }

    public QuantidadePolen getQuantidadePolen() {
        return quantidadePolen;
    }

    public void setQuantidadePolen(QuantidadePolen quantidadePolen) {
        this.quantidadePolen = quantidadePolen;
    }

    public Colmeia getColmeia() {
        return colmeia;
    }

    public void setColmeia(Colmeia colmeia) {
        this.colmeia = colmeia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
