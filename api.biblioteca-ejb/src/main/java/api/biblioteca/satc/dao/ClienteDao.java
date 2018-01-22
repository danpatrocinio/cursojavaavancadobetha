package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Cliente;
import api.biblioteca.satc.model.EntitySelectable;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class ClienteDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Cliente cliente) {
        em.persist(cliente);
    }

    public void atualizar(@Valid Cliente cliente) {
        em.merge(cliente);
    }

    public Cliente findById(Long idCliente) {
        return em.find(Cliente.class, idCliente);
    }

    public void remover(Long idCliente) throws ModelException {
        Cliente cliente = em.getReference(Cliente.class, idCliente);
        if (cliente == null) {
            throw new ModelException("Usuário não encontrado na base de dados!");
        }
        em.remove(cliente);
    }

    public List<Cliente> findAll() {
        return em.createQuery("SELECT o FROM Cliente o").getResultList();
    }

    public List<EntitySelectable> findAllToSelectable() {
        return em.createQuery("SELECT new api.biblioteca.satc.model.EntitySelectable(o.id, o.nome) FROM Cliente o").getResultList();
    }
}
