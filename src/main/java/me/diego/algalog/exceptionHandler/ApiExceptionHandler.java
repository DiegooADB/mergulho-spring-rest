package me.diego.algalog.exceptionHandler;

import me.diego.algalog.domain.exception.NegocioException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    public ApiExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<Problema.Campo> campos = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(err -> {

            String nome = ((FieldError) err).getField();
            String messagem = messageSource.getMessage(err, LocaleContextHolder.getLocale());

            campos.add(new Problema.Campo(nome, messagem));
        });

        Problema problema = new Problema(
                status.value(),
                LocalDateTime.now(),
                "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente",
                campos
        );


        return handleExceptionInternal(ex, problema, headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest webRequest) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        Problema problema = new Problema(
                status.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                null
        );

        return handleExceptionInternal(ex, problema, new HttpHeaders(), status, webRequest);
    }
}
