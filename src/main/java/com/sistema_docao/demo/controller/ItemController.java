package com.sistema_docao.demo.controller;

import com.sistema_docao.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itens")
public class ItemController {

    @Autowired
    private ItemService itemService;


}
