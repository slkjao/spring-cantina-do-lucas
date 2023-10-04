package com.cantina.cantina.repositories;

import com.cantina.cantina.entities.Mesa;
import com.cantina.cantina.entities.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
