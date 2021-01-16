package model.commandPattern;

import model.Projeto;

public class ProjetoConcluido implements ICommandProjeto{
    Projeto projeto;
    public ProjetoConcluido(Projeto p) {
        this.projeto = p;
    }
    public void mudarStatus(){
        projeto.mudarProjetoConcluido();
    }
}
