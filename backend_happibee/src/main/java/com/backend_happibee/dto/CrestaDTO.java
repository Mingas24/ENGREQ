package com.backend_happibee.dto;

import com.backend_happibee.model.entities.Cresta;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link Cresta}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CrestaDTO implements Serializable {
    Long id;
    @NotNull
    ColmeiaDTO colmeia;
    LocalDate data;

    public CrestaDTO(Long id, ColmeiaDTO colmeia) {
        this.id = id;
        this.colmeia = colmeia;
    }
}