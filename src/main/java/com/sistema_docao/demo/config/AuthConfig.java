package com.sistema_docao.demo.config;

import com.sistema_docao.demo.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AuthConfig implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    private PasswordEncoder passwordEncoder;

    public AuthConfig(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


}
