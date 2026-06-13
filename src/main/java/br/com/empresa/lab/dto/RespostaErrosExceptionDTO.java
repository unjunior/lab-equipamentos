package br.com.empresa.lab.dto;

import java.time.Instant;

public class RespostaErrosExceptionDTO {
    private Instant timestamp;
    private Integer status;
    private String mensagem;
    private String caminho;

    public RespostaErrosExceptionDTO(Instant timestamp, Integer status, String mensagem, String caminho) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
        this.caminho = caminho;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getCaminho() {
        return caminho;
    }
}
