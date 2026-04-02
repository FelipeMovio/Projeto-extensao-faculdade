package com.sistema_docao.demo.dto.sistema.request;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record DoacaoCadastroRequestDTO(

        @NotEmpty(message = "A lista de itens não pode estar vazia")
        List<DoacaoItemRequestDTO> itens
) {
}
