package com.sistema_docao.demo.dto.response;

import com.sistema_docao.demo.entity.Doador;

public record DoadorMiniResponseDTO(Long id, String nome) {
    public DoadorMiniResponseDTO(Doador doador){
        this(doador.getId(), doador.getNome());
    }
}
