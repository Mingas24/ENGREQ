package com.backend_happibee.services;

import com.backend_happibee.dto.ApiarioDTO;
import com.backend_happibee.dto.PedidoTransDTO;
import com.backend_happibee.mappers.ApiarioMapper;
import com.backend_happibee.mappers.LocalizacaoMapper;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.PedidoTrans;
import com.backend_happibee.model.valueObjects.Aprovacao;
import com.backend_happibee.repositories.ApiarioRepository;
//import com.backend_happibee.repositories.UserRepository;
import com.backend_happibee.repositories.PedidoTransRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {

    private final ApiarioRepository apiarioRepository;

    private final PedidoTransRepository pedidoTransRepository;

    private final EmailHandler emailHandler;




@Autowired
    public LocalizacaoService(ApiarioRepository apiarioRepository, PedidoTransRepository pedidoTransRepository, EmailHandler emailHandler){

        this.apiarioRepository = apiarioRepository;
    this.pedidoTransRepository = pedidoTransRepository;
    //this.userRepository = userRepository;


        this.emailHandler = emailHandler;
    }

    public ApiarioDTO alterarzona(PedidoTransDTO pedidoTransDTO){
        PedidoTrans pedido = pedidoTransRepository.getById(pedidoTransDTO.getId());
        Apiario apiario = apiarioRepository.getById(pedido.getApiarioId());
        if(pedidoTransDTO.getAprovado() == Aprovacao.SIM) {
            pedido.setAprovado(pedidoTransDTO.getAprovado());
            pedidoTransRepository.save(pedido);
            apiario.setLocalizacao(pedido.getLocalizacao());
            apiarioRepository.save(apiario);
            emailHandler.sendEmail("1180990@isep.ipp.pt","Pedido de Transumância","Confirmamos que o seu pedido de transumância para a Localização:" + pedido.getLocalizacao().toString() +" foi aceite.");
        } else{
            pedido.setAprovado(pedidoTransDTO.getAprovado());
            pedidoTransRepository.save(pedido);
            emailHandler.sendEmail("1180990@isep.ipp.pt","Pedido de Transumância","Confirmamos que o seu pedido de transumância para a Localização:" + pedido.getLocalizacao().toString() +" foi rejeitado.");
        }
        ApiarioDTO returnApiario = ApiarioMapper.toDTO(apiario);
        return returnApiario;
    }
/*
    public ApiarioDTO alterarZona(){

    }
    
 */
}
