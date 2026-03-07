package com.sistema_docao.demo.dto.response;

import com.sistema_docao.demo.dto.DoacaoItemResumoDTO;
import com.sistema_docao.demo.entity.DoacaoItem;
import com.sistema_docao.demo.entity.Item;
import com.sistema_docao.demo.entity.TipoItem;

import java.util.List;

public record ItemReadResponseDTO(
        Long id,
        String nome,
        TipoItem tipo,
        Integer quantidadeEstoque,
        List<DoacaoItemResumoDTO> doacoes
) {
    public ItemReadResponseDTO (Item item){
        this(
                item.getId(),
                item.getNome(),
                item.getTipo(),
                item.getQuantidadeEstoque(),
                item.getDoacoes()
                        .stream()
                        .map(DoacaoItemResumoDTO::new)
                        .toList()
        );
    }
}
