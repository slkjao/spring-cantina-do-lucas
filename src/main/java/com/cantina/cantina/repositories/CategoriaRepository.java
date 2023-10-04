package com.cantina.cantina.repositories;

import com.cantina.cantina.entities.Categoria;
import com.cantina.cantina.entities.Prato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
