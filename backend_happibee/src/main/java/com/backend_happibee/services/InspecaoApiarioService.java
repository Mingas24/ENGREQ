package com.backend_happibee.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_happibee.dto.InspecaoApiarioDTO;
import com.backend_happibee.dto.InspecaoApiarioDetailsDTO;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.Apicultor;
import com.backend_happibee.model.entities.InspecaoApiario;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.InspecaoApiarioRepository;


@Service
public class InspecaoApiarioService {
    @Autowired
    private InspecaoApiarioRepository repository; 
    
    @Autowired
    private ApiarioRepository repositoryApiario;

      //Criar novo registo de inspeçã de apiário
    public InspecaoApiarioDTO create(final InspecaoApiarioDTO insp) {
        Apiario apiario = repositoryApiario.getApiarioById(insp.getApiarioId());

        final InspecaoApiario inspecao = new InspecaoApiario(apiario,insp.getDataHora(),insp.getCondicoesMetereologicas(),insp.getControlePragasDoencas(),insp.isParticiparDoenca(),insp.isProducaoMel(), insp.getEstadoQuadros(),insp.getComportamentoRainha(),insp.getAlimentacao(),insp.getObservacoesGerais());

        return repository.save(inspecao).toDTO();
    }

    //Editar registo de inspeçaõ de apiário
    public InspecaoApiarioDTO update(Long id, final InspecaoApiarioDTO insp) {

        InspecaoApiario  inspecaoToUpdate = repository.getById(id);
        //Update fields
        inspecaoToUpdate.setDataHora(insp.getDataHora());
        inspecaoToUpdate.setCondicoesMetereologicas(insp.getCondicoesMetereologicas());
        inspecaoToUpdate.setControlePragasDoencas(insp.getControlePragasDoencas());
        inspecaoToUpdate.setParticiparDoenca(insp.isParticiparDoenca());
        inspecaoToUpdate.setProducaoMel(insp.isProducaoMel());
        inspecaoToUpdate.setEstadoQuadros(insp.getEstadoQuadros());
        inspecaoToUpdate.setComportamentoRainha(insp.getComportamentoRainha());
        inspecaoToUpdate.setAlimentacao(insp.getAlimentacao());
        inspecaoToUpdate.setObservacoesGerais(insp.getObservacoesGerais());

        return repository.save(inspecaoToUpdate).toDTO();
    }

    //Apagar registo de inspeção de apiário
    public boolean deleteInspecao(Long id){
        try {
            // Verifica se registo existe antes de apagar
            if (repository.existsById(id)) {
                InspecaoApiario inspecao = repository.getById(id);
                repository.delete(inspecao);
    
                // Verifica depois de apagar, paga garantir que registo foi eliminado
                return !repository.existsById(id);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    //Obter detalhes de inspeção de apiário por id de inspeção
    public InspecaoApiarioDetailsDTO getInspecaoDetailsById(Long id){
         InspecaoApiario inspecao = repository.getById(id);

        return inspecao.toDetailsDTO();
    }

    //Obter lista de inspeções feitas em apiário por id do apiário
    public List<InspecaoApiarioDetailsDTO> getInspecoesByApiarioId(Long apiarioId){
        
        List<InspecaoApiario> inspecoes = repository.getInspecoesByApiarioId(apiarioId);

        List<InspecaoApiarioDetailsDTO> inspecoesDTO = inspecoes.stream()
            .map(InspecaoApiario::toDetailsDTO)
            .collect(Collectors.toList());

        return inspecoesDTO;
    }
}
