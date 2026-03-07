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
import java.util.List;

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

        for (var itemDTO : dto.itens()) {


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
