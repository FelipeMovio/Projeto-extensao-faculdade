package com.sistema_docao.demo.repository;

import com.sistema_docao.demo.entity.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador,Long> {
}
