package me.diego.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.api.assembler.EntregaAssembler;
import me.diego.algalog.api.model.DestinatarioModel;
import me.diego.algalog.api.model.EntregaModel;
import me.diego.algalog.api.model.input.EntregaInput;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.repository.EntregaRepository;
import me.diego.algalog.domain.service.FinalizacaoEntregaService;
import me.diego.algalog.domain.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/entregas")
public class EntregaController {
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final EntregaRepository entregaRepository;
    private final EntregaAssembler entregaAssembler;
    private final FinalizacaoEntregaService finalizacaoEntregaService;

    @GetMapping
    public ResponseEntity<List<EntregaModel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(entregaAssembler.toCollectionModel(entregaRepository.findAll()));
    }

    @GetMapping(path = "/{entregaId}")
    public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
        return entregaRepository.findById(entregaId)
                .map(entrega ->
                        ResponseEntity.status(HttpStatus.OK).body(entregaAssembler.toModel(entrega))
                )
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{entregaId}/finalizacao")
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @PostMapping
    public ResponseEntity<EntregaModel> solicitar(@Valid @RequestBody EntregaInput entregaInput) {
        Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

        return ResponseEntity.status(HttpStatus.CREATED).body(entregaAssembler.toModel(entregaSolicitada));
    }
}
