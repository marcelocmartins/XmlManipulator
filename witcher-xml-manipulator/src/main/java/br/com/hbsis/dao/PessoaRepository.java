package br.com.hbsis.dao;

import br.com.hbsis.entity.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;


public interface PessoaRepository extends MongoRepository<Pessoa, BigInteger> {
    public Pessoa findByNome(String nome);
}
