package br.com.projetofinal.centraldeerros.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroInternoServidorException extends RuntimeException{

    public ErroInternoServidorException(String menssagemErro){

        super(menssagemErro);

    }

}
