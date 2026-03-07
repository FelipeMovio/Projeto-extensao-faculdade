package com.sistema_docao.demo.dto.response;

import com.sistema_docao.demo.dto.DoacaoResumoDTO;
import com.sistema_docao.demo.entity.Doador;

import java.util.List;

public record DoadorReadResponseDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        List<DoacaoResumoDTO> doacoes
) {
    public DoadorReadResponseDTO(Doador doador){
        this(
                doador.getId(),
                doador.getNome(),
                doador.getEmail(),
                doador.getTelefone(),
                doador.getDoacoes().stream()
                        .map(DoacaoResumoDTO::new)
                        .toList()
        );
    }
}
