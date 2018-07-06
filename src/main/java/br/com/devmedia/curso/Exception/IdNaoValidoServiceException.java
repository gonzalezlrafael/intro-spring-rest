package br.com.devmedia.curso.Exception;

/**
 * Classe responsavel por customizar a mensagem para id nao valido no caso da pesquisa
 * ser feita por um id que não esta cadastrado no banco de dados.
 */
public class IdNaoValidoServiceException extends RuntimeException {

    public IdNaoValidoServiceException(String message) {
        super(message);
    }

}
