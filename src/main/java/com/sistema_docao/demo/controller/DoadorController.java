package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.config.JWTUserData;
import com.sistema_docao.demo.dto.sistema.request.DoadorAtualizaRequestDTO;
import com.sistema_docao.demo.dto.sistema.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.DoadorReadResponseDTO;
import com.sistema_docao.demo.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doadores")
public class DoadorController {


    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<DoadorReadResponseDTO>> verTodos(Pageable pageable){
        Page<DoadorReadResponseDTO> dto = doadorService.getAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/me")
    public  ResponseEntity<DoadorReadResponseDTO> verUm(@AuthenticationPrincipal JWTUserData user){
        DoadorReadResponseDTO dto = doadorService.getOne(user.userId());

        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/me")
    public ResponseEntity<DoadorReadResponseDTO> editar(@AuthenticationPrincipal JWTUserData user, @RequestBody @Valid DoadorAtualizaRequestDTO dto ){
        DoadorReadResponseDTO readResponseDTO = doadorService.atualizar(user.userId(), dto);

        return ResponseEntity.ok(readResponseDTO);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> excluir(@AuthenticationPrincipal JWTUserData user){
        doadorService.excluir(user.userId());

        return ResponseEntity.noContent().build();
    }
}
