package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Autor;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.List;

public class AutorDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Autor autor) {
        em.persist(autor);
    }

    public void atualizar(@Valid Autor autor) {
        em.merge(autor);
    }

    public Autor findById(Long idAutor) {
        return em.find(Autor.class, idAutor);
    }

    public void remover(Long idAutor) throws ModelException {
        Autor autor = em.getReference(Autor.class, idAutor);
        if (autor == null) {
            throw new ModelException("Autor não encontrado na base de dados!");
        }
        em.remove(autor);
    }

    public List<Autor> findAll() {
        return em.createQuery("SELECT o FROM Autor o").getResultList();
    }

}
