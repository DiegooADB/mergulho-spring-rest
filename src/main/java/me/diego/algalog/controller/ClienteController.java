package me.diego.algalog.controller;

import me.diego.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteController {

    @GetMapping
    public List<Cliente> listar() {
        Cliente cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("João");
        cliente1.setTelefone("123123123");
        cliente1.setEmail("joaodascouves@algaworks.com");


        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Maria");
        cliente2.setTelefone("213123213");
        cliente2.setEmail("mariadasilva@algaworks.com");

        return List.of(cliente1, cliente2);
    }
}
