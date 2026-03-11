package com.sistema_docao.demo.service;

import com.sistema_docao.demo.entity.Doacao;
import com.sistema_docao.demo.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    public Map<String, Integer> gerarResumo(LocalDate inicio, LocalDate fim) {

        List<Doacao> doacoes = doacaoRepository.findByDataDoacaoBetween(inicio, fim);

        Map<String, Integer> resumo = new HashMap<>();

        for (Doacao d : doacoes) {
            resumo.merge(d.getTipoItem(), d.getQuantidade(), Integer::sum);
        }

        return resumo;
    }
}
