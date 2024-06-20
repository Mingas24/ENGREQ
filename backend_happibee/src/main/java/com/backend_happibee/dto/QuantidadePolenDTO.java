package com.backend_happibee.dto;

import lombok.*;

import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

/**
 * DTO for {@link com.backend_happibee.model.valueObjects.QuantidadePolen}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuantidadePolenDTO implements Serializable {
    @PositiveOrZero
    double quantidadePolenKg;
}