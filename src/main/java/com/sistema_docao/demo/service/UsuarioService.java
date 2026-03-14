package com.sistema_docao.demo.service;

import com.sistema_docao.demo.entity.Role;
import com.sistema_docao.demo.entity.Usuario;
import com.sistema_docao.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;


    private final PasswordEncoder passwordEncoder;


    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public RegisterResponseDto register(RegisterRequestDto dto) {
        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }


        Usuario newUser = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .roles(Set.of(Role.ROLE_USER))
                .build();

        Usuario saved = usuarioRepository.save(newUser);

        return new RegisterResponseDto(saved.getNome(),saved.getEmail());
    }

    public RegisterResponseDto createAdmin(AdminCreateUserRequestDto dto) {

        if (usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado!");
        }

        Usuario admin = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .roles(Set.of(Role.ROLE_ADMIN))
                .build();

        Usuario saved = usuarioRepository.save(admin);

        return new RegisterResponseDto(saved.getNome(), saved.getEmail());
    }




}

