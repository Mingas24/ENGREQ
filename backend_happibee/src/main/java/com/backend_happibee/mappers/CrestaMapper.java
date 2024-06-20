package com.backend_happibee.mappers;

import com.backend_happibee.dto.CrestaDTO;
import com.backend_happibee.model.entities.Cresta;

public class CrestaMapper {
    /*public static Cresta toEntity(CrestaDTO crestaDTO) throws Exception{
        return new Cresta();
    }*/

    public static CrestaDTO toDTO(Cresta cresta){
        return new CrestaDTO(cresta.getId(),ColmeiaMapper.toDTO(cresta.getColmeia()),cresta.getData());
    }

}