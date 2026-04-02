package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.sistema.request.DoacaoCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.DoacaoReadResponseDTO;
import com.sistema_docao.demo.entity.Doacao;
import com.sistema_docao.demo.entity.DoacaoItem;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.exception.BusinessException;
import com.sistema_docao.demo.exception.NotFoundException;
import com.sistema_docao.demo.repository.DoacaoRepository;
import com.sistema_docao.demo.repository.DoadorRepository;
import com.sistema_docao.demo.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoacoesService {


    private final DoacaoRepository doacaoRepository;


    private final DoadorRepository doadorRepository;


    private final ItemRepository itemRepository;

    public DoacoesService(DoacaoRepository doacaoRepository, DoadorRepository doadorRepository, ItemRepository itemRepository) {
        this.doacaoRepository = doacaoRepository;
        this.doadorRepository = doadorRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    public void registrarDoacao(Long userId,DoacaoCadastroRequestDTO dto) {

        Doador doador = doadorRepository
                .findByUsuarioId(userId)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        Doacao doacao = new Doacao();
        doacao.setDoador(doador);

        List<DoacaoItem> itensDoacao = new ArrayList<>();
        Set<Long> itensProcessados = new HashSet<>();

        for (var itemDTO : dto.itens()) {

            if (itemDTO.quantidade() <= 0) {
                throw new BusinessException("Quantidade deve ser maior que zero");
            }

            if (!itensProcessados.add(itemDTO.itemId())) {
                throw new IllegalArgumentException("Item duplicado na doação: " + itemDTO.itemId());
            }


            Item item = itemRepository.findById(itemDTO.itemId())
                    .orElseThrow(() -> new NotFoundException("Item não encontrado"));

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


    public Page<DoacaoReadResponseDTO> getAll(Pageable pageable) {
        return doacaoRepository.findAll(pageable)
                .map(DoacaoReadResponseDTO::new);
    }

    public DoacaoReadResponseDTO getOne(Long id) {

        Doacao doacao = doacaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doação não encontrada com id " + id));

        return new DoacaoReadResponseDTO(doacao);
    }
}
