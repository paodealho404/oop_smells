package model.commandPattern;
import model.Projeto;

public class ProjetoAndamento implements ICommandProjeto {
    private Projeto projeto;
    public ProjetoAndamento(Projeto p) {
        this.projeto = p;
    }
    public void mudarStatus() {
        projeto.mudarProjetoAndamento();
    }
}