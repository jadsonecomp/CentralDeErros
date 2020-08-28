package br.com.projetofinal.centraldeerros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recursoNome){

        super("Recurso: " + recursoNome + " não encontrado");

    }

}
