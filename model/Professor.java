package model;

import java.util.Collections;
import java.util.Vector;
public class Professor extends Colaborador{
    private Vector<Orientacao> orientacao;
    public Professor(String nome, String email)
    {
        super(nome, email);
        this.orientacao = new Vector<Orientacao>();
    }
    public Vector<Orientacao> getOrientacao() {
        return orientacao;
    }
    public void setOrientacao(Vector<Orientacao> orientacao) {
        this.orientacao = orientacao;
    }
    @Override
    public String toString() {
        return "Professor| "+super.toString();
    }
    @Override
    public String relatorioProdutividade() {
        String res = super.relatorioProdutividade();
        Vector<ProducaoAcademica> producoes = new Vector<ProducaoAcademica>(super.getPublicacao());   
        producoes.addAll(getOrientacao());
        res+= "Produção Academica associada: ";    
        if(producoes.size()==0) res+="Nenhuma";
        else {
            res+="\n";
            Collections.sort(producoes, Collections.reverseOrder());
            for (int i = 0; i < producoes.size(); i++) {
                res += producoes.elementAt(i)+"\n";
            }
        }
        return res;
        }
}