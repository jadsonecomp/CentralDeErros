package br.com.projetofinal.centraldeerros.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class RespostaException {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date data;

    private String mensagem;

    private String detalhes;

    private int httpCode;

    private String httpCodeMensagem;

    public RespostaException(Date data, String mensagem, String detalhes, int httpCode, String httpCodeMensagem) {
        super();
        this.httpCode = httpCode;
        this.httpCodeMensagem=httpCodeMensagem;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
        this.data = data;
    }

    public String getHttpCodeMensagem() {
        return httpCodeMensagem;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public Date getData() {
        return data;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getDetalhes() {
        return detalhes;
    }


}
