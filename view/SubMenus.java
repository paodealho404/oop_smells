package view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

import model.*;

public class SubMenus {

    public static void menuLabPesquisaAdicionarColaborador(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        System.out.println("Laboratório " + lab.getNome());
        System.out.println("->Adicionar colaborador ao laboratório");
        String nome, email;
        nome = MenuUtil.tryReadValidStr("Digite o nome do colaborador: ", sc);
        email = MenuUtil.tryReadValidStr("Digite o email do colaborador: ", sc);

        System.out.println("Qual o tipo de colaborador que deseja inserir?");
        System.out.println("1) Professor");
        System.out.println("2) Aluno");
        System.out.println("3) Pesquisador");
        int opt = MenuUtil.tryReadValidInt("Seleção: ", sc);
        switch (opt) {
            case 1:
                Colaborador c = new Professor(nome, email);
                lab.addColaborador(c);
                break;
            case 2:
                System.out.println("Qual o tipo de aluno?");
                System.out.println("1) Graduação");
                System.out.println("2) Mestrado");
                System.out.println("3) Doutorado");
                int optaux = MenuUtil.tryReadValidInt("Seleção: ", sc);
                Aluno a = new Aluno(nome, email);
                if (optaux == 1)
                    a.setTipo("Graduação");
                if (optaux == 2)
                    a.setTipo("Mestrado");
                if (optaux == 3)
                    a.setTipo("Doutorado");
                Colaborador x = (Colaborador) a;
                lab.addColaborador(x);
                break;
            case 3:
                String vinculo = MenuUtil.tryReadValidStr("Qual o vínculo do pesquisador?", sc);
                Pesquisador k = new Pesquisador(nome, email);
                k.setVinculo(vinculo);
                Colaborador y = (Colaborador) k;
                lab.addColaborador(y);
                break;
            default:
                System.out.println("Tipo de colaborador inválido");
                break;
        }
    }
    public static void menuLabPesquisaAdicionarProjetos(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        System.out.println("Laboratório " + lab.getNome());
        System.out.println("->Adicionar projeto ao laboratório");

        String titulo, objetivo, descricao, agenciaFinanciadora;
        LocalDate dataInicio, dataFim;
        Double valorFinanciado;

        titulo = MenuUtil.tryReadValidStr("Insira um título para o projeto: ", sc);
        objetivo = MenuUtil.tryReadValidStr("Insira o objetivo do projeto: ", sc);
        descricao = MenuUtil.tryReadValidStr("Insira a descricao do projeto: ", sc);
        agenciaFinanciadora = MenuUtil.tryReadValidStr("Insira a agencia financiadora: ", sc);
        valorFinanciado = MenuUtil.tryReadValidDouble("Insira o valor financiado: ", sc);


        dataInicio = MenuUtil.tryReadValidDate("Insira uma data de inicio", sc);
        dataFim = MenuUtil.tryReadValidDate("Insira uma data de fim", sc);
        MenuUtil.limpa();
        if (MenuUtil.dataInvalida(dataInicio, dataFim)) {
            System.out.println("Data inválida");
            return;
        }
        System.out.println("Para que o projeto seja validado é necessário a presença de um professor no mesmo");
        System.out.println("Digite uma opcao:\n1) Selecionar um professor existente \n2) Adicionar um novo professor ao laboratório e vincular à pesquisa");
        int opt = MenuUtil.tryReadValidInt("Seleção: ", sc);
        Vector<Colaborador> participantes = new Vector<Colaborador>();
        Projeto proj;
        switch (opt) {
            case 1:
                if (!lab.hasProfessor()) {
                    System.out.println("Não há professores associados ao laboratório");
                    System.out.println(
                            "Digite uma opcao:\n1) Selecionar um professor existente \n2) Adicionar um novo professor ao laboratório e vincular à pesquisa");
                    opt = MenuUtil.tryReadValidInt("Seleção: ", sc);;
                    break;
                }

                Vector<Colaborador> colab = lab.getColaboradores();
                System.out.println("Professores: ");
                for (int i = 0; i < colab.size(); i++) {
                    if (colab.elementAt(i) instanceof Professor)
                        System.out.println(colab.elementAt(i));
                }
                System.out.println("Insira o nome do professor desejado");
                String busca = MenuUtil.tryReadValidStr("", sc);;
                Colaborador p = lab.getColaborador(busca);
                if (p == null)
                    System.out.println("Professor não encontrado");
                else {
                    participantes.add(p);
                    proj = new Projeto(titulo, dataInicio, dataFim, agenciaFinanciadora, valorFinanciado, objetivo,
                            descricao, participantes);
                    if(proj.isProjectValid()) {
                        admin.cadastroProjetoLab(proj, lab);
                        admin.addProjetoColaborador(proj, p);
                    }
                }
                break;
            case 2:
                String nome = MenuUtil.tryReadValidStr("Digite o nome do professor: ", sc);
                String email = MenuUtil.tryReadValidStr("Digite o email do professor: ", sc);
                Colaborador c = new Professor(nome, email);
                lab.addColaborador(c);
                participantes.add(c);

                proj = new Projeto(titulo, dataInicio, dataFim, agenciaFinanciadora, valorFinanciado, objetivo,
                        descricao, participantes);
                if(proj.isProjectValid()) {
                    admin.addProjetoColaborador(proj, c);
                    admin.cadastroProjetoLab(proj, lab);
                }
                break;
            default:
                break;
        }
    }
    public static void menuExibicaoProjetos(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        Vector<Projeto> projs = lab.getProjetos();
        if (projs.size() == 0) {
            System.out.println("Ainda não há projetos cadastrados");
            MenuUtil.waitUser(sc);
            return;
        } else {
            for (int i = 0; i < projs.size(); i++) {
                System.out.println(i + ") " + projs.elementAt(i));
            }
            int x = MenuUtil.tryReadValidInt("Selecione um projeto para ser editado: ", sc);;
            if (x >= projs.size()) {
                System.out.println("Não existente");
                MenuUtil.waitUser(sc);
                return;
            }
            Projeto selecionado = projs.elementAt(x);
            int opt = -1;
            while (opt != 0) {
                MenuUtil.limpa();
                System.out.println("Laboratório " + lab.getNome()+ "\n->Edição do Projeto " + selecionado.getTitulo());
                System.out.println("Selecione uma funcionalidade");
                System.out.println("1) Alterar status do projeto");
                System.out.println("2) Adicionar colaborador (existente) ao projeto");
                System.out.println("0) Sair");
                opt = MenuUtil.tryReadValidInt("Seleção: ", sc);;
                switch (opt) {
                    case 1:
                        SubMenusProjeto.menuProjetoAlteracao(admin, sc, lab, selecionado);
                        MenuUtil.waitUser(sc);
                        break;
                    case 2:
                        SubMenusProjeto.menuAdicionarColaboradorProjeto(admin, sc, lab, selecionado);
                        MenuUtil.waitUser(sc);
                    case 0:
                        MenuUtil.limpa();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        MenuUtil.waitUser(sc);
                        break;
                }
            }
        }
    }
    public static void menuProducaoAcademica(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        int opt=-1;
        while(opt!=0) {
            MenuUtil.limpa();
            System.out.println("Menu de Produção Acadêmica\nEscolha uma das opções abaixo");
            System.out.println("1) Menu de Publicacões\n2) Menu de Orientações\n0) Sair");
            opt = MenuUtil.tryReadValidInt("Seleção: ", sc);;
            switch(opt) {
                case 1: 
                SubMenusProducao.menuPublicacao(admin, sc, lab);
                break;
                case 2: 
                SubMenusProducao.menuOrientacao(admin, sc, lab);
                break;
                case 0: 
                MenuUtil.limpa();
                break;
            }
        }
        
    }
    
   
}
