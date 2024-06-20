package com.backend_happibee.mappers;

import com.backend_happibee.dto.AlcaDTO;
import com.backend_happibee.dto.QuantidadeMelDTO;
import com.backend_happibee.dto.QuantidadePolenDTO;
import com.backend_happibee.model.entities.Alca;

public class AlcaMapper {
    public static AlcaDTO toDto(Alca alca){
        return new AlcaDTO(alca.getId());
    }
}
