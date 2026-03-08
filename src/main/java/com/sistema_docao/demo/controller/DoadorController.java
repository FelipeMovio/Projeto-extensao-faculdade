package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.dto.response.DoadorReadResponseDTO;
import com.sistema_docao.demo.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doadores")
public class DoadorController {

    @Autowired
    private DoadorService doadorService;


    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody DoadorCadastroRequestDTO dto){
       doadorService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<DoadorReadResponseDTO>> verTodos(Pageable pageable){
        Page<DoadorReadResponseDTO> dto = doadorService.getAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<DoadorReadResponseDTO> verUm(@PathVariable Long id ){
        DoadorReadResponseDTO dto = doadorService.getOne(id);

        return ResponseEntity.ok(dto);
    }
}
