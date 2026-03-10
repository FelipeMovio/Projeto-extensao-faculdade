package com.sistema_docao.demo.repository;

import com.sistema_docao.demo.entity.Doacao;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao, Long> {

    @EntityGraph(attributePaths = {
            "doador",
            "itens",
            "itens.item"
    })
    List<Doacao> findAll();
}
