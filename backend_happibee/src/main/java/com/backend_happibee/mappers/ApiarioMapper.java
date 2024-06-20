package com.backend_happibee.mappers;

import com.backend_happibee.dto.ApiarioDTO;
import com.backend_happibee.dto.LocalizacaoDTO;
import com.backend_happibee.model.entities.Apiario;

import java.util.stream.Collectors;

public class ApiarioMapper {
    public static ApiarioDTO toDTO(Apiario apiario) {
        return new ApiarioDTO(apiario.getId(),apiario.getColmeias().stream().map(o -> ColmeiaMapper.toDTO(o)).collect(Collectors.toList()), apiario.getApicultor().getId(),new LocalizacaoDTO(apiario.getLocalizacao().getLat(),apiario.getLocalizacao().getLongi(),apiario.getLocalizacao().getFreg()));
    }
}
