package com.backend_happibee.model.entities;

import com.backend_happibee.model.valueObjects.Localizacao;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "apiario")
public class Apiario {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "apiario")
    private List<Colmeia> colmeias;

    @ManyToOne
    @JoinColumn(name = "apicultor_id")
    private Apicultor apicultor;

    @Embedded
    Localizacao localizacao;

    @Column
    boolean instalado;

    /*@ManyToOne
    @JoinColumn(name="zona_id")
    private Zona zona;

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }*/
    @OneToMany(mappedBy = "apiario",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<InspecaoApiario> inspecoes;


    public boolean isInstalado() {
        return instalado;
    }
    
    public void setInstalado(boolean instalado) {
        this.instalado = instalado;
    }

    public Apicultor getApicultor() {
        return apicultor;
    }

    public void setApicultor(Apicultor apicultor) {
        this.apicultor = apicultor;
    }

    public Long getId() {
        return id;
    }


    public List<Colmeia> getColmeias() {
        return colmeias;
    }

    public Localizacao getLocalizacao(){return  localizacao;}

    public void setLocalizacao(Localizacao localizacao){this.localizacao = localizacao;}
    public void setColmeias(List<Colmeia> colmeias) {
        this.colmeias = colmeias;
    }
}
