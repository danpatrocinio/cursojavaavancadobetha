package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Emprestimo;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class EmprestimoDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Emprestimo emprestimo) {
        em.persist(emprestimo);
    }

    public void atualizar(@Valid Emprestimo emprestimo) {
        em.merge(emprestimo);
    }

    public Emprestimo findById(Long idEmprestimo) {
        return em.find(Emprestimo.class, idEmprestimo);
    }

    public void remover(Long idEmprestimo) throws ModelException {
        Emprestimo emprestimo = em.getReference(Emprestimo.class, idEmprestimo);
        if (emprestimo == null) {
            throw new ModelException("Empréstimo não encontrado na base de dados!");
        }
        em.remove(emprestimo);
    }

    public List<Emprestimo> findAll() {
        return em.createQuery("SELECT o FROM Emprestimo o").getResultList();
    }

    public List<Emprestimo> findAllPendentes() {
        return em.createQuery("SELECT o FROM Emprestimo o WHERE o.dataDevolucao IS NULL ORDER BY o.dataEmprestimo").getResultList();
    }

}
