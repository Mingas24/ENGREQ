package com.backend_happibee.model.valueObjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuantidadeMel implements Serializable {

    @Column
    private double quantidadeMelKg;

    public QuantidadeMel(double quantidadeMelKg) throws Exception {
        setQuantidadeMelKg(quantidadeMelKg);
    }

    public QuantidadeMel() {

    }

    public double getQuantidadeMelKg() {
        return quantidadeMelKg;
    }

    public void setQuantidadeMelKg(double quantidadeMelKg) throws Exception {
        if(quantidadeMelKg <0){
            throw new Exception("Quantidade de Mel inválida!");
        }else{
            this.quantidadeMelKg = quantidadeMelKg;
        }
    }
}
