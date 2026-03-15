package com.sistema_docao.demo.dto.sistema;

import com.sistema_docao.demo.entity.DoacaoItem;

public record DoacaoItemResumoDTO(
         Long id,
        Long itemId,
         String nomeItem,
        Integer quantidade
) {
    public DoacaoItemResumoDTO(DoacaoItem doacaoItem) {
        this(
                doacaoItem.getId(),
                doacaoItem.getItem().getId(),
                doacaoItem.getItem().getNome(),
                doacaoItem.getQuantidade()
        );
    }
}
