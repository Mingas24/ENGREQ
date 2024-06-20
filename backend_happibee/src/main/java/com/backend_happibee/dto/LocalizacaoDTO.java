package com.backend_happibee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalizacaoDTO {
    double lat;
    double longi;
    boolean zonaProtegida;
    String freg;

    public LocalizacaoDTO(double lat, double longi,String freg ) {
        this.lat = lat;
        this.longi = longi;
        this.freg = freg;
    }
}
