package com.cantina.cantina.controllers;

import com.cantina.cantina.entities.Mesa;
import com.cantina.cantina.services.MesaService;
import com.cantina.cantina.services.MesaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/mesas")
public class MesaController {

    @Autowired
    MesaService service;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Mesa> insert(@RequestBody @Valid Mesa Mesa) {
        Mesa = service.insert(Mesa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Mesa.getId()).toUri();
        return ResponseEntity.created(uri).body(Mesa);
    }



    @GetMapping
    public ResponseEntity<List<Mesa>> findAll() {
        List<Mesa> listaMesasDto = service.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(listaMesasDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Mesa> findById(@PathVariable Long id) {
        Mesa Mesa = service.findById(id);
        return ResponseEntity.ok().body(Mesa);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Mesa> update(@PathVariable Long id, @RequestBody @Valid Mesa Mesa) {
        Mesa = service.update(id, Mesa);
        return ResponseEntity.ok().body(Mesa);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMesa(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.ok().body("Operaçao Cancelada! Não foi possivel deletar!");
        }
        return ResponseEntity.ok().body("Mesa deletado com sucesso!");
    }

    public Mesa toMesa(Mesa Mesa) {
        return modelMapper.map(Mesa, Mesa.class);
    }

}
