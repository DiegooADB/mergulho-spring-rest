package me.diego.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.domain.exception.EntidadeNaoEncontradaException;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.repository.EntregaRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BuscaEntregaService {
    private final EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }
}
