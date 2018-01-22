package api.biblioteca.satc.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "livro")
public class Livro implements Serializable, api.biblioteca.satc.model.Entity {

    private static final long serialVersionUID = -6602970782830899899L;

    @Id
    @Column(name = "id_livro")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "O título do livro não pode ser nulo")
    @Column(name = "titulo", length = 80, nullable = false)
    @Size(min = 1, max = 80)
    private String titulo;
    @Column(name = "data_lancamento")
    private Date dataLancamento;
    @NotNull(message = "O autor deve ser informado")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", foreignKey = @ForeignKey(name = "fk_id_autor"))
    private Autor autor;
    @NotNull(message = "O gênero deve ser informado")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero" , foreignKey = @ForeignKey(name = "fk_id_genero"))
    private Genero genero;
    @NotNull(message = "A editora deve ser informada")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_editora", foreignKey = @ForeignKey(name = "fk_id_editora"))
    private Editora editora;
    private String isbn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setIdGenero(Genero genero) {
        this.genero = genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", titulo=\"" + titulo + "\"" +
                ", dataLancamento=\"" + dataLancamento + "\"" +
                ", autor=" + autor +
                ", genero=" + genero +
                ", isbn=\"" + isbn + "\"" +
                '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dataLancamento == null) ? 0 : dataLancamento.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((autor == null) ? 0 : autor.hashCode());
        result = prime * result + ((genero == null) ? 0 : genero.hashCode());
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return prime * result + ((titulo == null) ? 0 : titulo.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Livro other = (Livro) obj;
        if (dataLancamento == null) {
            if (other.dataLancamento != null)
                return false;
        } else if (!dataLancamento.equals(other.dataLancamento))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (autor == null) {
            if (other.autor != null)
                return false;
        } else if (!autor.equals(other.autor))
            return false;
        if (genero == null) {
            if (other.genero != null)
                return false;
        } else if (!genero.equals(other.genero))
            return false;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        if (titulo == null) {
            if (other.titulo != null)
                return false;
        } else if (!titulo.equals(other.titulo))
            return false;
        return true;
    }

}
