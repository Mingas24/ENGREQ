package com.backend_happibee.services;

import com.backend_happibee.dto.AlcaDTO;
import com.backend_happibee.dto.CrestaDTO;
import com.backend_happibee.mappers.AlcaMapper;
import com.backend_happibee.mappers.ColmeiaMapper;
import com.backend_happibee.mappers.CrestaMapper;
import com.backend_happibee.model.entities.Alca;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.Colmeia;
import com.backend_happibee.model.entities.Cresta;
import com.backend_happibee.model.valueObjects.QuantidadeMel;
import com.backend_happibee.model.valueObjects.QuantidadePolen;
import com.backend_happibee.repositories.AlcaRepository;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.ColmeiaRepository;
import com.backend_happibee.repositories.CrestaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CrestaService {
    private final CrestaRepository crestaRepository;
    private final ColmeiaRepository colmeiaRepository;
    private final AlcaRepository alcaRepository;
    private final ApiarioRepository apiarioRepository;

    public CrestaService(CrestaRepository crestaRepository, ColmeiaRepository colmeiaRepository, AlcaRepository alcaRepository, ApiarioRepository apiarioRepository) {
        this.crestaRepository = crestaRepository;
        this.colmeiaRepository = colmeiaRepository;
        this.alcaRepository = alcaRepository;
        this.apiarioRepository = apiarioRepository;
    }

    public CrestaDTO realizarCresta(CrestaDTO crestaDTO) throws Exception {
        Colmeia colmeia = colmeiaRepository.getColmeiaById(crestaDTO.getColmeia().getId());
        List<Alca> alcas = colmeia.getAlcas();
        List<AlcaDTO> alcaDTOS= crestaDTO.getColmeia().getAlcas();
        for (Alca alca: alcas){
            for (AlcaDTO alcaDTO: alcaDTOS){
                if(alca.getId().equals(alcaDTO.getId())){
                    alca.setQuantidadeMel(new QuantidadeMel(alcaDTO.getQuantidadeMel()));
                    alca.setQuantidadePolen(new QuantidadePolen(alcaDTO.getQuantidadePolen()));
                }
            }
            alcaRepository.save(alca);
        }
        Cresta cresta = crestaRepository.save(new Cresta(colmeiaRepository.getColmeiaById(crestaDTO.getColmeia().getId()), LocalDate.now()));
        CrestaDTO crestaDTOreturn = new CrestaDTO(cresta.getId(),crestaDTO.getColmeia(),cresta.getData());
        return crestaDTOreturn;
    }

    public List<AlcaDTO> getAlcasByColmeiaId(Long id){
        List<Alca> alcas = alcaRepository.getAlcasByColmeiaId(id);
        List<AlcaDTO> alcaDTOS = new ArrayList<>();
        for (Alca a : alcas){
            alcaDTOS.add(new AlcaDTO(a.getId()));
        }
        return alcaDTOS;
    }

    public List<CrestaDTO> getCrestas(Long id){
        List<Cresta> crestas = new ArrayList<>();
        for(Apiario a : apiarioRepository.getAllApiariosByApicultorId(id)){
            for(Colmeia c : colmeiaRepository.getColmeiasByApiarioId(a.getId())){
                for(Cresta cresta : crestaRepository.getCrestasByColmeiaId(c.getId())){
                    crestas.add(cresta);
                }
            }
        }
        return crestas.stream().map(o -> CrestaMapper.toDTO(o)).collect(Collectors.toList());
    }
}
