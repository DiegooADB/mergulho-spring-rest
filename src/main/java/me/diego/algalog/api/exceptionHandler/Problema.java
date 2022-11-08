package me.diego.algalog.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problema {
    public Problema(Integer status, OffsetDateTime dataHora, String title, List<Campo> campos) {
        this.status = status;
        this.dataHora = dataHora;
        this.title = title;
        this.campos = campos;
    }

    private Integer status;
    private OffsetDateTime dataHora;
    private String title;
    private List<Campo> campos;

    @Getter
    public static class Campo {
        public Campo(String nome, String messagem) {
            this.nome = nome;
            this.messagem = messagem;
        }

        private String nome;
        private String messagem;
    }
}
