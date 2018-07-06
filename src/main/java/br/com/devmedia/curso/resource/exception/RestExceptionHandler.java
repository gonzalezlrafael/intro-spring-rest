package br.com.devmedia.curso.resource.exception;


import br.com.devmedia.curso.Exception.IdNaoValidoServiceException;
import br.com.devmedia.curso.Exception.NaoExisteDaoException;
import br.com.devmedia.curso.domain.DetalheErro;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * A anotação @{@link ControllerAdvice} executa a  função de listener (ouvinte) das exceções
 * que ocorrerem no sistema
 *
 *
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NaoExisteDaoException.class)
    public ResponseEntity<Object> entidadeNaoEncontrada(RuntimeException exception, WebRequest request){
        return handleExceptionInternal( exception, DetalheErro.builder()
                        .addDetalheErro("Recurso não encontrado na base de dados")
                        .addErro(exception.getMessage())
                        .addStatusCode(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }

    @ExceptionHandler({NullPointerException.class,IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(
                exception, DetalheErro.builder()
                        .addDetalheErro("Um exceção foi lançada.")
                        .addErro(exception.getMessage())
                        .addStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({IdNaoValidoServiceException.class})
    public ResponseEntity<Object> idInvalido(RuntimeException exception, WebRequest request) {

        return handleExceptionInternal(
                exception, DetalheErro.builder()
                        .addErro(exception.getMessage())
                        .addStatusCode(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private String getPath(WebRequest request) {
        return  ((ServletWebRequest)request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest)request).getRequest().getMethod();
    }

}
