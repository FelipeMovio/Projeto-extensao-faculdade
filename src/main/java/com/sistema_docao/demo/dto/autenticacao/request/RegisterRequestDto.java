package com.sistema_docao.demo.dto.autenticacao.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RegisterRequestDto(

        @Email(message = "Email inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,

        @NotBlank(message = "Senha obrigatória")
        String senha,

        @NotBlank(message = "Nome obrigatório")
        String nome,

                @NotBlank(message = "Telefone é obrigatório")
        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$",
                message = "Telefone inválido. Use formato (xx) xxxxx-xxxx"
        )
                String telefone
) {}
