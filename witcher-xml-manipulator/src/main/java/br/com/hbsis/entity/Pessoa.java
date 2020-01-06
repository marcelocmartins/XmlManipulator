package br.com.hbsis.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Document(collection = "pessoa")
public class Pessoa {
    @Id
    private BigInteger id;
    private String nome;
    private int idade;
    private String ocupacao;
    private List<Magia> magias;
}
