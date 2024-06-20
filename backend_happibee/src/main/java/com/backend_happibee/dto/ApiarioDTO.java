package com.backend_happibee.dto;


import lombok.*;


import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiarioDTO {

    private Long id;

    private List<ColmeiaDTO> colmeias;

    private Long apicultor_id;

    private LocalizacaoDTO localizacao;

/*
    public ApiarioDTO(Long id, List<ColmeiaDTO> colmeias, Long apicultor_id, LocalizacaoDTO localizacaoDTO) {
        this.id = id;
        this.colmeias = colmeias;
        this.apicultor_id = apicultor_id;
        this.localizacaoDTO = localizacaoDTO;
    }

 */

    public ApiarioDTO(Long id, List<ColmeiaDTO> colmeias, Long apicultor_id) {
        this.id = id;
        this.colmeias = colmeias;
        this.apicultor_id = apicultor_id;
    }
}
