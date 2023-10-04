package com.cantina.cantina.controllers;

import com.cantina.cantina.entities.Prato;
import com.cantina.cantina.services.PratoService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/pratos")
public class PratoController {

    @Autowired
    PratoService service;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Prato> insert(@RequestBody @Valid Prato Prato) {
        Prato = service.insert(Prato);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Prato.getId()).toUri();
        return ResponseEntity.created(uri).body(Prato);
    }



    @GetMapping
    public ResponseEntity<List<Prato>> findAll() {
        List<Prato> listaPratosDto = service.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(listaPratosDto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Prato> findById(@PathVariable Long id) {
        Prato Prato = service.findById(id);
        return ResponseEntity.ok().body(Prato);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Prato> update(@PathVariable Long id, @RequestBody @Valid Prato Prato) {
        Prato = service.update(id, Prato);
        return ResponseEntity.ok().body(Prato);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrato(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.ok().body("Operaçao Cancelada! Não foi possivel deletar!");
        }
        return ResponseEntity.ok().body("Prato deletado com sucesso!");
    }

    public Prato toPrato(Prato prato) {
        return modelMapper.map(prato, Prato.class);
    }

}
