package com.backend_happibee.services;

import com.backend_happibee.dto.ColmeiaDTO;
import com.backend_happibee.mappers.ColmeiaMapper;
import com.backend_happibee.model.entities.Colmeia;
import com.backend_happibee.repositories.AlcaRepository;
import com.backend_happibee.repositories.ColmeiaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColmeiaService {
    private final ColmeiaRepository colmeiaRepository;
    private final AlcaRepository alcaRepository;

    public ColmeiaService(ColmeiaRepository colmeiaRepository, AlcaRepository alcaRepository) {
        this.colmeiaRepository = colmeiaRepository;
        this.alcaRepository = alcaRepository;
    }

    public List<ColmeiaDTO> getColmeiasByApiarioId (Long id){
        List<Colmeia> colmeiasSemAlcas = colmeiaRepository.getColmeiasByApiarioId(id);
        List<Colmeia> colmeias = new ArrayList<>();
        for(Colmeia c : colmeiasSemAlcas){
            c.setAlcas(alcaRepository.getAlcasByColmeiaId(c.getId()));
            colmeias.add(c);
        }
        return colmeias.stream().map(o -> ColmeiaMapper.toDTO(o)).collect(Collectors.toList());
    }
}
