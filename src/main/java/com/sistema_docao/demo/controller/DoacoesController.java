package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.service.DoacoesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doacoes")
public class DoacoesController {

    private final DoacoesService doacoesService;

    public DoacoesController (DoacoesService doacoesService){
        this.doacoesService = doacoesService;
    }

}
