package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Emprestimo;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmprestimoService extends AbstractCrudService<Emprestimo> {

    @Inject
    private GenericDao<Emprestimo> dao;

    @Override
    public GenericDao<Emprestimo> getDao() {
        return dao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Emprestimo inserir(Emprestimo emprestimo) throws ModelException {
        try {
            dao.inserir(emprestimo);
            return emprestimo;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Emprestimo atualizar(Emprestimo emprestimo) throws ModelException {
        try {
            dao.atualizar(emprestimo);
            return emprestimo;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idEmprestimo) throws ModelException {
        dao.remover(idEmprestimo);
    }

}
