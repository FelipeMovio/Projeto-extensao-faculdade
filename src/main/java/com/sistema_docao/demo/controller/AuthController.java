package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.config.TokenConfig;
import com.sistema_docao.demo.dto.autenticacao.request.AdminCreateUserRequestDto;
import com.sistema_docao.demo.dto.autenticacao.request.LoginRequestDto;
import com.sistema_docao.demo.dto.autenticacao.request.RegisterRequestDto;
import com.sistema_docao.demo.dto.autenticacao.response.LoginResponseDto;
import com.sistema_docao.demo.dto.autenticacao.response.RegisterResponseDto;
import com.sistema_docao.demo.entity.Usuario;
import com.sistema_docao.demo.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;


    private final AuthenticationManager authenticationManager;


    private final TokenConfig tokenConfig;

    public AuthController(UsuarioService service, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
        RegisterResponseDto user = service.register(dto);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegisterResponseDto> createAdmin(
            @RequestBody @Valid AdminCreateUserRequestDto dto) {

        return ResponseEntity.status(201).body(service.createAdmin(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.senha())
        );
        Usuario user = (Usuario) auth.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}
