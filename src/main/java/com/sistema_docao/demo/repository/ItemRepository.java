package com.sistema_docao.demo.repository;

import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.entity.TipoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {

    boolean existsByNomeAndTipo(String nome, TipoItem tipo);
}
