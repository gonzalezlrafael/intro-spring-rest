package br.com.devmedia.curso.dao;

import br.com.devmedia.curso.domain.Videoaula;

import java.util.List;

public interface VideoaulaDao {

    void save(Videoaula videoaula);

    void update(Videoaula videoaula);

    void delete(Videoaula videoaula);

    Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso);

    List<Videoaula> findAllByCurso(Long idCurso, String fields);
}
