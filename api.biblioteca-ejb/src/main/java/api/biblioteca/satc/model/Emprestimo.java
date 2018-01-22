package api.biblioteca.satc.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "emprestimo")
public class Emprestimo implements Serializable, api.biblioteca.satc.model.Entity{

    @Id
    @Column(name = "id_emprestimo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_devolucao")
    private Date dataDevolucao;

    @NotNull(message = "A data de empréstimo deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_emprestimo")
    private Date dataEmprestimo;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_previsao_devolucao")
    private Date dataPrevisaoDevolucao;

    @NotNull(message = "Para esta ação o funcionário deve ser informado")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcionario", foreignKey = @ForeignKey(name = "fk_id_funcionario"))
    private Funcionario funcionario;

    @NotNull(message = "Para esta ação o cliente deve ser informado")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cliente", foreignKey = @ForeignKey(name = "fk_id_cliente"))
    private Cliente cliente;

    @NotNull(message = "Pelo menos um livro deve ser informado")
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Livro> livros;

    @Column(name = "multa_por_atraso")
    private BigDecimal multaPorAtraso;

    @Size(max = 120)
    private String observacoes;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevisaoDevolucao() {
        return dataPrevisaoDevolucao;
    }

    public void setDataPrevisaoDevolucao(Date dataPrevisaoDevolucao) {
        this.dataPrevisaoDevolucao = dataPrevisaoDevolucao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    public BigDecimal getMultaPorAtraso() {
        return multaPorAtraso;
    }

    public void setMultaPorAtraso(BigDecimal multaPorAtraso) {
        this.multaPorAtraso = multaPorAtraso;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
