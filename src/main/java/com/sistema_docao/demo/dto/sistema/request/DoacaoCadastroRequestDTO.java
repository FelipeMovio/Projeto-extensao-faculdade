package com.sistema_docao.demo.dto.sistema.request;

import java.util.List;

public record DoacaoCadastroRequestDTO(
        List<DoacaoItemRequestDTO> itens
) {
}
