package br.com.empresa.lab.controller;

import br.com.empresa.lab.dto.RespostaErrosExceptionDTO;
import br.com.empresa.lab.service.excecoes.DatabaseException;
import br.com.empresa.lab.service.excecoes.RecursoNaoEncontradoException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExcecaoController {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<RespostaErrosExceptionDTO> recursoNaoEncontrado(
            RecursoNaoEncontradoException e,
            HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        RespostaErrosExceptionDTO erro = new RespostaErrosExceptionDTO(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<RespostaErrosExceptionDTO> database(
            DatabaseException e,
            HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        RespostaErrosExceptionDTO erro = new RespostaErrosExceptionDTO(Instant.now(), status.value(),
                e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(erro);
    }
}
