package com.cantina.cantina.controllers;

import com.cantina.cantina.entities.Pedido;
import com.cantina.cantina.services.PedidoService;
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
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    PedidoService service;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<Pedido> insert(@RequestBody @Valid Pedido Pedido) {
        Pedido = service.insert(Pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(Pedido);
    }



    @GetMapping
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> listaPedidosDto = service.findAll();
        return ResponseEntity.status(HttpStatus.CREATED).body(listaPedidosDto);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido Pedido = service.findById(id);
        return ResponseEntity.ok().body(Pedido);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Long id, @RequestBody @Valid Pedido Pedido) {
        Pedido = service.update(id, Pedido);
        return ResponseEntity.ok().body(Pedido);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePedido(@PathVariable Long id) {
        boolean deleted = service.delete(id);
        if (!deleted) {
            return ResponseEntity.ok().body("Operaçao Cancelada! Não foi possivel deletar!");
        }
        return ResponseEntity.ok().body("Pedido deletado com sucesso!");
    }

    public Pedido toPedido(Pedido Pedido) {
        return modelMapper.map(Pedido, Pedido.class);
    }

}
