package com.cantina.cantina.services;

import com.cantina.cantina.entities.Mesa;
import com.cantina.cantina.repositories.MesaRepository;
import com.cantina.cantina.repositories.MesaRepository;
import com.cantina.cantina.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    MesaRepository repository;

    public Mesa insert(Mesa MesaDto) {
        Mesa Mesa = new Mesa();
        BeanUtils.copyProperties(MesaDto, Mesa);
        return repository.save(Mesa);
    }

    public List<Mesa> findAll() {
        return repository.findAll();
    }

    public Mesa findById(Long id) {
        checkIfExists(id);
        Optional<Mesa> MesaOptional = repository.findById(id);
        return MesaOptional.get();

    }

    public Mesa update(Long id, Mesa MesaDto) {
        checkIfExists(id);
        Mesa Mesa = new Mesa();
        BeanUtils.copyProperties(MesaDto, Mesa);
        Mesa.setId(id);
        return repository.save(Mesa);
    }

    public Boolean delete(Long id) {
        checkIfExists(id);
        repository.deleteById(id);
        return true;
    }

    //Métodos Auxiliares
//    public MesaDto toMesaDto(Mesa Mesa) {
//        return modelMapper.map(Mesa, MesaDto.class);
//    }
//
//    public MesaDto addSelfLink(MesaDto MesaDto) {
//        return MesaDto.add(linkTo(methodOn(MesaController.class).findById(MesaDto.getId())).withSelfRel());
//    }
//
//    public MesaDto addAllLink(MesaDto MesaDto) {
//        return MesaDto.add(linkTo(methodOn(MesaController.class).findAll()).withRel("Lista de Mesas:"));
//    }

    public Boolean checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        return true;
    }


}
