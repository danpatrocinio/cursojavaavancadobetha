package api.biblioteca.satc.dao;

import api.biblioteca.satc.exceptions.ModelException;
import api.biblioteca.satc.model.Autor;
import api.biblioteca.satc.model.EntitySelectable;
import api.biblioteca.satc.model.Livro;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LivroDao {

    @Inject
    private EntityManager em;

    public void inserir(@Valid Livro livro) {
        em.persist(livro);
    }

    public void atualizar(@Valid Livro livro) {
        em.merge(livro);
    }

    public Livro findById(Long idLivro) {
        return em.find(Livro.class, idLivro);
    }

    public void remover(Long idLivro) throws ModelException {
        Livro livro = em.getReference(Livro.class, idLivro);
        if (livro == null) {
            throw new ModelException("Livro não encontrado na base de dados!");
        }
        em.remove(livro);
    }

    public List<Livro> findAll() {
        return em.createQuery("SELECT o FROM Livro o").getResultList();
    }

    public List<EntitySelectable> findAllToSelectable() {
        return em.createQuery("SELECT new api.biblioteca.satc.model.EntitySelectable(o.id, o.titulo) FROM Livro o").getResultList();
    }

    public List<Livro> findAllDisponiveisParaEmprestimoToSelectable() {

        List<Object[]> data = em.createNativeQuery("SELECT l.id_livro, l.titulo, (select nome from autor a where a.id_autor = l.id_autor) as nome_autor FROM livro l " +
                "WHERE NOT EXISTS(select el from emprestimo_livro el WHERE el.livros_id_livro = l.id_livro " +
                "  and EXISTS(select e from emprestimo e WHERE el.emprestimo_id_emprestimo = e.id_emprestimo and e.data_devolucao IS NULL))").getResultList();

        List<Livro> livros = new ArrayList<>();
        Livro livro;
        Autor autor;
        for(Object[] row : data) {
            livro = new Livro();
            livro.setId(Long.parseLong(row[0].toString()));
            livro.setTitulo(row[1].toString());
            autor = new Autor();
            autor.setNome(row[2].toString());
            livro.setAutor(autor);
            livros.add(livro);
        }

        return livros;
    }


    public List<Livro> findByDataLancamento(Date dataLancamento) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return findAllByProperty("data_lancamento", sdf.format(dataLancamento));
    }

    public List<Livro> findAllByTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            return Collections.emptyList();
        }
        TypedQuery<Livro> query = em.createQuery("SELECT o FROM Livro o WHERE o.titulo like '%:titulo%'", Livro.class);
        query.setParameter("titulo", titulo);
        return query.getResultList();
    }

    public List<Livro> findAllByProperty(String property, Long id) {
        if (property == null || property.isEmpty() || id == null) {
            return Collections.emptyList();
        }
        TypedQuery<Livro> query = em.createQuery("SELECT o FROM Livro o WHERE o." + property + " = :" + property, Livro.class);
        query.setParameter(property, id);
        return query.getResultList();
    }

    public List<Livro> findAllByProperty(String property, String param) {
        if (property == null || property.isEmpty() || param == null) {
            return Collections.emptyList();
        }
        TypedQuery<Livro> query = em.createQuery("SELECT o FROM Livro o WHERE o." + property + " = :" + property, Livro.class);
        query.setParameter(property, param);
        return query.getResultList();
    }
}
