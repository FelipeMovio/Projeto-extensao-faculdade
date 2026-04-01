package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.sistema.request.DoadorAtualizaRequestDTO;
import com.sistema_docao.demo.dto.sistema.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.dto.sistema.response.DoadorReadResponseDTO;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.repository.DoadorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DoadorService {


    private final DoadorRepository doadorRepository;

    public DoadorService(DoadorRepository doadorRepository) {
        this.doadorRepository = doadorRepository;
    }



    public Page<DoadorReadResponseDTO> getAll(Pageable paginacao){
        return doadorRepository.findAll(paginacao)
                .map(DoadorReadResponseDTO ::new);

    }

    public DoadorReadResponseDTO getOne(Long userId){
        Doador doador = doadorRepository
                .findByUsuarioId(userId)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        return new DoadorReadResponseDTO(doador);


    }

    @Transactional
    public DoadorReadResponseDTO atualizar(Long userId,DoadorAtualizaRequestDTO dto){
        Doador doador = doadorRepository
                .findByUsuarioId(userId)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        if(dto.telefone() != null){
            doador.setTelefone(dto.telefone());
        }

        if(dto.nome() != null){
            doador.getUsuario().setNome(dto.nome());
        }

        doadorRepository.save(doador);

        return new DoadorReadResponseDTO(doador);
    }


    @Transactional
    public void excluir(Long userId){
        Doador doador = doadorRepository
                .findByUsuarioId(userId)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        doadorRepository.delete(doador);
    }

}
