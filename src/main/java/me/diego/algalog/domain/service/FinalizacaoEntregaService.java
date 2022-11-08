package me.diego.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FinalizacaoEntregaService {
    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId) {

        Entrega entrega = buscaEntregaService.buscar(entregaId);
        entrega.finalizar();

        entregaRepository.save(entrega);
    }

}
