package com.backend_happibee.controllers;

import com.backend_happibee.dto.DeclaracaoAnualAprovacaoDTO;
import com.backend_happibee.dto.DeclaracaoAnualDTO;
import com.backend_happibee.model.entities.DeclaracaoAnual;
import com.backend_happibee.services.DeclaracaoAnualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/declaracaoAnual")
public class DeclaracaoAnualController {
    @Autowired
    private final DeclaracaoAnualService declaracaoAnualService;

    public DeclaracaoAnualController(DeclaracaoAnualService declaracaoAnualService) {
        this.declaracaoAnualService = declaracaoAnualService;
    }

    @PostMapping("/declarar")
    public DeclaracaoAnual realizarDeclaracaoAnual(@RequestBody DeclaracaoAnualDTO declaracaAnual) throws Exception {
        return declaracaoAnualService.realizarDeclaracaoAnual(declaracaAnual);
    }

    @PutMapping("/aprovacao/{id}")
    public DeclaracaoAnual aprovarDeclaracaoAnual(@RequestBody DeclaracaoAnualAprovacaoDTO aprovacao, @PathVariable long id) throws Exception {
        return declaracaoAnualService.aprovarDeclaracaoAnual(aprovacao, id);
    }

    @GetMapping("/get/{id}")
    public List<DeclaracaoAnual> getDeclaracaoAnual(@PathVariable Long id) throws Exception {
        return declaracaoAnualService.getDeclaracaoAnual(id);
    }
}
