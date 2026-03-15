package com.sistema_docao.demo.dto.autenticacao.request;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequestDto(@NotEmpty(message = "Email é obrigatorio") String email,
                              @NotEmpty(message = "senha obrigatoria ") String senha) {
}
