package br.com.devmedia.curso.dao;

import br.com.devmedia.curso.Exception.NaoExisteDaoException;
import br.com.devmedia.curso.domain.Videoaula;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class VideoaulaDaoImpl implements VideoaulaDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Videoaula videoaula) {

        entityManager.persist(videoaula);
    }

    @Override
    public void update(Videoaula videoaula) {

        entityManager.merge(videoaula);
    }

    @Override
    public void delete(Videoaula videoaula) {

        entityManager.remove(videoaula);
    }

    @Override
    public Videoaula findByIdVideoaulaAndIdCurso(Long idVideoaula, Long idCurso) {
        String query = "select v from Videoaula v where v.id = ?1 and v.curso.id = ?2";
        try {
            return entityManager
                    .createQuery(query, Videoaula.class)
                    .setParameter(1, idVideoaula)
                    .setParameter(2, idCurso)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new NaoExisteDaoException("Videoaula id = "+ idVideoaula +
                    " nÃ£o encontrada para Curso id = " + idCurso + ".");
        }
    }

    @Override
    public List<Videoaula> findAllByCurso(Long idCurso, String fields) {
        String select = fields.equals("curso")
                ? "select v"
                : "select new Videoaula(v.id, v.titulo, v.descricao, v.numero)";

        return entityManager
                .createQuery(select + " from Videoaula v where v.curso.id = ?1", Videoaula.class)
                .setParameter(1, idCurso)
                .getResultList();
    }
}
