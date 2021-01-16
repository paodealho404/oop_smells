package view;

import java.time.LocalDate;
import java.util.Vector;

import model.Colaborador;
import model.LabPesquisa;
import model.Professor;
import model.Projeto;
import model.Publicacao;

public class MenuUtil {
    public static void limpa() {
        for (int i = 0; i < 50; i++) {
            System.out.println("");
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
