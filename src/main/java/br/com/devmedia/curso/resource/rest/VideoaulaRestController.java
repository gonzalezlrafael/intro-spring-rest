package br.com.devmedia.curso.resource.rest;


import br.com.devmedia.curso.domain.Videoaula;
import br.com.devmedia.curso.service.VideoaulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Anotação @{@link RestController} transforma a classe em um controller rest
 */
@RestController
@RequestMapping(
        value = "/cursos/{idCusro}/videoaulas",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class VideoaulaRestController {

    @Autowired
    private VideoaulaService service;

    @DeleteMapping("/{idVideoaula}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("idCurso") Long idCurso, @PathVariable("idVideoaula") Long idVideoaula) {

        service.delete(idVideoaula, idCurso);
    }

    @PutMapping("/{idVideoaula}")
    @ResponseStatus(HttpStatus.OK)
    public Videoaula editar(@PathVariable("idCurso") Long idCurso,
                            @PathVariable("idVideoaula") Long idVideoaula, @RequestBody Videoaula videoaula) {

        service.update(idVideoaula, idCurso, videoaula);
        return videoaula;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> salvar(@PathVariable("idCurso") Long idCurso, @RequestBody Videoaula videoaula) {

        service.save(idCurso, videoaula);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(videoaula.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Videoaula> listar(@PathVariable("idCurso") Long idCurso,
                                  @RequestParam(name = "fields", required = false, defaultValue = "") String fields) {

        return service.findAllByCurso(idCurso, fields);
    }

    @GetMapping("/{idVideoaula}")
    @ResponseStatus(HttpStatus.OK)
    public Videoaula getVideoaula(@PathVariable("idCurso") Long idCurso,
                                  @PathVariable("idVideoaula") Long idVideoaula) {

        return service.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
    }
}
