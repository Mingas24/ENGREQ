package com.backend_happibee.controllers;

import com.backend_happibee.dto.ColmeiaDTO;
import com.backend_happibee.services.ColmeiaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/colmeia")
public class ColmeiaController {
    private final ColmeiaService colmeiaService;

    public ColmeiaController(ColmeiaService colmeiaService) {
        this.colmeiaService = colmeiaService;
    }

    @PostMapping ("getByApiario")
    public List<ColmeiaDTO> getColmeiasByApiarioId(@RequestBody String id){
        return colmeiaService.getColmeiasByApiarioId(Long.parseLong(id));
    }


}
