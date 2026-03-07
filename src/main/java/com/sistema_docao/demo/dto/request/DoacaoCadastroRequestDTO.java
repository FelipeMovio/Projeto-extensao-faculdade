package com.sistema_docao.demo.dto.request;

import java.util.List;

public record DoacaoCadastroRequestDTO(
        Long doadorId,
        List<DoacaoItemRequestDTO> itens
) {
}
