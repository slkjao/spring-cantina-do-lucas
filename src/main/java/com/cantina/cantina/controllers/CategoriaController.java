package com.cantina.cantina.controllers;

import com.cantina.cantina.entities.Categoria;
import com.cantina.cantina.services.CategoriaService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService service;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Categoria> insert(@RequestBody @Valid Categoria Categoria){
        Categoria = service.insert(Categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(Categoria);
    }


    @GetMapping
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> listaCategoriasDto = service.findAll();
        return ResponseEntity.ok().body(listaCategoriasDto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id){
        Categoria Categoria = service.findById(id);
        return ResponseEntity.ok().body(Categoria);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Categoria> update(@PathVariable Long id, @RequestBody @Valid Categoria Categoria){
        Categoria  = service.update(id, Categoria);
        return ResponseEntity.ok().body(Categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrato(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        return ResponseEntity.ok().body("Categoria deletada com sucesso!");
    }

    public Categoria toCategoria(Categoria categoria){
        return modelMapper.map(categoria, Categoria.class);
    }

}
