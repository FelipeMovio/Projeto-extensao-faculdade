package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.config.TokenConfig;
import com.sistema_docao.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UsuarioService service;


    private AuthenticationManager authenticationManager;


    private  TokenConfig tokenConfig;

    public AuthController(UsuarioService service, AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }


}
