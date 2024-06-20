package com.backend_happibee.dto;

import com.backend_happibee.model.entities.Alca;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * DTO for {@link Alca}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AlcaDTO implements Serializable {
    Long id;
    @PositiveOrZero
    double quantidadeMel;
    @PositiveOrZero
    double quantidadePolen;

    public AlcaDTO(Long id) {
        this.id=id;
    }
}