package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Cliente;
import api.biblioteca.satc.util.ExceptionResponse;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ClienteService extends AbstractCrudService<Cliente> {

    @Inject
    private GenericDao<Cliente> dao;

    @Override
    public GenericDao<Cliente> getDao() {
        return dao;
    }

    public Cliente findById(Long id) {
        return dao.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cliente inserir(Cliente cliente) throws ModelException {
        try {
            dao.inserir(cliente);
            return cliente;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Cliente atualizar(Cliente cliente) throws ModelException {
        try {
            dao.atualizar(cliente);
            return cliente;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idCliente) throws ModelException {
        dao.remover(idCliente);
    }

}
