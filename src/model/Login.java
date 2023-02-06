package model;

public class Login {

    private Integer id;
    private String nome;
    private String senha;

    public Login(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
