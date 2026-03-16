package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.autenticacao.request.AdminCreateUserRequestDto;
import com.sistema_docao.demo.dto.autenticacao.request.RegisterRequestDto;
import com.sistema_docao.demo.dto.autenticacao.response.RegisterResponseDto;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.entity.Role;
import com.sistema_docao.demo.entity.Usuario;
import com.sistema_docao.demo.repository.DoadorRepository;
import com.sistema_docao.demo.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UsuarioService {

    private final DoadorRepository doadorRepository;

    private final UsuarioRepository usuarioRepository;


    private final PasswordEncoder passwordEncoder;


    public UsuarioService(DoadorRepository doadorRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.doadorRepository = doadorRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto) {

        Usuario user = Usuario.builder()
                .nome(dto.nome())
                .email(dto.email())
                .senha(passwordEncoder.encode(dto.senha()))
                .roles(Set.of(Role.ROLE_DOADOR))
                .build();

        usuarioRepository.save(user);

        Doador doador = new Doador();
        doador.setUsuario(user);
        doador.setTelefone(dto.telefone());

        doadorRepository.save(doador);

        return new RegisterResponseDto(user.getNome(), user.getEmail());
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

        Doador doador = new Doador();
        doador.setUsuario(admin);
        doador.setTelefone(dto.telefone());

        doadorRepository.save(doador);

        return new RegisterResponseDto(saved.getNome(), saved.getEmail());
    }




}

