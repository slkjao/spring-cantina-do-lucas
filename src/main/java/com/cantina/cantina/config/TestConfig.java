package com.cantina.cantina.config;

import com.cantina.cantina.entities.*;
import com.cantina.cantina.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    PratoRepository pratoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    GarcomRepository garcomRepository;

    @Autowired
    MesaRepository mesaRepository;

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public void run(String... args) throws Exception {
        Prato p1 = new Prato(null, "Bolinho", "12 bolinhos", BigDecimal.valueOf(29.9));
        Prato p2 = new Prato(null, "Chucrutes", "12 chucrutes", BigDecimal.valueOf(22.9));
        Prato p3 = new Prato(null, "Arroz a Moda", "arroz com frango tomate e ervilha", BigDecimal.valueOf(19.9));

        Categoria c1 = new Categoria(null, "Prato Principal");
        Categoria c2 = new Categoria(null, "Entrada");
        categoriaRepository.saveAll(Arrays.asList(c1,c2));

        p1.getCategorias().add(c1);
        p2.getCategorias().add(c2);
        p3.getCategorias().add(c1);
        pratoRepository.saveAll(Arrays.asList(p1,p2,p3));

        LocalDateTime dateTime = LocalDateTime.of(2023, 8, 21, 12, 0, 0);
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        LocalDateTime dateTime2 = LocalDateTime.of(2023, 8, 21, 15, 0, 0);
        Instant instant2 = dateTime2.toInstant(ZoneOffset.UTC);

        Instant horaEntrada = instant;
        Instant horaSaida = instant2;

        Garcom g1 = new Garcom(null, "Nunes");
        Garcom g2 = new Garcom(null, "Cesar");
        Garcom g3 = new Garcom(null, "Rivelino");
        Garcom g4 = new Garcom(null, "Dino");
        Mesa m1 = new Mesa(null,null,null);
        mesaRepository.save(m1);

        garcomRepository.saveAll(Arrays.asList(g1,g2,g3,g4));

        Pedido pe1 = new Pedido(null, "teste", Instant.now(), g1, m1);
        Pedido pe2 = new Pedido(null, "teste2", Instant.now(), g1, m1);
        pe1.getListaDePratos().put(p2,5);
        pe1.getListaDePratos().put(p1,4);
        pe2.getListaDePratos().put(p2,1);
        m1.setPedido(pe1,pe2);

        pedidoRepository.saveAll(Arrays.asList(pe1,pe2));

        m1.mostrarPedidos();
        m1.mudarQnt(1,p2,3);
        m1.mostrarPedidos();


    }

}
