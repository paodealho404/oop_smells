package model;

import java.util.Collections;
import java.util.Vector;
import model.strategypattern.StrategyRelatorioProfessor;
public class Professor extends Colaborador{
    private Vector<Orientacao> orientacao;
    public Professor(String nome, String email)
    {
        super(nome, email);
        setRelatorio( new StrategyRelatorioProfessor(this) );
        this.orientacao = new Vector<Orientacao>();
    }
    public Vector<Orientacao> getOrientacao() {
        return orientacao;
    }
    public void setOrientacao(Vector<Orientacao> orientacao) {
        this.orientacao = orientacao;
    }
    public String appendInicio() {
        return "Professor| ";
    }
    public String appendProfessorInfo() {
        return appendInicio() + super.toString();
    }
    @Override
    public String toString() {
        return appendProfessorInfo();
    }
    public String appendInicioRelatorio() {
        return super.relatorioProdutividade();
    }
    public Vector<ProducaoAcademica> ordenarProducaoAcademica(){
        Vector<ProducaoAcademica> producoes = new Vector<ProducaoAcademica>(super.getPublicacao());   
        producoes.addAll(getOrientacao());
        Collections.sort(producoes, Collections.reverseOrder());
        return producoes;
    }
    public String returnListaProducaoAcademica() {      
        Vector<ProducaoAcademica> producoes = ordenarProducaoAcademica();
        if(producoes.size()==0) return "Nenhuma";
        else {
            String res =  "\n";   
            for (int i = 0; i < producoes.size(); i++) {
                res += producoes.elementAt(i)+"\n";
            }
            return res;
        }  
    }
    @Override
    public String relatorioProdutividade() {
        return super.relatorio.gerarRelatorio();
    }
}