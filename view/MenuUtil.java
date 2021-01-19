package view;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

import model.Colaborador;
import model.LabPesquisa;
import model.Professor;
import model.Projeto;
import model.Publicacao;
import java.lang.Exception;

public class MenuUtil {
    public static void limpa() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
    public static void waitUser(Scanner sc) {
        System.out.println("Pressione qualquer tecla para continuar...");
        sc.nextLine();
        limpa();
    }
    public static int tryReadValidInt( String msg, Scanner sc) {
        boolean read=false;
        int i=0;
        while(!read) {
            System.out.print(msg);
            try {
                i = Integer.parseInt(sc.nextLine());
                read = true;
            }
            catch(NumberFormatException e) {
                System.out.println("Entrada inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        return i;
    }
    public static double tryReadValidDouble( String msg, Scanner sc) {
        boolean read=false;
        double i=0;
        while(!read) {
            System.out.print(msg);
            try {
                i = Double.parseDouble(sc.nextLine());
                read = true;
            }
            catch(NumberFormatException e) {
                System.out.println("Entrada inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        return i;
    }
    public static String tryReadValidStr( String msg, Scanner sc ) {
        boolean read=false;
        String res="";
        while(!read) {
            System.out.print(msg);
            try {
                res = sc.nextLine();
                if(res.length()==0) throw new Exception("Entrada de texto inválida");
                else {
                    read = true;
                    return res;
                }
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
        return res;
    }
    public static LocalDate tryReadValidDate(String msg, Scanner sc) {
        while(true) {
            try {
                System.out.println(msg);
                int dia = tryReadValidInt("Dia: ", sc);
                int mes = tryReadValidInt("Mês: ", sc);
                int ano = tryReadValidInt("Ano: ", sc);
                LocalDate data = LocalDate.of(ano, mes, dia);
                return data;
            }
            catch (DateTimeException e) {
                System.out.println("Data Inválida");
                System.out.println("Pressione qualquer tecla para continuar...");
                sc.nextLine();
            }
        }
    }
    public static boolean dataInvalida(LocalDate dataInicio, LocalDate dataFim) {
        if(dataFim.isAfter(dataInicio)) return false;
        return true;
    }
    public static boolean checagemColaboradores(LabPesquisa lab) {
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        if(colaboradores.size()==0) {
            System.out.println("Ainda não há colaboradores no laboratório");
            return false;
        }
        return true;
    }
    public static boolean checagemProjetos(LabPesquisa lab) {
        Vector<Projeto> projetos = lab.getProjetos();
        if(projetos.size()==0) {
            System.out.println("Ainda não há projetos no laboratório");
            return false;
        }
        return true;
    }
    public static boolean checagemPublicacoes(LabPesquisa lab) {
        Vector<Publicacao> publicacoes = lab.getPublicacoes();
        if(publicacoes.size()==0) {
            System.out.println("Ainda não há publicações no laboratório");
            return false;
        }
        return true;
    }
    public static boolean checagemAlunos(LabPesquisa lab) {
        if(!lab.hasAluno()) {
            System.out.println("Não há alunos no laboratório");
            return false;
        }
        return true;
    }
    public static boolean checagemProfessores(LabPesquisa lab) {
        if(!lab.hasProfessor()) {
            System.out.println("Não há professores no laboratório");
            return false;
        }
        else return true;
    }
    public static boolean checagemAlunosProfessores(LabPesquisa lab) {
        boolean professor=lab.hasProfessor(), aluno=lab.hasAluno();
        if(!aluno || !professor) {
            if(!aluno && !professor) {
                System.out.println("Não há professores e nem alunos no laboratório");
            }
            else if(!aluno) {
                System.out.println("Não há alunos no laboratório");
            }
            else if(!professor) {
                System.out.println("Não há professores no laboratório");
            }
            return false;
        }
        else return true;
    }
    public static boolean checagemOrientacoes(LabPesquisa lab) {
        Vector<Colaborador> colaboradores = lab.getColaboradores();
        boolean aluno, professor;
        aluno = lab.hasAluno();
        professor = lab.hasProfessor();
        if(!aluno||!professor)
        {
            if(!aluno&&!professor) System.out.println("Não há professores e nem alunos no laboratório");
            else if(!aluno) System.out.println("Não há alunos no laboratório");
            else if(!professor) System.out.println("Não há professores no laboratório");
            return false;
        }   
        else {
            for (int i = 0; i < colaboradores.size(); i++) {
                if(colaboradores.elementAt(i) instanceof Professor) {
                    Professor aux = (Professor) colaboradores.elementAt(i);
                    if(aux.getOrientacao().size()>0) return true;
                }
            }
            System.out.println("Não há orientações registradas");
            return false;
        }
    }
}
