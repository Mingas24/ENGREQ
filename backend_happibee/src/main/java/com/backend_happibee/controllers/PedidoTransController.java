package com.backend_happibee.controllers;

import com.backend_happibee.dto.PedidoTransDTO;
import com.backend_happibee.services.PedidoTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/pedidotrans")
public class PedidoTransController {
    private final PedidoTransService pedidoTransService;




    @Autowired
    public PedidoTransController(PedidoTransService pedidoTransService) {

        this.pedidoTransService = pedidoTransService;

    }

    @PostMapping("criar")
    public PedidoTransDTO criarPedidoTrans(@RequestBody PedidoTransDTO pedidoTransDTO){
        return pedidoTransService.criarPedidoTrans(pedidoTransDTO);
    }

    @GetMapping("/get/{id}")
    public List<PedidoTransDTO> getPedidos(@PathVariable("id") final Long id){
        return pedidoTransService.getPedidosTrans(id);
    }



}
