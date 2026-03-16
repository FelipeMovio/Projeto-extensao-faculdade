package com.sistema_docao.demo.dto.sistema.response;

import com.sistema_docao.demo.dto.sistema.DoacaoResumoDTO;
import com.sistema_docao.demo.entity.Doador;

import java.util.List;

public record DoadorReadResponseDTO(
        Long id,
        String telefone,
        List<DoacaoResumoDTO> doacoes
) {
    public DoadorReadResponseDTO(Doador doador){
        this(
                doador.getId(),
                doador.getTelefone(),
                doador.getDoacoes().stream()
                        .map(DoacaoResumoDTO::new)
                        .toList()
        );
    }
}
