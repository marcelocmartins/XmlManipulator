package br.com.hbsis.controller;

import br.com.hbsis.entity.Pessoa;
import br.com.hbsis.dao.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping("/lista")
    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

    @GetMapping("export/{theId}")
    public Pessoa findById(@PathVariable BigInteger theId) throws Exception {
        Optional<Pessoa> optionalPessoa = pessoaRepository.findById(theId);

        if(optionalPessoa.isPresent()) {
            Pessoa pessoa = optionalPessoa.get();
            return pessoa;
        }
        else {
            throw new Exception("ERRO: pessoa não encontrada");
        }
    }
}
