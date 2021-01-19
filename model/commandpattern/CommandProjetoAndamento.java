package model.commandpattern;
import model.Projeto;

public class CommandProjetoAndamento implements ICommandProjeto {
    private Projeto projeto;
    public CommandProjetoAndamento(Projeto p) {
        this.projeto = p;
    }
    public void mudarStatus() {
        projeto.mudarProjetoAndamento();
    }
}