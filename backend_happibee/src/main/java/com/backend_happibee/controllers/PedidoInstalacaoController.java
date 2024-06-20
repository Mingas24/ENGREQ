package com.backend_happibee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.backend_happibee.dto.*;
import com.backend_happibee.model.entities.PedidoInstalacao;
import com.backend_happibee.services.InspecaoApiarioService;
import com.backend_happibee.services.PedidoInstalacaoService;


@RestController
@RequestMapping("api/pedidoinstalacao")
public class PedidoInstalacaoController {
    @Autowired
    private final PedidoInstalacaoService service;

    public PedidoInstalacaoController(PedidoInstalacaoService service)
    {
        this.service = service;
    }

    //Criar pedido de instalação
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PedidoInstalacaoDTO> create(@RequestBody PedidoInstalacaoDTO p) {
        try {
            //Pedido
            final PedidoInstalacaoDTO pedido = service.createPedidoInstalacao(p);

            return new ResponseEntity<>(pedido, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Erro no registo da inspeção do apiário.");
        }
    }

    //Resposta ao pedido de instalação
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<PedidoInstalacaoDTO> update(@PathVariable("id") Long id, @RequestBody PedidoInstalacaoDTO p) {
        try {
            //Pedido
            final PedidoInstalacaoDTO pedido = service.respostaPedidoInstalacao(id ,p);

            return new ResponseEntity<>(pedido, HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ocorreu um erro na edição do registo.");
        }
    }


    ////Obter lista de pedidos feitos pelo apicultor apiário
    @GetMapping("/getPedidosInstalacao/{apicultorId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<PedidoInstalacaoDTO>> getPedidosByApicultorId(@PathVariable("apicultorId") final Long apicultorId) {
        try{
            List<PedidoInstalacaoDTO> pedidos = service.getPedidosByApicultorId(apicultorId);
            
            return new ResponseEntity<Iterable<PedidoInstalacaoDTO>>(pedidos, HttpStatus.OK);

        }catch (Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontradas inspeções para esse apiário.");
        }
    }
    
}
