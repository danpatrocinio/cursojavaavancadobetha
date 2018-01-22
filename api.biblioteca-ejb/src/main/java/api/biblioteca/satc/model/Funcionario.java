package api.biblioteca.satc.model;

import api.biblioteca.satc.enums.Cargo;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable, api.biblioteca.satc.model.Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;
    @NotNull(message = "O nome do funcionário não pode ser nulo")
    @Column(name = "nome", length = 80, nullable = false)
    @Size(min = 2, max = 80)
    private String nome;
    @Size(min = 11, max = 11)
    @NotNull(message = "O CPF do funcionário não pode ser nulo")
    private String cpf;
    private String rg;
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;
    private String sexo;
    private String celular;
    private String telefone;
    private String email;
    @Size(min = 5, max = 80)
    @NotNull(message = "O endereço do funcionário não pode ser nulo")
    private String endereco;
    private Cargo cargo;

    @Override
    public Long getId() {
        return id;
    }

    public String getCelular() {
        return celular;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public String getNome() {
        return nome;
    }

    public String getRg() {
        return rg;
    }

    public String getSexo() {
        return sexo;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
