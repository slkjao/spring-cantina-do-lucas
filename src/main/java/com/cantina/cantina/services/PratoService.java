package com.cantina.cantina.services;

import com.cantina.cantina.controllers.PratoController;
import com.cantina.cantina.entities.Prato;
import com.cantina.cantina.repositories.PratoRepository;
import com.cantina.cantina.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PratoService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PratoRepository repository;

    public Prato insert(Prato pratoDto) {
        Prato prato = new Prato();
        BeanUtils.copyProperties(pratoDto, prato);
        return repository.save(prato);
    }

    public List<Prato> findAll() {
        return repository.findAll();
    }

    public Prato findById(Long id) {
        checkIfExists(id);
        Optional<Prato> pratoOptional = repository.findById(id);
        return pratoOptional.get();

    }

    public Prato update(Long id, Prato pratoDto) {
        checkIfExists(id);
        Prato prato = new Prato();
        BeanUtils.copyProperties(pratoDto, prato);
        prato.setId(id);
        return repository.save(prato);
    }

    public Boolean delete(Long id) {
        checkIfExists(id);
        repository.deleteById(id);
        return true;
    }

    //Métodos Auxiliares
//    public PratoDto toPratoDto(Prato prato) {
//        return modelMapper.map(prato, PratoDto.class);
//    }
//
//    public PratoDto addSelfLink(PratoDto pratoDto) {
//        return pratoDto.add(linkTo(methodOn(PratoController.class).findById(pratoDto.getId())).withSelfRel());
//    }
//
//    public PratoDto addAllLink(PratoDto pratoDto) {
//        return pratoDto.add(linkTo(methodOn(PratoController.class).findAll()).withRel("Lista de Pratos:"));
//    }

    public Boolean checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        return true;
    }


}
