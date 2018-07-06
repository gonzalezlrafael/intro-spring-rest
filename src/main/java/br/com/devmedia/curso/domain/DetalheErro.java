package br.com.devmedia.curso.domain;

import org.springframework.http.HttpStatus;

import java.util.Objects;

/**
 * Classe responsável por detalher e customizar as menasgens de retorno do WS para o cliente.
 */
public class DetalheErro {

    /*
       Ex.: 404
     */
    private Integer statusCode;

    /*
       Ex.: Not Found
     */
    private String statusMessage;

    /*
       Ex.: Post, Get, Delet
     */
    private String httpMethod;

    /*
       Mensagem customizada(se optar por detalhar mais) ou a propria mensagem de erro
     */
    private String erro;

    /*
       Dealhamento do erro
     */
    private String detalhe;

    /*
       Url que fez a solicitação
     */
    private String path;

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private DetalheErro erro;

        Builder(){
            this.erro = new DetalheErro();
        }

        public Builder addStatusCode(HttpStatus status){
            this.erro.statusCode =status.value();
            this.erro.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method){
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addErro(String erro){
            this.erro.erro = erro;
            return this;
        }

        public Builder addDetalheErro(String detalhe){
            this.erro.detalhe = detalhe;
            return this;
        }

        public Builder addPath(String path){
            this.erro.path = path;
            return this;
        }

        public DetalheErro build(){
            return this.erro;
        }
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getErro() {
        return erro;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public String getPath() {
        return path;
    }

}
