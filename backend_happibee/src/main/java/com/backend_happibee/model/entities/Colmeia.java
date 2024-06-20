package com.backend_happibee.model.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "colmeia")
public class Colmeia {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apiario_id")
    private Apiario apiario;

    public void setAlcas(List<Alca> alcas) {
        this.alcas = alcas;
    }

    public List<Alca> getAlcas() {
        return alcas;
    }

    public Long getId() {
        return id;
    }

    @OneToMany(mappedBy = "colmeia")
    private List<Alca> alcas;

    public Apiario getApiario() {
        return apiario;
    }

    public void setApiario(Apiario apiario) {
        this.apiario = apiario;
    }


    public void setId(Long id) {
        this.id = id;
    }
}
