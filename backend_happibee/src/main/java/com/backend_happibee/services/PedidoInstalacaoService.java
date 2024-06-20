package com.backend_happibee.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend_happibee.dto.InspecaoApiarioDetailsDTO;
import com.backend_happibee.dto.LocalizacaoDTO;
import com.backend_happibee.dto.PedidoInstalacaoDTO;
import com.backend_happibee.model.entities.Apiario;
import com.backend_happibee.model.entities.InspecaoApiario;
import com.backend_happibee.model.entities.PedidoInstalacao;
import com.backend_happibee.repositories.ApiarioRepository;
import com.backend_happibee.repositories.PedidoInstalacaoRepository;

@Service
public class PedidoInstalacaoService {
    @Autowired
    private PedidoInstalacaoRepository repository; 

    @Autowired
    private ApiarioRepository repositoryApiario;
    
    //Registar novo pedido
    public PedidoInstalacaoDTO createPedidoInstalacao(final PedidoInstalacaoDTO pedido){
        Apiario apiario = repositoryApiario.getApiarioById(pedido.getApiarioId());

        final PedidoInstalacao _pedido = new PedidoInstalacao(pedido.getApiarioId(),apiario.getLocalizacao(), false, "");
        return repository.save(_pedido).toDTO();
    } 

    //Resposta ao pedido (atualiza pedido e criação de apiário)
    public PedidoInstalacaoDTO respostaPedidoInstalacao(Long id, PedidoInstalacaoDTO pedido){
        // Carrega o pedido existente da DB
        PedidoInstalacao _pedido = repository.getPedidoInstalacaoById(id);

            // Verifica se o pedido existe antes de tentar atualizá-lo
            if (_pedido != null) {
                // Atualiza os atributos relevantes
                _pedido.setAutorizado(pedido.isAutorizado());
                _pedido.setObservacoes(pedido.getObservacoes());

                // Salva as alterações no pedido
                _pedido = repository.saveAndFlush(_pedido);

                // Verifica se o pedido foi autorizado
                if (pedido.isAutorizado()) {
                    // Atualiza o apiário
                    Apiario apiario = repositoryApiario.getApiarioById(pedido.getApiarioId());
                    apiario.setInstalado(true);
                    repositoryApiario.save(apiario);
                }
            }

        return (_pedido != null) ? _pedido.toDTO() : null;
    }

    //Lista de pedidos de instalação
        //Obter lista de inspeções feitas em apiário por id do apiário
    public List<PedidoInstalacaoDTO> getPedidosByApicultorId(Long apicultorId){
        
        List<PedidoInstalacao> pedidos = repository.findAll();
        List<Apiario> apiariosInstalados = repositoryApiario.getApiariosByApicultorId(apicultorId);
        List<Apiario> apiariosNaoInstalados = repositoryApiario.getApiariosNotInstalledByApicultorId(apicultorId);

        // Filtra os pedidos para incluir apenas aqueles relacionados aos apiários instalados
        List<PedidoInstalacaoDTO> pedidosDoApicultorInstalados = pedidos.stream()
        .filter(pedido -> apiariosInstalados.stream().anyMatch(apiario -> apiario.getId().equals(pedido.getApiarioId())))
        .map(PedidoInstalacao::toDTO)
        .collect(Collectors.toList());

        // Filtra os pedidos para incluir apenas aqueles relacionados aos apiários não instalados
        List<PedidoInstalacaoDTO> pedidosDoApicultorNaoInstalados = pedidos.stream()
        .filter(pedido -> apiariosNaoInstalados.stream().anyMatch(apiario -> apiario.getId().equals(pedido.getApiarioId())))
        .map(PedidoInstalacao::toDTO)
        .collect(Collectors.toList());

        // Se necessário, você pode combinar as duas listas em uma única lista
        List<PedidoInstalacaoDTO> todosPedidos = new ArrayList<>(pedidosDoApicultorInstalados);
        todosPedidos.addAll(pedidosDoApicultorNaoInstalados);

        return todosPedidos;
    }
}
