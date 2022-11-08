package me.diego.algalog.domain.service;

import me.diego.algalog.domain.exception.NegocioException;
import me.diego.algalog.domain.model.Cliente;
import me.diego.algalog.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CatalogoClienteService {
    private final ClienteRepository clienteRepository;

    public CatalogoClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {

        System.out.println(cliente);

        boolean isEquals = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteFound -> !clienteFound.equals(cliente));


        if (isEquals)
            throw new NegocioException("Já existe um cliente cadastrado com esse email");

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
