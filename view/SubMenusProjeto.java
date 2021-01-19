package view;
import java.util.Scanner;

import model.Administrador;
import model.LabPesquisa;
import model.Projeto;
import model.commandpattern.CommandProjetoAndamento;
import model.commandpattern.CommandProjetoConcluido;
import model.Colaborador;

public class SubMenusProjeto {
    public static void menuProjetoAlteracao(Administrador admin, Scanner sc, LabPesquisa lab, Projeto p) {
        MenuUtil.limpa();
        System.out.println("Mudar estado do projeto para: ");
        System.out.println("1) Em andamento");
        System.out.println("2) Concluído");
        int opt = MenuUtil.tryReadValidInt("Seleção: ", sc);
        switch (opt) {
            case 1:
                CommandProjetoAndamento andamento = new CommandProjetoAndamento(p);
                admin.setMudancaProjeto(andamento);
                admin.mudarStatusProjeto();
                break;
            case 2:
                CommandProjetoConcluido concluido = new CommandProjetoConcluido(p);
                admin.setMudancaProjeto(concluido);
                admin.mudarStatusProjeto();
                break;
            default:
                System.out.println("Opção inválida");
                break;
        }
    }
    public static void menuAdicionarColaboradorProjeto(Administrador admin, Scanner sc, LabPesquisa lab, Projeto projeto) {
        MenuUtil.limpa();
        for (int i = 0; i < lab.getColaboradores().size(); i++) {
            System.out.println(i+") "+lab.getColaborador(i));    
        }
        System.out.println("Insira o índice do colaborador a ser adicionado no projeto");
        int colab = MenuUtil.tryReadValidInt("Seleção: ", sc);
        Colaborador colab_result = lab.getColaborador(colab);
        if (colab_result != null) {
            admin.addProjetoColaborador(projeto, colab_result);
        } else {
            System.out.println("Colaborador não encontrado");
        }
    }
}
