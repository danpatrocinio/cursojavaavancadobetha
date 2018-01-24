package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.dao.LivroDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.model.Livro;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class LivroService extends AbstractCrudService<Livro> {

    @Inject
    private GenericDao<Livro> dao;

    @Inject
    private LivroDao livroDao;

    @Override
    public GenericDao<Livro> getDao() {
        return dao;
    }

    public List<EntitySelectable> findAllToSelectable() {
        return livroDao.findAllToSelectable();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Livro inserir(Livro livro) throws ModelException {
        try {
            dao.inserir(livro);
            return livro;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Livro atualizar(Livro livro) throws ModelException {
        try {
            dao.atualizar(livro);
            return livro;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idLivro) throws ModelException {
        dao.remover(idLivro);
    }

    public List<Livro> findByTitulo(String titulo) {
        return livroDao.findAllByTitulo(titulo);
    }

    public List<Livro> findByAutor(Long idAutor) {
        return livroDao.findAllByProperty("idAutor", idAutor);
    }

    public List<Livro> findByEditora(Long idEditora) {
        return livroDao.findAllByProperty("idEditora", idEditora);
    }

    public List<Livro> findByGenero(Long idGenero) {
        return livroDao.findAllByProperty("idGenero", idGenero);
    }

    public List<Livro> findByISBN(String isbn) {
        return livroDao.findAllByProperty("isbn", isbn);
    }

}
