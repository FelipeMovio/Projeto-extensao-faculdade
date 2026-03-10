package com.sistema_docao.demo.dto.response;

import com.sistema_docao.demo.dto.DoacaoItemResumoDTO;
import com.sistema_docao.demo.entity.Doacao;
import com.sistema_docao.demo.entity.DoacaoItem;
import com.sistema_docao.demo.entity.Doador;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public record DoacaoReadResponseDTO(
        Long id,
        LocalDate dataCriacao,
        String nome,
        List<DoacaoItemResumoDTO> itens
) {
    public DoacaoReadResponseDTO(Doacao doacao) {
        this(
                doacao.getId(),
                doacao.getDataCriacao(),
                doacao.getDoador().getNome(),
                doacao.getItens()
                        .stream()
                        .map(DoacaoItemResumoDTO :: new)
                        .collect(Collectors.toList())
        );
    }
}
