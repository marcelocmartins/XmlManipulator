package br.com.hbsis.controller;

import br.com.hbsis.entity.Pessoa;
import br.com.hbsis.dao.PessoaRepository;
import br.com.hbsis.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping("/lista")
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }

    @GetMapping("/lista/{theId}")
    public Pessoa findById(@PathVariable BigInteger theId) throws Exception {
       return pessoaService.findById(theId);
    }

    @PostMapping("/inserexml")
    public ResponseEntity inserexml(@RequestBody Pessoa thePessoa) {
        pessoaService.save(thePessoa);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/export/{theId}")
    public void generateXML(HttpServletResponse response, @PathVariable("theId") BigInteger theId) throws Exception{
        pessoaService.generateXml(response, theId);
    }
}
