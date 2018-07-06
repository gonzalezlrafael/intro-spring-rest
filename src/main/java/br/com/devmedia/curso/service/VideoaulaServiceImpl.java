package br.com.devmedia.curso.service;

import br.com.devmedia.curso.dao.VideoaulaDao;
import br.com.devmedia.curso.domain.Videoaula;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VideoaulaServiceImpl implements VideoaulaService {

    @Autowired
    private VideoaulaDao dao;
    @Autowired
    private CursoService cursoService;

    @Override
    public void save(Long idCurso, Videoaula videoaula) {
        videoaula.setCurso(cursoService.findById(idCurso));
        dao.save(videoaula);
    }

    @Override
    public void update(Long idVideoaula, Long idCurso, Videoaula videoaula) {
        videoaula.setId(idVideoaula);
        videoaula.setCurso(findByIdVideoaulaAndIdCurso(idVideoaula, idCurso).getCurso());
        dao.update(videoaula);
    }

    @Override
    public void delete(Long idVideoaula, Long idCurso) {

        dao.delete(findByIdVideoaulaAndIdCurso(idVideoaula, idCurso));
    }

    @Override @Transactional(readOnly = true)
    public Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {

        return dao.findByIdVideoaulaAndIdCurso(idVideoaula, idCurso);
    }

    @Override @Transactional(readOnly = true)
    public List<Videoaula> findAllByCurso(Long idCurso, String fields) {

        return dao.findAllByCurso(idCurso, fields);
    }
}
