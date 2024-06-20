package com.backend_happibee.model.valueObjects;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class QuantidadePolen implements Serializable {
    @Column
    private double quantidadePolenKg;

    public QuantidadePolen(double quantidadePolenKg) throws Exception {
        setQuantidadePolenKg(quantidadePolenKg);
    }

    public QuantidadePolen() {

    }

    public double getQuantidadePolenKg() {
        return quantidadePolenKg;
    }

    public void setQuantidadePolenKg(double quantidadePolenKg) throws Exception {
        if(quantidadePolenKg <0){
            throw new Exception("Quantidade de Polen inválida!");
        }else{
            this.quantidadePolenKg = quantidadePolenKg;
        }
    }
}
