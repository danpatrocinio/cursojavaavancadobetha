package api.biblioteca.satc.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "genero")
public class Genero implements Serializable, api.biblioteca.satc.model.Entity {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id_genero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "A descrição do gênero não pode ser nula")
    @Column(name = "descricao", length = 80, nullable = false)
    @Size(min = 4, max = 80, message = "A descrição do gênero deve ter no mínimo 4 caracteres e no máximo 80")
    private String descricao;

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genero genero = (Genero) o;

        if (id != null ? !id.equals(genero.id) : genero.id != null) return false;
        return descricao != null ? descricao.equals(genero.descricao) : genero.descricao == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        return 31 * result + (descricao != null ? descricao.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "Genero{" +
                "id=" + id +
                ", descricao=\"" + descricao + "\"" +
                '}';
    }
}
