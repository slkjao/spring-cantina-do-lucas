package com.cantina.cantina.controllers;

import com.cantina.cantina.entities.Garcom;
import com.cantina.cantina.services.GarcomService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/garcons")
public class GarcomController {

    @Autowired
    GarcomService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Garcom> insert(@RequestBody @Valid Garcom Garcom){
        Garcom = service.insert(Garcom);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Garcom.getId()).toUri();
        return ResponseEntity.created(uri).body(Garcom);
    }


    @GetMapping
    public ResponseEntity<List<Garcom>> findAll(){
        List<Garcom> listaGarconsDto = service.findAll();
        return ResponseEntity.ok().body(listaGarconsDto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Garcom> findById(@PathVariable Long id){
        Garcom Garcom = service.findById(id);
        return ResponseEntity.ok().body(Garcom);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Garcom> update(@PathVariable Long id, @RequestBody @Valid Garcom Garcom){
        Garcom  = service.update(id, Garcom);
        return ResponseEntity.ok().body(Garcom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return ResponseEntity.ok().body("Garcom deletado com sucesso!");
    }

    public Garcom toGarcom(Garcom garcom){
        return modelMapper.map(garcom, Garcom.class);
    }

}
