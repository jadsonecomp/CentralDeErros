package br.com.projetofinal.centraldeerros.advice;

import br.com.projetofinal.centraldeerros.exceptions.RecursoNaoEncontradoException;
import br.com.projetofinal.centraldeerros.exceptions.RespostaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RespostaCustomizadaEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public final ResponseEntity<RespostaException> handleNotFoundException(RecursoNaoEncontradoException ex, WebRequest request) {
        RespostaException exceptionResponse = new RespostaException(new Date(),
                                                                    ex.getMessage(),
                                                                    request.getDescription(false),
                                                                    HttpStatus.NOT_FOUND.value(),
                                                                    HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<RespostaException>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
