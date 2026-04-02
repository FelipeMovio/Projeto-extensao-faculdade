package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.config.JWTUserData;
import com.sistema_docao.demo.dto.sistema.request.DoacaoCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.DoacaoReadResponseDTO;
import com.sistema_docao.demo.service.DoacoesService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    private final DoacoesService doacoesService;

    public DoacoesController (DoacoesService doacoesService){
        this.doacoesService = doacoesService;
    }

    @PostMapping
    public ResponseEntity<Void> doar(
            @AuthenticationPrincipal JWTUserData user,
            @Valid @RequestBody DoacaoCadastroRequestDTO dto) {

        doacoesService.registrarDoacao(user.userId(), dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<Page<DoacaoReadResponseDTO>> verTodas(Pageable pageable) {

        Page<DoacaoReadResponseDTO> dto = doacoesService.getAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoReadResponseDTO> verUma(@PathVariable Long id) {

        DoacaoReadResponseDTO dto = doacoesService.getOne(id);

        return ResponseEntity.ok(dto);
    }

}
