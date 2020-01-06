package br.com.hbsis.service;

import br.com.hbsis.dao.PessoaRepository;
import br.com.hbsis.entity.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaService (PessoaRepository thePessoaRepository) {
        this.pessoaRepository = thePessoaRepository;
    }

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();
    }

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

    public void save (Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void generateXml(HttpServletResponse response, BigInteger theId) throws Exception {
        Pessoa pessoa = this.findById(theId);
        String xmlPessoa = conversorJsonXml(pessoa);
        System.out.println(">>> valor de xmlPessoa (generateXml): " + xmlPessoa);

        byte[] xmlDoc = xmlPessoa.getBytes();
        response.setContentType("text/xml");
        response.setHeader("Content-Disposition", "attachment; filename=bruxao.xml");

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(xmlDoc);
        outputStream.flush();
        outputStream.close();

    }

    public String conversorJsonXml (Pessoa jsonData) {
        ObjectMapper obj = new ObjectMapper();

        try {
            String jsonStr = obj.writeValueAsString(jsonData);
            System.out.println(">>> Valor de jsonStr(conversorJsonXml: " + jsonStr);
        }
        catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(jsonData);
        JSONObject object = new JSONObject(jsonData);

        //converting json to xml
        String xml_data = XML.toString(object);
        return xml_data;
    }

}
