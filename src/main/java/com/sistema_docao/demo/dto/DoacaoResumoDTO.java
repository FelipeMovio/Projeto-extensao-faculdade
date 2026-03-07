package com.sistema_docao.demo.dto;

import com.sistema_docao.demo.dto.response.DoadorMiniResponseDTO;

import com.sistema_docao.demo.entity.Doacao;


import java.time.LocalDate;
import java.util.List;

public record DoacaoResumoDTO(
         Long id,

        LocalDate dataCriacao,

        DoadorMiniResponseDTO doador,

        List<DoacaoItemResumoDTO> itens
) {
    public DoacaoResumoDTO(Doacao doacao) {
        this(
                doacao.getId(),
                doacao.getDataCriacao(),
                new DoadorMiniResponseDTO(doacao.getDoador()),
                doacao.getItens().stream()
                        .map(DoacaoItemResumoDTO::new)
                        .toList()
        );
    }
}
