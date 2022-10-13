package br.com.avaliacao.service.exception;

public class DataInicioNaoPermitidaException extends RuntimeException{
    public DataInicioNaoPermitidaException(String message) {
        super(message);
    }
}
