package me.diego.algalog.api.model;

import lombok.Getter;
import lombok.Setter;
import me.diego.algalog.domain.model.StatusEntrega;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {

    private Long id;
    private String nomeCliente;
    private DestinatarioModel destinatarioModel;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime dataFinalizacao;

}
