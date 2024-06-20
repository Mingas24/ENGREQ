package com.backend_happibee.model.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "cresta")
public class Cresta {



    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Colmeia colmeia;

    @Column
    private LocalDate data;

    public Cresta(Colmeia colmeia,LocalDate data) {
        this.colmeia = colmeia;
        this.data=data;
    }

    public Cresta() {

    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Colmeia getColmeia() {
        return colmeia;
    }
}
