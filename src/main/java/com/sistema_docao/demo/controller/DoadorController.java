package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.sistema.request.DoadorAtualizaRequestDTO;
import com.sistema_docao.demo.dto.sistema.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.DoadorReadResponseDTO;
import com.sistema_docao.demo.service.DoadorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doadores")
public class DoadorController {


    private final DoadorService doadorService;

    public DoadorController(DoadorService doadorService) {
        this.doadorService = doadorService;
    }

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

    @PatchMapping("/{id}")
    public ResponseEntity<DoadorReadResponseDTO> editar(@PathVariable Long id, @RequestBody @Valid DoadorAtualizaRequestDTO dto ){
        DoadorReadResponseDTO readResponseDTO = doadorService.atualizar(id, dto);

        return ResponseEntity.ok(readResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id ){
        doadorService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
