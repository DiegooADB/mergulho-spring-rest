package me.diego.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.domain.model.Cliente;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.model.StatusEntrega;
import me.diego.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class SolicitacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final CatalogoClienteService catalogoClienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {

        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }
}
