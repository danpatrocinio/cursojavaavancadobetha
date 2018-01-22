package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.dao.GeneroDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Genero;
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
public class GeneroService extends AbstractCrudService<Genero> {

    @Inject
    private GenericDao<Genero> dao;

    @Override
    public GenericDao<Genero> getDao() {
        return dao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Genero inserir(Genero genero) throws ModelException {
        try {
            dao.inserir(genero);
            return genero;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Genero atualizar(Genero genero) throws ModelException {
        try {
            dao.atualizar(genero);
            return genero;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idGenero) throws ModelException {
        dao.remover(idGenero);
    }

}
