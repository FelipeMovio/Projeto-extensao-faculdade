package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.RelatorioItemDTO;
import com.sistema_docao.demo.entity.Doacao;
import com.sistema_docao.demo.entity.DoacaoItem;
import com.sistema_docao.demo.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    private final DoacaoRepository repository;

    public RelatorioService(DoacaoRepository repository) {
        this.repository = repository;
    }

    public List<RelatorioItemDTO> gerarRelatorio(LocalDate inicio, LocalDate fim) {

        List<Doacao> doacoes = repository.findByDataCriacaoBetween(inicio, fim);

        Map<String, Integer> resumo = new HashMap<>();

        for (Doacao doacao : doacoes) {

            for (DoacaoItem item : doacao.getItens()) {

                String tipo = item.getItem().getTipo().name(); // depende da sua entidade Item
                Integer quantidade = item.getQuantidade();

                resumo.merge(tipo, quantidade, Integer::sum);
            }
        }

        return resumo.entrySet()
                .stream()
                .map(e -> new RelatorioItemDTO(
                        e.getKey(),
                        e.getValue()
                ))
                .toList();
    }

    public String gerarRelatorioCSV(LocalDate inicio, LocalDate fim) {

        List<RelatorioItemDTO> relatorio = gerarRelatorio(inicio, fim);

        StringBuilder csv = new StringBuilder();

        csv.append("Tipo,Quantidade\n");

        for (RelatorioItemDTO item : relatorio) {
            csv.append(item.tipoItem())
                    .append(",")
                    .append(item.quantidadeTotal())
                    .append("\n");
        }

        return csv.toString();
    }
}
