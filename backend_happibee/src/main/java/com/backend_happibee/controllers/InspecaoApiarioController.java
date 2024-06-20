package com.backend_happibee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.backend_happibee.dto.*;
import com.backend_happibee.services.InspecaoApiarioService;


@RestController
@RequestMapping("api/inspecaoapiario")
public class InspecaoApiarioController {
    @Autowired
    private final InspecaoApiarioService service;

    public InspecaoApiarioController(InspecaoApiarioService service)
    {
        this.service = service;
    }

    //Criar novo registo de inspeçã de apiário
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InspecaoApiarioDTO> create(@RequestBody InspecaoApiarioDTO inspecaoApiario) {
        try {
            // Adiciona nova inspeção de apiário
            final InspecaoApiarioDTO insp = service.create(inspecaoApiario);

            return new ResponseEntity<>(insp, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Erro no registo da inspeção do apiário.");
        }
    }

    //Editar registo de inspeçaõ de apiário
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InspecaoApiarioDTO> update(@PathVariable("id") Long id, @RequestBody InspecaoApiarioDTO inspecaoApiario) {
        try {
            // Edita inspeção de apiário
            final InspecaoApiarioDTO insp = service.update(id,inspecaoApiario);

            return new ResponseEntity<>(insp, HttpStatus.OK);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ocorreu um erro na edição do registo.");
        }
    }

    //Apagar registo de inspeção de apiário
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteInspecao(@PathVariable("id") Long id) {
        try {
            boolean apagaRegisto = service.deleteInspecao(id);
    
            if (apagaRegisto) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocorreu um erro a eliminar registo.", e);
        }
    }


    //Obter detalhes de inspeção de apiário por id de inspeção
    @GetMapping("/getInspecaoDetailsById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<InspecaoApiarioDetailsDTO> getInspecaoApiarioDetailsById(@PathVariable("id") final Long id) {
        try{

            InspecaoApiarioDetailsDTO inspecaoDetails = service.getInspecaoDetailsById(id);
            
            return new ResponseEntity<InspecaoApiarioDetailsDTO>(inspecaoDetails, HttpStatus.OK);

        }catch (Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro a obter detalhes de inspeção de apiário.");
        }
    }

    ////Obter lista de inspeções feitas em apiário por id do apiário
    @GetMapping("/getInspecoesByApiarioId/{apiarioId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Iterable<InspecaoApiarioDetailsDTO>> getInspecoesByApiarioId(@PathVariable("apiarioId") final Long apiarioId) {
        try{
            List<InspecaoApiarioDetailsDTO> inspecoesList = service.getInspecoesByApiarioId(apiarioId);
            
            return new ResponseEntity<Iterable<InspecaoApiarioDetailsDTO>>(inspecoesList, HttpStatus.OK);

        }catch (Exception e){
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foram encontradas inspeções para esse apiário.");
        }
    }
}
