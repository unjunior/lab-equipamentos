package br.com.empresa.lab.service.excecoes;

public class RecursoNaoEncontradoException extends RuntimeException{
    public RecursoNaoEncontradoException(String msg){
        super(msg);
    }
}
