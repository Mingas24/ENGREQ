package com.backend_happibee.dto;

import com.backend_happibee.model.entities.Alca;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.Colmeia;
import lombok.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link Colmeia}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ColmeiaDTO implements Serializable {
    Long id;
    @NotNull
    List<AlcaDTO> alcas;



    /*public ColmeiaDTO(Long id, List<AlcaDTO> alcas) {
        this.id = id;
        this.alcas = alcas;
    }*/
}