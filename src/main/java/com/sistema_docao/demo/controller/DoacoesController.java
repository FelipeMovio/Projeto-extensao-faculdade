package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.request.DoacaoCadastroRequestDTO;
import com.sistema_docao.demo.dto.response.DoacaoReadResponseDTO;
import com.sistema_docao.demo.service.DoacoesService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    private final DoacoesService doacoesService;

    public DoacoesController (DoacoesService doacoesService){
        this.doacoesService = doacoesService;
    }

    @PostMapping
    public ResponseEntity<Void> doar(@RequestBody DoacaoCadastroRequestDTO dto){
        doacoesService.registrarDoacao(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<DoacaoReadResponseDTO>> verTodas(Pageable pageable) {

        Page<DoacaoReadResponseDTO> dto = doacoesService.getAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoacaoReadResponseDTO> verUma(@PathVariable Long id) {

        DoacaoReadResponseDTO dto = doacoesService.getOne(id);

        return ResponseEntity.ok(dto);
    }

}
