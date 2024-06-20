package com.backend_happibee.services;

import com.backend_happibee.dto.DeclaracaoAnualAprovacaoDTO;
import com.backend_happibee.dto.DeclaracaoAnualDTO;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.Apicultor;
import com.backend_happibee.model.entities.Colmeia;
import com.backend_happibee.model.entities.DeclaracaoAnual;
import com.backend_happibee.model.valueObjects.Aprovacao;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.ApicultorRepository;
import com.backend_happibee.repositories.ColmeiaRepository;
import com.backend_happibee.repositories.DeclaracaoAnualRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeclaracaoAnualService {
    private final DeclaracaoAnualRepository declaracaoAnualRepository;
    private final ApicultorRepository apicultorRepository;
    private final ApiarioRepository apiarioRepository;
    private final ColmeiaRepository colmeiaRepository;

    public DeclaracaoAnualService(DeclaracaoAnualRepository declaracaoAnualRepository,
                                  ApicultorRepository apicultorRepository, ApiarioRepository apiarioRepository,
                                  ColmeiaRepository colmeiaRepository) {
        this.declaracaoAnualRepository = declaracaoAnualRepository;
        this.apicultorRepository = apicultorRepository;
        this.apiarioRepository = apiarioRepository;
        this.colmeiaRepository = colmeiaRepository;
    }

    public DeclaracaoAnual realizarDeclaracaoAnual(DeclaracaoAnualDTO declaracaoAnualDTO) throws Exception {
        Apicultor apicultor = getApicultorInfo(declaracaoAnualDTO.getApicultorNIF());
        List<Apiario> apiarios = getApiariosInfo(apicultor.getId());
        List<Colmeia> colmeias = new ArrayList<>();
        for (Apiario apiario : apiarios) {
            colmeias.addAll(getColmeiasInfo(apiario.getId()));
        }
        DeclaracaoAnual declaracaoAnual = new DeclaracaoAnual(apicultor.getNif(), apicultor.getFullName(), apicultor.getMorada()
                , declaracaoAnualDTO.getApicultorCodigoResidencia(),
                declaracaoAnualDTO.getApicultorCodigoFreguesia(),
                declaracaoAnualDTO.isApicultorCulturaIntensiva(), colmeias.size(), colmeias.size(), apiarios.size(),
                colmeias.size() + apiarios.size(), declaracaoAnualDTO.isTransumancia(), declaracaoAnualDTO.getRegistoInicioAtividade(),
                declaracaoAnualDTO.getFechoAtividade(), declaracaoAnualDTO.getPedidoAlteracao(), declaracaoAnualDTO.isZonaControlada());
        return declaracaoAnualRepository.save(declaracaoAnual);
    }

    public Apicultor getApicultorInfo(String apicultorNIF) throws Exception {

        try {
            return apicultorRepository.findByNif(apicultorNIF);
        } catch (Exception e) {
            throw new Exception("Apicultor não encontrado");
        }
    }

    public List<Apiario> getApiariosInfo(Long apicultorId) throws Exception {
        try {
            return apiarioRepository.getApiariosByApicultorId(apicultorId);
        } catch (Exception e) {
            throw new Exception("Apiarios não encontrados");
        }
    }

    public List<Colmeia> getColmeiasInfo(Long apiarioId) throws Exception {
        try {
            return colmeiaRepository.getColmeiasByApiarioId(apiarioId);
        } catch (Exception e) {
            throw new Exception("Colmeias não encontradas");
        }
    }

    public DeclaracaoAnual aprovarDeclaracaoAnual(DeclaracaoAnualAprovacaoDTO aprovacao, Long id) throws Exception {
        Optional<DeclaracaoAnual> declaracaoAnualOptional = declaracaoAnualRepository.findById(id);
        DeclaracaoAnual declaracaoAnual = null;
        if (declaracaoAnualOptional.isPresent()) {
            declaracaoAnual = declaracaoAnualOptional.get();
        } else {
            throw new Exception("Declaracao Anual não encontrada");
        }
        declaracaoAnual.setAprovacao(aprovacao.getAprovacao().toString().equals("SIM") ? Aprovacao.SIM : Aprovacao.NAO);
        return declaracaoAnualRepository.save(declaracaoAnual);
    }

    public List<DeclaracaoAnual> getDeclaracaoAnual(Long id) throws Exception {
        try {
            Apicultor apicultor = null;
            if(apicultorRepository.findById(id).isEmpty()){
                throw new Exception("Apicultor não encontrado");
            }else {
                apicultor = apicultorRepository.findById(id).get();
            }
            List<DeclaracaoAnual> declaracaoAnuals = new ArrayList<>();
            for (DeclaracaoAnual da : declaracaoAnualRepository.findAll()) {
                if (da.getApicultorNIF().equals(apicultor.getNif())) {
                    declaracaoAnuals.add(da);
                }
            }
            return declaracaoAnuals;
        } catch (Exception e) {
            throw new Exception("Declaracao Anual não encontrada");
        }
    }
}
