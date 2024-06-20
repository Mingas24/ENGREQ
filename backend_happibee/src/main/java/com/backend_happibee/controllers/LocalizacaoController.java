package com.backend_happibee.controllers;

import com.backend_happibee.dto.ApiarioDTO;
import com.backend_happibee.dto.PedidoTransDTO;
import com.backend_happibee.services.LocalizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/localizacao")
public class LocalizacaoController {


    private final LocalizacaoService locService;
    @Autowired
    public LocalizacaoController(LocalizacaoService locService) {
        this.locService = locService;
    }

    @PutMapping("alterarLocalizacao")
    public ApiarioDTO alterarLocalizacao(@RequestBody PedidoTransDTO pedidoTransDTO){
        return locService.alterarzona(pedidoTransDTO);
    }
}
