package com.backend_happibee.controllers;

import com.backend_happibee.dto.AlcaDTO;
import com.backend_happibee.dto.CrestaDTO;
import com.backend_happibee.services.CrestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/cresta")
public class CrestaController {

    private final CrestaService crestaService;
    @Autowired
    public CrestaController(CrestaService crestaService) {
        this.crestaService = crestaService;
    }

    @PostMapping("realizar")
    public CrestaDTO realizarCresta(@RequestBody CrestaDTO crestaDTO) throws Exception {
        return crestaService.realizarCresta(crestaDTO);
    }

    @PostMapping("alcas")
    public List<AlcaDTO> getAlcasByColmeiaId(@RequestBody String id){
        return crestaService.getAlcasByColmeiaId(Long.parseLong(id));
    }

    @GetMapping("/{id}")
    public List<CrestaDTO> getCrestas(@PathVariable("id") final Long id){
        return crestaService.getCrestas(id);
    }
}
