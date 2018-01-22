package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Genero;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class GeneroDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Genero genero) {
        em.persist(genero);
    }

    public void atualizar(@Valid Genero genero) {
        em.merge(genero);
    }

    public Genero findById(Long idGenero) {
        return em.find(Genero.class, idGenero);
    }

    public void remover(Long idGenero) throws ModelException {
        Genero genero = em.getReference(Genero.class, idGenero);
        if (genero == null) {
            throw new ModelException("Genero não encontrado na base de dados!");
        }
        em.remove(genero);
    }

    public List<Genero> findAll() {
        return em.createQuery("SELECT o FROM Genero o").getResultList();
    }

}
