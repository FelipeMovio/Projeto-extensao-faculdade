package com.sistema_docao.demo.service;

import com.sistema_docao.demo.repository.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelatorioService {

    @Autowired
    private DoacaoRepository doacaoRepository;
}
