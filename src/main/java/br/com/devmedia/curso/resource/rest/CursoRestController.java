package br.com.devmedia.curso.resource.rest;

import br.com.devmedia.curso.domain.Curso;
import br.com.devmedia.curso.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * Anotação @{@link RestController} transforma a classe em um controller rest
 */
@RestController
@RequestMapping(
        value = "cursos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class CursoRestController {

    @Autowired
    private CursoService service;


    /**
     * Anotacao @{@link GetMapping} determina que o metodo de chamada para esse metodo é via get
     * @return List<Curso>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Curso> listar(){
       return service.findAll();
    }

    /**
     * Anotacao @{@link PostMapping} Determina que vao apenas receber requisições via post
     * cahamdo via metodo post, sempre caira sempre nesse metodo
     *
     * @param curso
     * @return ResponseEntity<Void> retorna um corpo vazio na resposta, e no cabeçalho é inserido a url para localização
     * do objeto que foi inserido. exemplo  /cursos/<id_curso_inserido>
     */
    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody Curso curso){
        service.save(curso);

        //Cria url para devolver no cabecalho da resposta.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(curso.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Curso getCurso(@PathVariable("id") Long id) {

        return service.findById(id);
    }

    /**
     * Metodo Put é utilizado quando existe a necessidade de atualizar o objeto inteiro.
     * @param id
     * @param curso
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public Curso editar(@PathVariable("id") Long id,
                        @RequestBody Curso curso){
        service.update(id,curso);
        return curso;
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{id}")
    public Curso editarDataInicio(@PathVariable("id") Long id, @RequestBody Curso curso){
        return service.updateDataInicio(id,curso.getDataInicio());
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id){
        service.delete(id);
    }


}
