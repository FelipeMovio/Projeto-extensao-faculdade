package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.RelatorioItemDTO;
import com.sistema_docao.demo.service.RelatorioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/relatorios")
public class RelatorioController {

    private final RelatorioService service;

    public RelatorioController(RelatorioService service) {
        this.service = service;
    }

    @GetMapping("/mensal")
    public List<RelatorioItemDTO> gerarRelatorio(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        return service.gerarRelatorio(inicio, fim);
    }
}
