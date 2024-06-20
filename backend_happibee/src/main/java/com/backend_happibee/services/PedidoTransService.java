package com.backend_happibee.services;

import com.backend_happibee.dto.PedidoTransDTO;
import com.backend_happibee.mappers.LocalizacaoMapper;
import com.backend_happibee.mappers.PedidoTransMapper;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.PedidoTrans;
import com.backend_happibee.model.valueObjects.Localizacao;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.PedidoTransRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoTransService {

    private final ApiarioRepository apiarioRepository;

    private final PedidoTransRepository pedidoTransRepository;

    public PedidoTransService(ApiarioRepository apiarioRepository, PedidoTransRepository pedidoTransRepository) {
        this.apiarioRepository = apiarioRepository;
        this.pedidoTransRepository = pedidoTransRepository;
    }

    public PedidoTransDTO criarPedidoTrans(PedidoTransDTO pedidoTransDTO) {
         Localizacao loc = LocalizacaoMapper.toDomain(pedidoTransDTO.getLocalizacaoDTO());
        PedidoTrans newPedido = new PedidoTrans(pedidoTransDTO.getApiarioId(),loc);
        pedidoTransRepository.save(newPedido);
        return pedidoTransDTO;
    }

    public List<PedidoTransDTO> getPedidosTrans(Long id){
        List<Apiario> list = apiarioRepository.getApiariosByApicultorId(id);
        List<PedidoTrans>list2 = new ArrayList<>();
        for(Apiario a : list){
            for(PedidoTrans p : pedidoTransRepository.findAll() ){
                if(a.getId() == p.getApiarioId()){
                    list2.add(p);
                }
            }
        }
        return  list2.stream().map(o -> PedidoTransMapper.toDTO(o)).collect(Collectors.toList());
    }
}
