package br.com.avaliacao.controller.exception;

import br.com.avaliacao.service.exception.DataInicioNaoPermitidaException;
import br.com.avaliacao.service.exception.IllegalArgumentException;
import br.com.avaliacao.service.exception.PeriodoNaoPermitidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> objectNotFound(IllegalArgumentException ex, HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PeriodoNaoPermitidoException.class)
    public ResponseEntity<StandardError> PeriodoNaoPermitido(PeriodoNaoPermitidoException ex,
                                                             HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), request.getRequestURI() );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(DataInicioNaoPermitidaException.class)
    public ResponseEntity<StandardError> DataInicioNaoPermitida(DataInicioNaoPermitidaException ex,
                                                                HttpServletRequest request) {
        StandardError error = new StandardError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(),
                ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }
}
