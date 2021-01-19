package view;

import model.Administrador;
import model.LabPesquisa;
import model.Projeto;
import model.Colaborador;
import java.util.Scanner;
public class Menus {
    public static void menuAdmin() {
        Scanner sc = new Scanner(System.in);
        String nome="", email="", login="", senha="";
        System.out.println("Crie um administrador para o sistema");
        boolean ready=false;
        while(!ready) {
            nome = MenuUtil.tryReadValidStr("Nome: ",sc);
            email = MenuUtil.tryReadValidStr("Email: ",sc);
            login = MenuUtil.tryReadValidStr("Login: ",sc);
            senha = MenuUtil.tryReadValidStr("Senha: ",sc);
            MenuUtil.waitUser(sc);
            ready = true;
        }
        
        Administrador admin = new Administrador(nome, email, login, senha);
        System.out.println("Efetue login no sistema "+ admin.getNome());
        senha = MenuUtil.tryReadValidStr("Senha: ",sc);;
        boolean flag = false;
        while(!flag)
        {
            if(admin.logar(senha)) {
                flag=true;
                MenuUtil.limpa();
                menuCriarLab(admin, sc);
            }
            else {
                MenuUtil.waitUser(sc);
                System.out.println("Efetue login: "+ admin.getLogin());
                senha = MenuUtil.tryReadValidStr("Senha: ", sc);
            }
        }
        sc.close();
    }
    public static void menuCriarLab(Administrador admin, Scanner sc) {
        MenuUtil.limpa();
        System.out.println("Crie um laboratório de pesquisa");
        String nome = MenuUtil.tryReadValidStr("Nome: ", sc);
        LabPesquisa lab = new LabPesquisa(nome, admin);
        menuLabPesquisa(admin, sc, lab);
    }
    public static void menuLabPesquisa(Administrador admin, Scanner sc, LabPesquisa lab) { 
        MenuUtil.limpa();
        int opt = -1;
        while(opt!=0) {
            MenuUtil.limpa();
            System.out.println("Laboratório "+ lab.getNome());
            System.out.println("Escolha uma opção: ");
            System.out.println("1) Adicionar Colaboradores ao Laboratório");
            System.out.println("2) Adicionar Projetos ao Laboratório");
            System.out.println("3) Menu de Produção Acadêmica");
            System.out.println("4) Menu de Edição de Projetos");
            System.out.println("5) Relatório de Produção Acadêmica de Projetos");
            System.out.println("6) Relatório de Produção Acadêmica de Colaboradores");
            System.out.println("7) Relatório de Produção Acadêmica do Laboratório");
            System.out.println("0) Sair");
            opt = MenuUtil.tryReadValidInt("Seleção: ", sc);
            switch(opt) {
                case 1:
                    SubMenus.menuLabPesquisaAdicionarColaborador(admin, sc, lab);
                    MenuUtil.waitUser(sc);
                    break;
                case 2:
                    SubMenus.menuLabPesquisaAdicionarProjetos(admin, sc, lab);
                    MenuUtil.waitUser(sc);
                    break;
                case 3:
                    SubMenus.menuProducaoAcademica(admin, sc, lab);
                    break;
                case 4:
                    SubMenus.menuExibicaoProjetos(admin, sc, lab);
                    break;
                case 5:
                    menuRelatorioProjeto(lab, sc);
                    MenuUtil.waitUser(sc);
                    break;
                case 6: 
                    menuRelatorioColaborador(lab, sc);
                    MenuUtil.waitUser(sc);
                    break;
                case 7:
                    System.out.println(lab.relatorioProdutividade());
                    MenuUtil.waitUser(sc);
                    break;
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
    public static void menuRelatorioColaborador(LabPesquisa lab, Scanner sc) {
        MenuUtil.limpa();
        if(lab.getColaboradores().size()==0) {
            System.out.println("Não há colaboradores cadastrados neste laboratório de pesquisa");
            return;
        }
        System.out.println("Lista de Colaboradores vinculados ao Laboratório de Pesquisa:");
        for (int i = 0; i < lab.getColaboradores().size(); i++) {
            System.out.println(i+") "+ lab.getColaborador(i));
        }
        int opt = MenuUtil.tryReadValidInt("Para gerar o relatório de produção acadêmica selecione um colaborador: ", sc);
        if(opt < lab.getColaboradores().size()){    
            Colaborador colab = lab.getColaborador(opt);
            System.out.println("\n\n"+colab.relatorioProdutividade());
        }
        else {
            System.out.println("Opção inválida");
        }
    }
    public static void menuRelatorioProjeto(LabPesquisa lab, Scanner sc) {
        MenuUtil.limpa();
        if(lab.getProjetos().size()==0) {
            System.out.println("Não há Projetos cadastrados neste Laboratório de Pesquisa");
            return;
        }
        System.out.println("Lista de Projetos vinculados ao Laboratório de Pesquisa");
        for (int i = 0; i < lab.getProjetos().size(); i++) {
                System.out.println(i+") "+ lab.getProjeto(i));
        }
        
        int opt = MenuUtil.tryReadValidInt("Para gerar o relatório de produção acadêmica selecione um projeto:\nSeleção: ", sc);

        if( opt < lab.getProjetos().size()) {
            Projeto proj = lab.getProjeto(opt);
            System.out.println("\n\n"+proj.relatorioProdutividade());
        } 
        else {
            System.out.println("Opção inválida");
        }
    }
}
