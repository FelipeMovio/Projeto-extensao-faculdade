package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.request.ItemCadastroRequestDTO;
import com.sistema_docao.demo.dto.response.ItemReadResponseDTO;
import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public void registrar(ItemCadastroRequestDTO dto){
        Item item = new Item();

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

    public void excluir(Long id){
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item nao encontrado" + id));

        itemRepository.delete(item);
    }
}
