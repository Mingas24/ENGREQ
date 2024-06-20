package com.backend_happibee.mappers;

import com.backend_happibee.dto.LocalizacaoDTO;
import com.backend_happibee.model.valueObjects.Localizacao;

public class LocalizacaoMapper {
    public static Localizacao toDomain(LocalizacaoDTO localizacaoDTO){
     return new Localizacao(localizacaoDTO.getLat(), localizacaoDTO.getLongi(),localizacaoDTO.getFreg() ,localizacaoDTO.isZonaProtegida());
    }

    public static LocalizacaoDTO toDTO(Localizacao localizacao){
        return new LocalizacaoDTO(localizacao.getLat(), localizacao.getLongi(), localizacao.isZonaProtegida(), localizacao.getFreg());
    }

}
