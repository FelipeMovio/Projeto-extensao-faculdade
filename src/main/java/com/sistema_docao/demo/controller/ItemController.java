package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.request.ItemCadastroRequestDTO;
import com.sistema_docao.demo.dto.response.ItemReadResponseDTO;
import com.sistema_docao.demo.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid ItemCadastroRequestDTO dto){
        itemService.registrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<ItemReadResponseDTO>> verTodos(Pageable pageable){
        Page<ItemReadResponseDTO> dto = itemService.getAll(pageable);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemReadResponseDTO> verUm (@PathVariable Long id){
        ItemReadResponseDTO dto = itemService.getOne(id);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        itemService.excluir(id);

        return ResponseEntity.noContent().build();
    }

}
