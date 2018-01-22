package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Editora;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EditoraService extends AbstractCrudService<Editora> {

    @Inject
    private GenericDao<Editora> dao;

    @Override
    public GenericDao<Editora> getDao() {
        return dao;
    }

    public Editora findById(Long id) {
        return dao.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Editora inserir(Editora editora) throws ModelException {
        try {
            dao.inserir(editora);
            return editora;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Editora atualizar(Editora editora) throws ModelException {
        try {
            dao.atualizar(editora);
            return editora;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idEditora) throws ModelException {
        dao.remover(idEditora);
    }

}
