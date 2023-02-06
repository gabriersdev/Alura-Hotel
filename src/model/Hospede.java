package model;

import java.sql.Date;

public class Hospede {
    private Integer id;
    private String nome;
    private String sobrenome;
    private Date data_nascimento;
    private String nacionalidade;
    private String telefone;
    private Integer cod_reserva;

    public Hospede() {

    }

    public Hospede(String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer cod_reserva) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.cod_reserva = cod_reserva;
    }

    public Hospede(Integer id, String nome, String sobrenome, Date data_nascimento, String nacionalidade, String telefone, Integer cod_reserva) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.data_nascimento = data_nascimento;
        this.nacionalidade = nacionalidade;
        this.telefone = telefone;
        this.cod_reserva = cod_reserva;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCod_reserva(Integer cod_reserva) {
        this.cod_reserva = cod_reserva;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public Integer getCod_reserva() {
        return cod_reserva;
    }
}
