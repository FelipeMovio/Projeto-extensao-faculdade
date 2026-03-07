package com.sistema_docao.demo.repository;

import com.sistema_docao.demo.entity.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
}
