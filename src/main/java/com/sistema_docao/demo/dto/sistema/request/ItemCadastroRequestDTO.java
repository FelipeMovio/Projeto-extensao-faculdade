package com.sistema_docao.demo.dto.sistema.request;

import com.sistema_docao.demo.entity.TipoItem;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCadastroRequestDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotNull(message = "tipo obrigatório")
        TipoItem tipo,

        @NotNull(message = "Quantidade é obrigatório")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidadeEstoque
) {
}
