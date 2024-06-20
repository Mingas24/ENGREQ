package com.backend_happibee.services;

import com.backend_happibee.dto.AlcaDTO;
import com.backend_happibee.dto.ApiarioDTO;
import com.backend_happibee.dto.ColmeiaDTO;
import com.backend_happibee.model.entities.Alca;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.mappers.ApiarioMapper;
import com.backend_happibee.model.entities.Apicultor;
import com.backend_happibee.model.entities.Colmeia;
import com.backend_happibee.model.valueObjects.Localizacao;
import com.backend_happibee.repositories.AlcaRepository;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.ColmeiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiarioService {
    private final ApiarioRepository apiarioRepository;
    private final ColmeiaRepository colmeiaRepository;
    private final AlcaRepository alcaRepository;

    public ApiarioService(ApiarioRepository apiarioRepository, ColmeiaRepository colmeiaRepository, AlcaRepository alcaRepository) {
        this.apiarioRepository = apiarioRepository;
        this.colmeiaRepository = colmeiaRepository;
        this.alcaRepository = alcaRepository;
    }

    public List<ApiarioDTO> getAllApiarios(){
        List<Apiario> apiarios = apiarioRepository.findAll();
        return apiarios.stream().map(o -> ApiarioMapper.toDTO(o)).collect(Collectors.toList());
    }

    public List<ApiarioDTO> getApiariosByApicultorId(Long id){
        List<Apiario> apiarios = apiarioRepository.getApiariosByApicultorId(id);
        return apiarios.stream().map(o -> ApiarioMapper.toDTO(o)).collect(Collectors.toList());
    }

    public List<ApiarioDTO> getApiariosNotInstalledByApicultorId(Long id){
        List<Apiario> apiarios = apiarioRepository.getApiariosNotInstalledByApicultorId(id);
        return apiarios.stream().map(o -> ApiarioMapper.toDTO(o)).collect(Collectors.toList());
    }
    
    public ApiarioDTO criarApiario(ApiarioDTO apiarioDTO){
        Apiario apiario = new Apiario();
        Apicultor apicultor = new Apicultor();
        apicultor.setId(apiarioDTO.getApicultor_id());
        apiario.setApicultor(apicultor);
        apiario.setLocalizacao(new Localizacao(apiarioDTO.getLocalizacao().getLat(), apiarioDTO.getLocalizacao().getLongi(),apiarioDTO.getLocalizacao().getFreg(),apiarioDTO.getLocalizacao().isZonaProtegida()));
        apiarioRepository.save(apiario);
        for (ColmeiaDTO colmeiaDTO : apiarioDTO.getColmeias()){
            Colmeia colmeia = new Colmeia();
            colmeia.setApiario(apiario);
            colmeiaRepository.save(colmeia);
            for (AlcaDTO alcaDTO: colmeiaDTO.getAlcas()){
                Alca alca = new Alca();
                alca.setColmeia(colmeia);
                alcaRepository.save(alca);
            }
        }
        return apiarioDTO;
    }
}
