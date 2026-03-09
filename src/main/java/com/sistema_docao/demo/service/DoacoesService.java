package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.request.DoacaoCadastroRequestDTO;
import com.sistema_docao.demo.entity.Doacao;
import com.sistema_docao.demo.entity.DoacaoItem;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.repository.DoacaoRepository;
import com.sistema_docao.demo.repository.DoadorRepository;
import com.sistema_docao.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoacoesService {

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Transactional
    public void registrarDoacao(DoacaoCadastroRequestDTO dto) {

        Doador doador = doadorRepository.findById(dto.doadorId())
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        Doacao doacao = new Doacao();
        doacao.setDoador(doador);

        List<DoacaoItem> itensDoacao = new ArrayList<>();
        Set<Long> itensProcessados = new HashSet<>();

        for (var itemDTO : dto.itens()) {

            if (itemDTO.quantidade() <= 0) {
                throw new IllegalArgumentException("Quantidade deve ser maior que zero");
            }

            if (!itensProcessados.add(itemDTO.itemId())) {
                throw new IllegalArgumentException("Item duplicado na doação: " + itemDTO.itemId());
            }


            Item item = itemRepository.findById(itemDTO.itemId())
                    .orElseThrow(() -> new RuntimeException("Item não encontrado"));

            DoacaoItem doacaoItem = new DoacaoItem();
            doacaoItem.setDoacao(doacao);
            doacaoItem.setItem(item);
            doacaoItem.setQuantidade(itemDTO.quantidade());

            // atualizar estoque
            item.setQuantidadeEstoque(
                    item.getQuantidadeEstoque() + itemDTO.quantidade()
            );

            itensDoacao.add(doacaoItem);
        }


        doacao.setItens(itensDoacao);

        doacaoRepository.save(doacao);
    }
}
