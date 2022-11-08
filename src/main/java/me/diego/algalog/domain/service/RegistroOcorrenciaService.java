package me.diego.algalog.domain.service;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.model.Ocorrencia;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegistroOcorrenciaService {
    private final BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);
    }
}
