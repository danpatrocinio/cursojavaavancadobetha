package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.AutorDao;
import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Autor;
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
public class AutorService extends AbstractCrudService<Autor> {

    @Inject
    private GenericDao<Autor> dao;

    @Override
    public GenericDao<Autor> getDao() {
        return dao;
    }

    public Autor findById(Long id) {
        return dao.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Autor inserir(Autor autor) throws ModelException {
        try {
            dao.inserir(autor);
            return autor;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Autor atualizar(Autor autor) throws ModelException {
        try {
            dao.atualizar(autor);
            return autor;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idAutor) throws ModelException {
        dao.remover(idAutor);
    }

}
