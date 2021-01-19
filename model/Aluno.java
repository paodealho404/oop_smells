package model;

import java.util.Collections;
import java.util.Vector;
import model.strategypattern.StrategyRelatorioAluno;
public class Aluno extends Colaborador{
    String tipo;
    
    public Aluno( String nome, String email) {
        super(nome, email);
        this.setRelatorio(new StrategyRelatorioAluno(this));
    }
    public Aluno( String nome, String email, String tipo)
    {
        super(nome, email);
        setTipo(tipo);
        this.setRelatorio(new StrategyRelatorioAluno(this));
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String appendInicio() {
        String res = "Aluno| " + super.toString();
        res += "\n";
        return  res;
    }
    public String appendTipo() {
        return "Tipo de Aluno: " + getTipo() + "\n";
    }
    public String appendAlunoInfo() {
        return appendInicio() + appendTipo() ;
    }

    @Override
    public String toString() {
        return appendAlunoInfo();
    }
    public String returnListaPublicacoes() {
        String res ="\n";
        Vector <ProducaoAcademica> publicacoes = ordenarProducoesAcademicas();
        for (int i = 0; i < publicacoes.size(); i++) {
            res+= publicacoes.elementAt(i) + "\n";
        }
        return res;
    }
    public Vector<ProducaoAcademica> ordenarProducoesAcademicas() {
        Vector<ProducaoAcademica> producoes = new Vector<ProducaoAcademica>(super.getPublicacao());
        Collections.sort(producoes, Collections.reverseOrder());
        return producoes;
    }
    public String relatorioProdutividade() {
        return super.relatorio.gerarRelatorio();
    }
}