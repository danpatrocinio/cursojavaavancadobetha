package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Editora;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class EditoraDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Editora editora) {
        em.persist(editora);
    }

    public void atualizar(@Valid Editora editora) {
        em.merge(editora);
    }

    public void remover(Long idEditora) throws ModelException {
        Editora editora = em.getReference(Editora.class, idEditora);
        if (editora == null) {
            throw new ModelException("Editora não encontrada na base de dados!");
        }
        em.remove(editora);
    }

    public List<Editora> findAll() {
        return em.createQuery("SELECT o FROM Editora o").getResultList();
    }

}
