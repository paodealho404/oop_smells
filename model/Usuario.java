package model;
import java.util.Date;
public abstract class Usuario {
    private String nome;
    private String email;
    Date dataAssociacao;
    public Usuario( String nome, String email){
        setEmail(email);
        setNome(nome);
    }
    public void setDataAssociacao(Date dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }
    public Date getDataAssociacao() {
        return dataAssociacao;
    }
    public String getEmail() {
        return email;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public abstract String toString();
}
