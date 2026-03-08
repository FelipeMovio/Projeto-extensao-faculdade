package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.request.ItemCadastroRequestDTO;
import com.sistema_docao.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody ItemCadastroRequestDTO dto){
        itemService.registrar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
