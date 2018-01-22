package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.FuncionarioDao;
import api.biblioteca.satc.dao.GenericDao;
import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.model.Funcionario;
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
public class FuncionarioService extends  AbstractCrudService<Funcionario> {

    @Inject
    private GenericDao<Funcionario> dao;

    @Override
    public GenericDao<Funcionario> getDao() {
        return dao;
    }

    public List<EntitySelectable> findAllToSelectable() {
        return dao.findAllToSelectableNome();
    }

    public Funcionario findById(Long id) {
        return dao.findById(id);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Funcionario inserir(Funcionario funcionario) throws ModelException {
        try {
            dao.inserir(funcionario);
            return funcionario;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Funcionario atualizar(Funcionario funcionario) throws ModelException {
        try {
            dao.atualizar(funcionario);
            return funcionario;
        } catch (ConstraintViolationException e) {
            ExceptionResponse error = new ExceptionResponse();
            for (ConstraintViolation cve : e.getConstraintViolations()) {
                error.addMessage(cve.getMessageTemplate());
            }
            throw new ModelException(error.getMessage());
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idFuncionario) throws ModelException {
        dao.remover(idFuncionario);
    }

}
