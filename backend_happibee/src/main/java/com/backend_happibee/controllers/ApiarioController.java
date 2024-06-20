package com.backend_happibee.controllers;

import com.backend_happibee.dto.ApiarioDTO;
import com.backend_happibee.dto.InspecaoApiarioDetailsDTO;
import com.backend_happibee.services.ApiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping(path = "api/apiario")
public class ApiarioController {

    private final ApiarioService apiarioService;

    @Autowired
    public ApiarioController(ApiarioService apiarioService) {
        this.apiarioService = apiarioService;
    }

    @GetMapping
    public List<ApiarioDTO> getAllApiarios(){
        return apiarioService.getAllApiarios();
    }

    @GetMapping("/getApiariosByApicultorId/{id}")
    public  List<ApiarioDTO> getApiariosByApicultorId(@PathVariable("id") final Long id) {
        return apiarioService.getApiariosByApicultorId(id);
    }

    @GetMapping("/getApiariosNotInstalledByApicultorId/{id}")
    public  List<ApiarioDTO> getApiariosNotInstalledByApicultorId(@PathVariable("id") final Long id) {
        return apiarioService.getApiariosNotInstalledByApicultorId(id);
    }

    @PostMapping("criar")
    public ApiarioDTO criarApiario(@RequestBody ApiarioDTO apiarioDTO){
        return apiarioService.criarApiario(apiarioDTO);
    }
}
