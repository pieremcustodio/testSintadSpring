package com.practica.controllers;

import com.practica.dto.request.EntidadRequest;
import com.practica.dto.response.MessageResponse;
import com.practica.services.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/entidad")
public class EntidadController {
    @Autowired
    private EntidadService entidadService;

    @GetMapping
    public List<EntidadRequest> index(){
        return entidadService.allEntidad();
    }

    @GetMapping("{id}")
    public ResponseEntity<EntidadRequest> show(@PathVariable(name = "id") long id){
        try{
            return ResponseEntity.ok(entidadService.oneEntidad(id));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping
    public ResponseEntity<?> store(@Valid @RequestBody EntidadRequest entidadDTO){
        try{
            entidadService.createEntidad(entidadDTO);
            return ResponseEntity.ok(new MessageResponse("Entidad creada"));
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody EntidadRequest entidadDTO,
                                         @PathVariable(name = "id") long id){
        try{
            entidadService.updateEntidad(entidadDTO, id);
            return ResponseEntity.ok(new MessageResponse("Entidad actualizada con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> destroy(@PathVariable(name = "id") long id){
        try{
            entidadService.deleteEntidad(id);
            return ResponseEntity.ok(new MessageResponse("Entidad eliminada con exito"));
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
