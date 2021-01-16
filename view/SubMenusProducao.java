package view;

import java.util.Scanner;
import java.util.Vector;

import model.Administrador;
import model.Aluno;
import model.Colaborador;
import model.LabPesquisa;
import model.Orientacao;
import model.Professor;
import model.Projeto;
import model.Publicacao;

public class SubMenusProducao {
    public static void menuPublicacao(Administrador admin, Scanner sc, LabPesquisa lab) {
        int opt = -1;
        while(opt!=0) {
            MenuUtil.limpa();
            System.out.println("Menu de Produção Acadêmica -> Publicações\nEscolha uma das opções abaixo:");
            System.out.println("1) Adicionar publicação\n2) Adicionar colaboradores (existentes) como autores a uma publicação (existente)\n0) Sair");
            opt = Integer.parseInt(sc.nextLine());
            switch(opt) {
                case 1:
                menuAdicionarPublicacao(admin, sc, lab);
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
                break;
                case 2:
                menuAdicionarAutor(admin, sc, lab);
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
                break;
                case 0:
                MenuUtil.limpa();
                break;
                default: 
                MenuUtil.limpa();
                System.out.println("Opção Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
                break;

            }
        }
    }
    public static void menuAdicionarPublicacao(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        if(!MenuUtil.checagemColaboradores(lab) || !MenuUtil.checagemProjetos(lab)) {
            return;
        }
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        Vector<Projeto> projetos = lab.getProjetos();
        Vector<Colaborador> autores = new Vector<Colaborador>();
        String titulo, conferencia;
        int anoPublicacao;

        System.out.print("Insira um título para a publicação: ");
        titulo = sc.nextLine();
        System.out.print("Insira o nome da conferência de publicação: ");
        conferencia = sc.nextLine();
        System.out.print("Insira um ano de publicação: ");
        anoPublicacao = Integer.parseInt(sc.nextLine());
        MenuUtil.limpa();

        
       
        
        int opt = 0;
        while(opt!=-1) {
            MenuUtil.limpa();
            System.out.println("Escolha autores para adicionar à publicação");
            for (int i = 1; i <= colaboradores.size(); i++) {
                if(autores.contains(lab.getColaborador(i-1))) continue;
                System.out.println(i+") "+ lab.getColaborador(i-1));
            }
            System.out.println("0) SAIR");    
            opt = Integer.parseInt(sc.nextLine())-1;
            if(opt==-1) break;
            if(opt>=0 && opt<colaboradores.size()) {
                if(!autores.contains(lab.getColaborador(opt))) {
                    autores.add(lab.getColaborador(opt));
                }
                else {
                    System.out.println("Colaborador já está como autor da publicação");                    
                    System.out.println("Pressione qualquer tecla para continuar...");
                    sc.nextLine();
                }
            }
            else {
                System.out.println("Opção Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        if(autores.size()==0) {
            System.out.println("Não foi possível inserir publicação. Publicação sem autores");
            return;
        }
        Projeto projeto = null;
        while(projeto==null) {
            MenuUtil.limpa();
            System.out.println("Escolha um projeto para vincular à publicacão");
            for (int i = 0; i < projetos.size(); i++) {
                System.out.println(i+") " +projetos.elementAt(i).getTitulo() + " - " + projetos.elementAt(i).getStatus());
            }
            opt = Integer.parseInt(sc.nextLine());
            projeto = lab.getProjeto(opt);
            if(!projeto.getStatus().equals("Em andamento")) {
                System.out.println("Projeto não está em fase de andamento, deseja continuar? (S/N)");
                if(sc.nextLine().equalsIgnoreCase("N")) projeto = null;
            }
        }

        Publicacao p = new Publicacao(titulo, conferencia, anoPublicacao, projeto, autores);
        admin.addPublicacaoLabPesquisa(p, lab);
    }
    public static void menuAdicionarAutor(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        if(!MenuUtil.checagemColaboradores(lab) || !MenuUtil.checagemPublicacoes(lab)) {
            return;
        }
        Vector<Publicacao> publicacoes = lab.getPublicacoes();
        Vector<Colaborador> autores=null;
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        Publicacao publicacao_selecionada = null;
        int opt;
        while(publicacao_selecionada == null) {
            MenuUtil.limpa();
            System.out.println("Selecione uma publicação para adicionar autores"); 
            for (int i = 0; i < publicacoes.size(); i++) {
                System.out.println(i+") "+ publicacoes.elementAt(i));
            }
            opt = Integer.parseInt(sc.nextLine());
            if(opt<publicacoes.size()){
                publicacao_selecionada  = publicacoes.elementAt(opt);
                autores = publicacao_selecionada.getAutores();
            } 
            else {
                System.out.println("Opção inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        opt = 0;
        while(opt!=-1) {
            MenuUtil.limpa();
            System.out.println("Escolha autores para adicionar à publicação");
            for (int i = 1; i <= colaboradores.size(); i++) {
                if(autores.contains(lab.getColaborador(i-1))) continue;
                System.out.println(i+") "+ lab.getColaborador(i-1));
            }
            System.out.println("0) SAIR");    
            opt = Integer.parseInt(sc.nextLine())-1;
            if(opt==-1) break;
            if(opt>=0 && opt<colaboradores.size()) {
                if(!autores.contains(lab.getColaborador(opt))) {
                    autores.add(lab.getColaborador(opt));
                }
                else {
                    System.out.println("Colaborador já está como autor da publicação");
                    System.out.println("Pressione qualquer tecla para continuar...");
                    sc.nextLine();
                }
                
            }
            else {
                System.out.println("Opção Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }

    }
    public static void menuOrientacao(Administrador admin, Scanner sc, LabPesquisa lab) {

        MenuUtil.limpa();
        System.out.println("Menu de Produção Acadêmica -> Orientações\nEscolha uma das opções abaixo:");
        System.out.println("1) Adicionar orientação\n2) Adicionar alunos (existentes) a uma orientação (existente)\n0) Sair");
        int opt = Integer.parseInt(sc.nextLine());
        switch(opt) {
            case 1:
            menuAdicionarOrientacao(admin, sc, lab);
            System.out.println("Pressione qualquer tecla para continuar...");
            sc.nextLine();
            break;
            case 2:
            menuAdicionarAluno(admin, sc, lab);
            System.out.println("Pressione qualquer tecla para continuar...");
            sc.nextLine();
            break;
            case 0: 
            MenuUtil.limpa();
            break;
        }
    }
    public static void menuAdicionarAluno(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        if(!MenuUtil.checagemColaboradores(lab)||!MenuUtil.checagemOrientacoes(lab)) {
            return;
        }
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        Vector<Aluno> orientandos = null;
        Professor profSelecionado=null;
        Vector<Orientacao> orientacoes = null;
        Orientacao orientacaoSelecionada = null;
        int opt=0;
        while(opt>=0 && opt<colaboradores.size() && profSelecionado==null) {
            MenuUtil.limpa();
            System.out.println("Escolha um professor:");
            for (int i = 0; i < colaboradores.size(); i++) {
                if(lab.getColaborador(i) instanceof Professor) {
                    System.out.println(i+") "+ lab.getColaborador(i));
                }
            }
            opt = Integer.parseInt(sc.nextLine());
            if(opt<colaboradores.size() && lab.getColaborador(opt) instanceof Professor) {
                profSelecionado = (Professor) lab.getColaborador(opt);
                orientacoes = profSelecionado.getOrientacao();
                if(orientacoes.size()==0) {
                    System.out.println("Este professor não possui orientações");
                    return;
                }
            }
            else {
                System.out.println("Opção inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        boolean valid = false;
        while(!valid) {
            MenuUtil.limpa();
            System.out.println("Selecione uma orientação do professor "+ profSelecionado.getNome());
            for (int i = 0; i < orientacoes.size(); i++) {
                System.out.println(i+") "+ orientacoes.elementAt(i));
            }
            opt = Integer.parseInt(sc.nextLine());
            if(opt>=0 && opt<orientacoes.size()) {
                orientacaoSelecionada = orientacoes.elementAt(opt);
                orientandos = orientacaoSelecionada.getAlunos();
                valid = true;
            }
        }
        while(opt!=-1) {
            MenuUtil.limpa();
            System.out.println("Escolha alunos para adicionar como orientandos");
            for (int i = 1; i <= colaboradores.size(); i++) {
                if(lab.getColaborador(i-1) instanceof Aluno) {
                    if(orientandos.contains((Aluno)lab.getColaborador(i-1))) continue;
                    System.out.println(i+") "+ lab.getColaborador(i-1));
                }
            }
            System.out.println("0) SAIR");    
            opt = Integer.parseInt(sc.nextLine())-1;
            if(opt==-1) break;
            else {
                if(opt>=0 && opt<colaboradores.size() && lab.getColaborador(opt) instanceof Aluno) {
                    orientacaoSelecionada.addAluno((Aluno)lab.getColaborador(opt));
                }
                else {
                    System.out.println("Opção Inválida");
                    System.out.println("Pressione qualquer tecla para continuar...");
                    sc.nextLine();
                }
            }
           
        }
    } 
    public static void menuAdicionarOrientacao(Administrador admin, Scanner sc, LabPesquisa lab) {
        MenuUtil.limpa();
        if(!MenuUtil.checagemAlunosProfessores(lab)) {
            return;
        }
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        System.out.println("Insira o título da orientação");
        String titulo = sc.nextLine();
        System.out.println("Insira o ano da orientação");
        int anoPublicacao = Integer.parseInt(sc.nextLine());
        Professor orientador = null;
        boolean valid = false;
        int opt;
        while(!valid) {
            MenuUtil.limpa();
            System.out.println("Escolha um professor para adicionar a orientação");
            for (int i = 0; i < colaboradores.size(); i++) {
                if(colaboradores.elementAt(i) instanceof Professor) {
                    System.out.println(i+") "+ colaboradores.elementAt(i).getNome());
                }   
            }
            opt = Integer.parseInt(sc.nextLine());
            if(opt>=0 && opt<colaboradores.size() && lab.getColaborador(opt) instanceof Professor) {
                orientador =  (Professor) lab.getColaborador(opt);
                valid = true;
            }
            else {
                System.out.println("Opção Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        opt=0;
        Vector<Aluno> orientandos= new Vector<Aluno>();

        while(opt!=-1) {    
            MenuUtil.limpa();
            System.out.println("Escolha alunos para adicionar como orientandos");
            for (int i = 1; i <= colaboradores.size(); i++) {
                if(lab.getColaborador(i-1) instanceof Aluno) {
                    if(orientandos.contains((Aluno)lab.getColaborador(i-1))) continue;
                    System.out.println(i+") "+ lab.getColaborador(i-1));
                }
            }
            System.out.println("0) SAIR");    
            opt = Integer.parseInt(sc.nextLine())-1;
            if(opt==-1) break;
            if(opt>=0 && opt<colaboradores.size() && lab.getColaborador(opt) instanceof Aluno) {
                orientandos.add((Aluno)lab.getColaborador(opt));
            }
            else {
                System.out.println("Opção Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }

        if(orientandos.size()==0) {
            System.out.println("Orientação inválida, não há orientandos");
            return;
        }
        else{
            Orientacao orientacao = new Orientacao(titulo, anoPublicacao, orientador, orientandos);
            admin.addOrientacaoProfessor(orientacao, orientador);
        }
        
    } 
}
