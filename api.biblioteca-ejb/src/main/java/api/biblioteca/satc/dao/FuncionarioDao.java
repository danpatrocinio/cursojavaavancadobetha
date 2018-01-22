package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.model.Funcionario;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class FuncionarioDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Funcionario funcionario) {
        em.persist(funcionario);
    }

    public void atualizar(@Valid Funcionario funcionario) {
        em.merge(funcionario);
    }

    public Funcionario findById(Long idFuncionario) {
        return em.find(Funcionario.class, idFuncionario);
    }

    public void remover(Long idFuncionario) throws ModelException {
        Funcionario funcionario = em.getReference(Funcionario.class, idFuncionario);
        if (funcionario == null) {
            throw new ModelException("Funcionário não encontrado na base de dados!");
        }
        em.remove(funcionario);
    }

    public List<Funcionario> findAll() {
        return em.createQuery("SELECT o FROM Funcionario o").getResultList();
    }

    public List<EntitySelectable> findAllToSelectable() {
        return em.createQuery("SELECT new api.biblioteca.satc.model.EntitySelectable(o.id, o.nome) FROM Funcionario o").getResultList();
    }
}
