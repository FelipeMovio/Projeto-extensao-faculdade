package com.sistema_docao.demo.dto.sistema.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoadorAtualizaRequestDTO(

        String nome,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido. Use formato (xx) xxxxx-xxxx"
        )
        String telefone
) {
}
