package com.cantina.cantina.services;

import com.cantina.cantina.entities.Pedido;
import com.cantina.cantina.repositories.PedidoRepository;
import com.cantina.cantina.repositories.PedidoRepository;
import com.cantina.cantina.services.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PedidoRepository repository;

    public Pedido insert(Pedido PedidoDto) {
        Pedido Pedido = new Pedido();
        BeanUtils.copyProperties(PedidoDto, Pedido);
        return repository.save(Pedido);
    }

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        checkIfExists(id);
        Optional<Pedido> PedidoOptional = repository.findById(id);
        return PedidoOptional.get();

    }

    public Pedido update(Long id, Pedido PedidoDto) {
        checkIfExists(id);
        Pedido Pedido = new Pedido();
        BeanUtils.copyProperties(PedidoDto, Pedido);
        Pedido.setId(id);
        return repository.save(Pedido);
    }

    public Boolean delete(Long id) {
        checkIfExists(id);
        repository.deleteById(id);
        return true;
    }

    //Métodos Auxiliares
//    public PedidoDto toPedidoDto(Pedido Pedido) {
//        return modelMapper.map(Pedido, PedidoDto.class);
//    }
//
//    public PedidoDto addSelfLink(PedidoDto PedidoDto) {
//        return PedidoDto.add(linkTo(methodOn(PedidoController.class).findById(PedidoDto.getId())).withSelfRel());
//    }
//
//    public PedidoDto addAllLink(PedidoDto PedidoDto) {
//        return PedidoDto.add(linkTo(methodOn(PedidoController.class).findAll()).withRel("Lista de Pedidos:"));
//    }

    public Boolean checkIfExists(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
        }
        return true;
    }


}
