package api.biblioteca.satc.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "editora")
public class Editora implements Serializable, api.biblioteca.satc.model.Entity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_editora")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O nome da editora não pode ser nulo")
    @Column(name = "nome", length = 80, nullable = false)
    @Size(min = 3, max = 80, message = "O nome da editora deve ter no mínimo 3 caracteres e no máximo 80")
    private String nome;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Editora editora = (Editora) o;

        if (id != null ? !id.equals(editora.id) : editora.id != null) return false;
        return nome != null ? nome.equals(editora.nome) : editora.nome == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Editora{" +
                "id=" + id +
                ", nome=\"" + nome + "\"" +
                '}';
    }
}
