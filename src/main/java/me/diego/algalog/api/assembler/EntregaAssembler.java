package me.diego.algalog.api.assembler;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.api.model.EntregaModel;
import me.diego.algalog.api.model.input.EntregaInput;
import me.diego.algalog.domain.model.Entrega;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntregaAssembler {
    private final ModelMapper modelMapper;

    public EntregaModel toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaModel.class);
    }

    public List<EntregaModel> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInput entregaInput) {
        return modelMapper.map(entregaInput, Entrega.class);
    }
}
