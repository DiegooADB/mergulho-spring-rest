package me.diego.algalog.api.controller;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.api.assembler.OcorrenciaAssembler;
import me.diego.algalog.api.model.OcorrenciaModel;
import me.diego.algalog.api.model.input.OcorrenciaInput;
import me.diego.algalog.domain.model.Entrega;
import me.diego.algalog.domain.model.Ocorrencia;
import me.diego.algalog.domain.service.BuscaEntregaService;
import me.diego.algalog.domain.service.RegistroOcorrenciaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/entregas/{entregaId}/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {
    private final BuscaEntregaService buscaEntregaService;
    private final RegistroOcorrenciaService registroOcorrenciaService;
    private final OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    public ResponseEntity<OcorrenciaModel> registrar(@PathVariable Long entregaId,
                                    @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrencia = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrenciaAssembler.toModel(ocorrencia));
    }

    @GetMapping
    public ResponseEntity<List<OcorrenciaModel>> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ResponseEntity.status(HttpStatus.OK).body(ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias()));
    }
}
