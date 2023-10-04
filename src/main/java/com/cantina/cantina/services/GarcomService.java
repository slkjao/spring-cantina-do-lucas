package com.cantina.cantina.services;

import com.cantina.cantina.controllers.GarcomController;
import com.cantina.cantina.entities.Garcom;
import com.cantina.cantina.repositories.GarcomRepository;
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
public class GarcomService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    GarcomRepository repository;

    public Garcom insert(Garcom garcom) {

        return repository.save(garcom);
    }

    public List<Garcom> findAll() {
        return repository.findAll();
    }

    public Garcom findById(Long id) {
        checkIfExists(id);
        Optional<Garcom> garcomOptional = repository.findById(id);
        return garcomOptional.get();

    }

    public Garcom update(Long id, Garcom garcomDto) {
        checkIfExists(id);
        Garcom garcom = new Garcom();
        BeanUtils.copyProperties(garcomDto, garcom);
        garcom.setId(id);
        return repository.save(garcom);
    }

    public Boolean delete(Long id) {
        checkIfExists(id);
        repository.deleteById(id);
        return true;
    }

    //Métodos Auxiliares
//    public GarcomDto toGarcomDto(Garcom garcom) {
//        return modelMapper.map(garcom, GarcomDto.class);
//    }
//
//    public GarcomDto addSelfLink(GarcomDto garcomDto) {
//        return garcomDto.add(linkTo(methodOn(GarcomController.class).findById(garcomDto.getId())).withSelfRel());
//    }
//
//    public GarcomDto addAllLink(GarcomDto garcomDto) {
//        return garcomDto.add(linkTo(methodOn(GarcomController.class).findAll()).withRel("Lista de Garcons:"));
//    }

    public Boolean checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        return true;
    }


}
