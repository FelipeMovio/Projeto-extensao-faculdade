package com.sistema_docao.demo.service;

import com.sistema_docao.demo.dto.request.DoadorAtualizaRequestDTO;
import com.sistema_docao.demo.dto.request.DoadorCadastroRequestDTO;
import com.sistema_docao.demo.dto.response.DoadorReadResponseDTO;
import com.sistema_docao.demo.entity.Doador;
import com.sistema_docao.demo.repository.DoadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoadorService {

    @Autowired
    private DoadorRepository doadorRepository;

    @Transactional
    public void register(DoadorCadastroRequestDTO dto){

        Doador doador = new Doador();
        doador.setNome(dto.nome());
        doador.setEmail(dto.email());
        doador.setTelefone(dto.telefone());

        doadorRepository.saveAndFlush(doador);
    }


    public Page<DoadorReadResponseDTO> getAll(Pageable paginacao){
        return doadorRepository.findAll(paginacao)
                .map(DoadorReadResponseDTO ::new);

    }

    public DoadorReadResponseDTO getOne(Long id){
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado com id " + id));

        return new DoadorReadResponseDTO(doador);


    }

    @Transactional
    public DoadorReadResponseDTO atualizar(Long id,DoadorAtualizaRequestDTO dto){
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        if(dto.nome() != null){
            doador.setNome(dto.nome());
        }

        if(dto.telefone() != null){
            doador.setTelefone(dto.telefone());
        }

        doadorRepository.save(doador);

        return new DoadorReadResponseDTO(doador);
    }


    @Transactional
    public void excluir(Long id){
        Doador doador = doadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doador não encontrado"));

        doadorRepository.delete(doador);
    }

}
