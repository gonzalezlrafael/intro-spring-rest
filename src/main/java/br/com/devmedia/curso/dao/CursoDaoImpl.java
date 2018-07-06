package br.com.devmedia.curso.dao;

import br.com.devmedia.curso.Exception.NaoExisteDaoException;
import br.com.devmedia.curso.domain.Curso;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * A anotacao @{@link Repository} marca essa classe como repositorio e classe de pesistencia
 */
@Repository
public class CursoDaoImpl implements CursoDao {

    /**
     * Spring injeta instancia de entity manager
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Curso curso) {

        entityManager.persist(curso);
    }

    @Override
    public void update(Curso curso) {

        entityManager.merge(curso);
    }

    @Override
    public void delete(Long id) {
        try{
            entityManager.remove(entityManager.getReference(Curso.class, id));
        }catch (EntityNotFoundException exception){
            throw new NaoExisteDaoException(String.format("Não exite curso para o id= %s",id));
        }
    }

    @Override
    public Curso findById(Long id) {
        Curso curso = entityManager.find(Curso.class, id);
        if (curso == null) {
            throw new NaoExisteDaoException(String.format("Curso não existe para id=%d",id));
        }
        return curso;

    }

    @Override
    public List<Curso> findAll() {
        return entityManager
                .createQuery("select c from Curso c", Curso.class)
                .getResultList();
    }
}
