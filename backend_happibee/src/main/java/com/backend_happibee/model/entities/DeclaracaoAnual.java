package com.backend_happibee.model.entities;

import com.backend_happibee.model.valueObjects.Aprovacao;
import com.backend_happibee.model.valueObjects.Localizacao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "declaracao_anual")
public class DeclaracaoAnual {
        @Getter
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private Long id;

        @Getter
        @Setter
        @Column
        private String apicultorNIF;

        @Getter
        @Setter
        @Column
        private String apicultorNome;

        @Getter
        @Setter
        @Column
        private String apicultorMorada;

        @Getter
        @Setter
        @Column
        private int apicultorCodigoResidencia;

        @Getter
        @Setter
        @Column
        private int apicultorCodigoFreguesia;

        @Getter
        @Setter
        @Column
        private Boolean apicultorCulturaIntensiva;

        @Getter
        @Setter
        @Column
        private int numColmeias;

        @Getter
        @Setter
        @Column
        private int numNucleos;

        @Getter
        @Setter
        @Column
        private int numTotalColonias;

        @Getter
        @Setter
        @Column
        private int numTotal;

        @Getter
        @Setter
        @Column
        private boolean transumancia;

        @Getter
        @Setter
        @Column
        private String registoInicioAtividade;

        @Getter
        @Setter
        @Column
        private String fechoAtividade;

        @Getter
        @Setter
        @Column
        private String pedidoAlteracao;

        @Getter
        @Setter
        @Column
        private boolean zonaControlada;

        @Getter
        @Setter
        @Column
        private Aprovacao aprovacao;

        public DeclaracaoAnual() {
        }

        public DeclaracaoAnual(String apicultorNIF, String apicultorNome, String apicultorMorada,
                               int apicultorCodigoResidencia, int apicultorCodigoFreguesia,
                               Boolean apicultorCulturaIntensiva, int numColmeias,
                               int numNucleos, int numTotalColonias, int numTotal, boolean transumancia, String registoInicioAtividade,
                               String fechoAtividade, String pedidoAlteracao, boolean zonaControlada) {
                this.apicultorNIF = apicultorNIF;
                this.apicultorNome = apicultorNome;
                this.apicultorMorada = apicultorMorada;
                this.apicultorCodigoResidencia = apicultorCodigoResidencia;
                this.apicultorCodigoFreguesia = apicultorCodigoFreguesia;
                this.apicultorCulturaIntensiva = apicultorCulturaIntensiva;
                this.numColmeias = numColmeias;
                this.numNucleos = numNucleos;
                this.numTotalColonias = numTotalColonias;
                this.numTotal = numTotal;
                this.transumancia = transumancia;
                this.registoInicioAtividade = registoInicioAtividade;
                this.fechoAtividade = fechoAtividade;
                this.pedidoAlteracao = pedidoAlteracao;
                this.zonaControlada = zonaControlada;
                this.aprovacao = Aprovacao.ND;
        }
}
