package com.sistema_docao.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(
        name = "itens",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"nome", "tipo"})
        }
)
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private Integer quantidadeEstoque;

    @OneToMany(mappedBy = "item")
    private List<DoacaoItem> doacoes;
}
