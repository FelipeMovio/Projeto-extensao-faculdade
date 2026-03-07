package com.sistema_docao.demo.dto.request;

import jakarta.validation.constraints.Min;

public record DoacaoItemRequestDTO(
        Long itemId,

        @Min(value = 1, message = "Quantidade deve ser maior que 0")
        Integer quantidade
) {
}
