package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.sistema.request.ItemCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.ItemReadResponseDTO;
import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {


    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Transactional
    public void registrar(ItemCadastroRequestDTO dto){
        Item item = new Item();

        boolean existe = itemRepository.existsByNomeAndTipo(dto.nome(), dto.tipo());
        if(existe){
            throw new IllegalArgumentException(
                    "Já existe um item com nome '" + dto.nome() + "' e tipo '" + dto.tipo() + "'."
            );
        }

        item.setNome(dto.nome());
        item.setTipo(dto.tipo());
        item.setQuantidadeEstoque(dto.quantidadeEstoque());

        itemRepository.save(item);

    }

    public Page <ItemReadResponseDTO> getAll(Pageable paginacao){
       return itemRepository.findAll(paginacao)
               .map(ItemReadResponseDTO::new);

    }

    public ItemReadResponseDTO getOne(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new  RuntimeException("Item nao encontrado"  + id));

        return new ItemReadResponseDTO(item);

    }

    @Transactional
    public void excluir(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item nao encontrado" + id));

        itemRepository.delete(item);
    }
}
