package api.biblioteca.satc.service;

import api.biblioteca.satc.dao.EmprestimoDao;
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
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static api.biblioteca.satc.model.Emprestimo.PRAZO_ENTREGA_POR_LIVRO;

@Stateless
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmprestimoService extends AbstractCrudService<Emprestimo> {

    @Inject
    private GenericDao<Emprestimo> dao;

    @Inject
    private EmprestimoDao emprestimosDao;

    @Override
    public GenericDao<Emprestimo> getDao() {
        return dao;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Emprestimo inserir(Emprestimo emprestimo) throws ModelException {
        try {
            if (emprestimo.getDataEmprestimo() == null) {
                emprestimo.setDataEmprestimo(new java.util.Date());
            }
            if (emprestimo.getLivros() == null || emprestimo.getLivros().size() < 1) {
                throw new ModelException("Pelo menos um livro deve ser informado!");
            }
            int prazo = PRAZO_ENTREGA_POR_LIVRO * emprestimo.getLivros().size();
            Calendar previsaoEntrega = new GregorianCalendar();
            previsaoEntrega.setTime(emprestimo.getDataEmprestimo());
            previsaoEntrega.set(Calendar.DATE, previsaoEntrega.get(Calendar.DATE) + prazo);
            emprestimo.setDataPrevisaoDevolucao(previsaoEntrega.getTime());
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

    @TransactionAttribute
    public List<Emprestimo> findAllPendentes(){
        return emprestimosDao.findAllPendentes();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void remover(Long idEmprestimo) throws ModelException {
        dao.remover(idEmprestimo);
    }

}
