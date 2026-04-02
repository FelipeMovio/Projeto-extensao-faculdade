package com.sistema_docao.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doadores")
public class Doador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Doacao> doacoes;
}
