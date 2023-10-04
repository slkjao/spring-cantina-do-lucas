package com.cantina.cantina.repositories;

import com.cantina.cantina.entities.Mesa;
import com.cantina.cantina.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
