package br.com.devmedia.curso.service;

import br.com.devmedia.curso.Exception.IdNaoValidoServiceException;
import br.com.devmedia.curso.dao.CursoDao;
import br.com.devmedia.curso.domain.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


import java.util.Date;
import java.util.List;

/**
 * A anotação @{@link Service} marca a classe como um bean gerenciado pelo spring (Bena de negocio)
 * A anotação @{@link Transactional} vai informar para o spring que deve gerenciar as transacoes dos metodos que estao nessa classe
 * essa anotação contem um parametro read-only setado como false, assim sendo a transação será tanto para leitura como para escrita
 */
@Service
@Transactional
public class CursoServiceImpl implements CursoService{

    @Autowired
    private CursoDao dao;

    @Override
    public void save(Curso curso) {
        dao.save(curso);
    }

    @Override
    public void update(Long id, Curso curso) {
        curso.setId(idValido(id));
        dao.findById(id);
        dao.update(curso);
    }

    @Override
    public void delete(Long id) {
        dao.delete(idValido(id));
    }

    /**
     * A anotação @{@link Transactional} com o parametro readoly como true, passa para o spring que esse metodo é apenas de leitura
     * ignorando a transacao para ele
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public Curso findById(Long id) {

        return dao.findById(idValido(id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Curso> findAll() {
        return dao.findAll();
    }

    @Override
    public Curso updateDataInicio(Long id, Date dataInicio) {
        Curso curso = dao.findById(idValido(id));
        curso.setDataInicio(dataInicio);
        return curso;
    }

    /*
     * Metodo responsável por fazer a validação do id antes de iniciar o processamento de pesquisa no banco
     * de dados, caso o id seja inválido <= 0 já lança uma exceção de IdInvalidoException
     */
    private Long idValido(Long id){
        if(id == null || id <= 0){
            throw new IdNaoValidoServiceException("Valor do campo id está inválido. O valor deve ser maior que zero.");
        }
        return id;
    }
}
