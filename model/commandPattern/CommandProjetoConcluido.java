package model.commandpattern;
import model.Projeto;

public class CommandProjetoConcluido implements ICommandProjeto{
    Projeto projeto;
    public CommandProjetoConcluido(Projeto p) {
        this.projeto = p;
    }
    public void mudarStatus(){
        projeto.mudarProjetoConcluido();
    }
}
