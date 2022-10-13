package br.com.avaliacao.service.exception;

public class PeriodoNaoPermitidoException extends RuntimeException{
    public PeriodoNaoPermitidoException(String message) {
        super(message);
    }
}
