package me.diego.algalog.api.assembler;

import lombok.RequiredArgsConstructor;
import me.diego.algalog.api.model.OcorrenciaModel;
import me.diego.algalog.domain.model.Ocorrencia;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OcorrenciaAssembler {
    private final ModelMapper modelMapper;

    public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaModel.class);
    }

    public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrenciaList) {
        return ocorrenciaList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
