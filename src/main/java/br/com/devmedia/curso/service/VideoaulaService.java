package br.com.devmedia.curso.service;

import br.com.devmedia.curso.domain.Videoaula;

import java.util.List;

public interface VideoaulaService {
    void save(Long idCurso, Videoaula videoaula);

    void update(Long idVideoaula, Long idCurso, Videoaula videoaula);

    void delete(Long idVideoaula, Long idCurso);

    Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<Videoaula> findAllByCurso(Long idCurso, String fields);
}
