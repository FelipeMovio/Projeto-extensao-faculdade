package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.dto.sistema.RelatorioItemDTO;
import com.sistema_docao.demo.service.RelatorioService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<RelatorioItemDTO>> gerarRelatorio(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {
        List<RelatorioItemDTO> dtosList = service.gerarRelatorio(inicio, fim);

        return ResponseEntity.ok(dtosList);
    }

    @GetMapping("/mensal/csv")
    public ResponseEntity<String> gerarCSV(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) {

        String csv = service.gerarRelatorioCSV(inicio, fim);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=relatorio.csv")
                .header("Content-Type", "text/csv")
                .body(csv);
    }

    @GetMapping("/mensal/pdf")
    public ResponseEntity<byte[]> gerarPDF(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fim
    ) throws Exception {

        byte[] pdf = service.gerarRelatorioPDF(inicio, fim);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=relatorio.pdf")
                .header("Content-Type", "application/pdf")
                .body(pdf);
    }
}
