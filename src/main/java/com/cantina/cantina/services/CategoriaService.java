package com.cantina.cantina.services;

import com.cantina.cantina.controllers.CategoriaController;
import com.cantina.cantina.controllers.PratoController;
import com.cantina.cantina.entities.Categoria;

import com.cantina.cantina.entities.Prato;

import com.cantina.cantina.repositories.CategoriaRepository;

import com.cantina.cantina.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CategoriaService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    CategoriaRepository repository;

    public Categoria insert(Categoria categoriaDto) {
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDto, categoria);
        return repository.save(categoria);
    }


    public List<Categoria> findAll() {
        return repository.findAll();
    }

    public Categoria findById(Long id) {
        checkIfExists(id);
        Optional<Categoria> categoriaOptional = repository.findById(id);
        return categoriaOptional.get();

    }

    public Categoria update(Long id, Categoria categoriaDto) {
        checkIfExists(id);
        Categoria categoria = new Categoria();
        BeanUtils.copyProperties(categoriaDto, categoria);
        categoria.setId(id);
        return repository.save(categoria);
    }

    public Boolean delete(Long id) {
        checkIfExists(id);
        repository.deleteById(id);
        return true;
    }


    //Métodos Auxiliares

    //Mapper DTO
//    public CategoriaDto toCategoriaDto(Categoria categoria) {
//        return modelMapper.map(categoria, CategoriaDto.class);
//    }
//
//    public PratoDto toPratoDto(Prato prato) {
//        return modelMapper.map(prato, PratoDto.class);
//    }
//
//    //Adicionar Link
//    public PratoDto addSelfLink(PratoDto pratoDto) {
//        return pratoDto.add(linkTo(methodOn(PratoController.class).findById(pratoDto.getId())).withSelfRel());
//    }
//
//    public CategoriaDto addSelfLink(CategoriaDto categoriaDto) {
//        return categoriaDto.add(linkTo(methodOn(CategoriaController.class).findById(categoriaDto.getId())).withSelfRel());
//    }
//
//    public CategoriaDto addAllLink(CategoriaDto categoriaDto) {
//        return categoriaDto.add(linkTo(methodOn(CategoriaController.class).findAll()).withRel("Lista de todas categorias:"));
//    }
//
//    //Converter Lista para DTO
//    public List<PratoDto> toListPratosDto(List<Prato> listaDePratos) {
//        //Transforma em DTO e adiciona selfLink
//        return listaDePratos.stream().map(prato -> toPratoDto(prato)).map(pratoDto -> addSelfLink(pratoDto)).collect(Collectors.toList());
//    }
    
    public Boolean checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        return true;
    }
}
