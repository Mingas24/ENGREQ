package com.backend_happibee.mappers;

import com.backend_happibee.dto.ColmeiaDTO;
import com.backend_happibee.model.entities.Colmeia;

import java.util.stream.Collectors;

public class ColmeiaMapper {
    public static ColmeiaDTO toDTO(Colmeia colmeia) {
        return new ColmeiaDTO(colmeia.getId(),colmeia.getAlcas().stream().map(o -> AlcaMapper.toDto(o)).collect(Collectors.toList()));
    }
}
