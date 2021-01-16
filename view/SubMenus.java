package view;

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
        System.out.println("Digite o nome do colaborador: ");
        nome = sc.nextLine();
        System.out.println("Digite o email do colaborador: ");
        email = sc.nextLine();
        System.out.println("Qual o tipo de colaborador que deseja inserir?");
        System.out.println("1) Professor");
        System.out.println("2) Aluno");
        System.out.println("3) Pesquisador");
        int opt = Integer.parseInt(sc.nextLine());
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
                int optaux = Integer.parseInt(sc.nextLine());
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
                System.out.println("Qual o vínculo do pesquisador?");
                String vinculo = sc.nextLine();
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

        System.out.print("Insira um título para o projeto: ");
        titulo = sc.nextLine();
        System.out.print("Insira o objetivo do projeto: ");
        objetivo = sc.nextLine();
        System.out.print("Insira a descricao do projeto: ");
        descricao = sc.nextLine();
        System.out.print("Insira a agencia financiadora: ");
        agenciaFinanciadora = sc.nextLine();
        System.out.print("Insira o valor financiado: ");
        valorFinanciado = Double.parseDouble(sc.nextLine());
        System.out.println("Insira uma data de inicio");
        System.out.print("Dia: ");
        int dia = Integer.parseInt(sc.nextLine());
        System.out.print("Mes: ");
        int mes = Integer.parseInt(sc.nextLine());
        System.out.print("Ano: ");
        int ano = Integer.parseInt(sc.nextLine());
        dataInicio = LocalDate.of(ano, mes, dia);
        System.out.println("Insira uma data de fim");
        System.out.print("Dia: ");
        dia = Integer.parseInt(sc.nextLine());
        System.out.print("Mes: ");
        mes = Integer.parseInt(sc.nextLine());
        System.out.print("Ano: ");
        ano = Integer.parseInt(sc.nextLine());
        dataFim = LocalDate.of(ano, mes, dia);
        MenuUtil.limpa();
        if (MenuUtil.dataInvalida(dataInicio, dataFim)) {
            System.out.println("Data inválida");
            return;
        }
        System.out.println("Para que o projeto seja validado é necessário a presença de um professor no mesmo");
        System.out.println(
                "Digite uma opcao:\n1) Selecionar um professor existente \n2) Adicionar um novo professor ao laboratório e vincular à pesquisa");
        int opt = Integer.parseInt(sc.nextLine());
        Vector<Colaborador> participantes = new Vector<Colaborador>();
        Projeto proj;
        switch (opt) {
            case 1:
                if (!lab.hasProfessor()) {
                    System.out.println("Não há professores associados ao laboratório");
                    System.out.println(
                            "Digite uma opcao:\n1) Selecionar um professor existente \n2) Adicionar um novo professor ao laboratório e vincular à pesquisa");
                    opt = Integer.parseInt(sc.nextLine());
                    break;
                }

                Vector<Colaborador> colab = lab.getColaboradores();
                System.out.println("Professores: ");
                for (int i = 0; i < colab.size(); i++) {
                    if (colab.elementAt(i) instanceof Professor)
                        System.out.println(colab.elementAt(i));
                }
                System.out.println("Insira o nome do professor desejado");
                String busca = sc.nextLine();
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
                System.out.println("Digite o nome do professor: ");
                String nome = sc.nextLine();
                System.out.println("Digite o email do professor: ");
                String email = sc.nextLine();
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
            System.out.println("Pressione qualquer tecla para continuar...");
            sc.nextLine();
            return;
        } else {
            for (int i = 0; i < projs.size(); i++) {
                System.out.println(i + ") " + projs.elementAt(i));
            }
            System.out.println("Selecione um projeto para ser editado");
            int x = Integer.parseInt(sc.nextLine());
            if (x >= projs.size()) {
                System.out.println("Não existente");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
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
                opt = Integer.parseInt(sc.nextLine());
                switch (opt) {
                    case 1:
                        SubMenusProjeto.menuProjetoAlteracao(admin, sc, lab, selecionado);
                        System.out.println("Pressione qualquer tecla para continuar...");
                        sc.nextLine();
                        break;
                    case 2:
                        SubMenusProjeto.menuAdicionarColaboradorProjeto(admin, sc, lab, selecionado);
                        System.out.println("\n\nPressione qualquer tecla para continuar...");
                        sc.nextLine();
                    case 0:
                        MenuUtil.limpa();
                        break;
                    default:
                        System.out.println("Opção inválida");
                        System.out.println("Pressione qualquer tecla para continuar...");
                        sc.nextLine();
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
            opt = Integer.parseInt(sc.nextLine());
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
