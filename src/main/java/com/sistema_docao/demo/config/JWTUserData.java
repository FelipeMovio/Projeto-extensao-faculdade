package com.sistema_docao.demo.config;

import lombok.Builder;

import java.util.List;

@Builder
public record JWTUserData(Long userId, String email, List<String> roles ) {
}
