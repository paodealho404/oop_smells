package model;

import java.util.Collections;
import java.util.Vector;
import model.strategypattern.StrategyRelatorioPesquisador;

public class Pesquisador extends Colaborador {
    private String vinculo;
    public Pesquisador(String nome, String email) {
        super(nome, email);
        setRelatorio(new StrategyRelatorioPesquisador(this));
    }
    public String getVinculo() {
        return vinculo;
    }
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    public String appendInicio() {
        return "Pesquisador| ";
    }
    public String appendPesquisadorInfo() {
        return appendInicio() + super.toString();
    }
    @Override
    public String toString() {
        return appendPesquisadorInfo();
    }
    public Vector<ProducaoAcademica> ordenaProducaoAcademica() {
        Vector<ProducaoAcademica> producoes = new Vector<ProducaoAcademica>(super.getPublicacao());
        Collections.sort(producoes, Collections.reverseOrder());
        return producoes;
    }
    public String returnListaPublicacoes() {
        String res = "";
        Vector<ProducaoAcademica> producoes = ordenaProducaoAcademica();
        if(producoes.size()==0) res+="Nenhuma";
        else {
            res+="\n";
            for (int i = 0; i < producoes.size(); i++) {
                res+=producoes.elementAt(i)+"\n";
            }
        }
        return res;
    }
    public String relatorioProdutividade() {
        return super.relatorio.gerarRelatorio();
    }
}
