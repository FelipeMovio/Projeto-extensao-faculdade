package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doador")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;

    public ResponseEntity<Void> cadastrar(@Valid @RequestBody DoadorCadastroRequestDTO dto){
       doadorService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
