package model.strategypattern;

import model.Projeto;

public class StrategyRelatorioProjeto implements IStrategyRelatorio{
    Projeto projeto;
    public StrategyRelatorioProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    public String appendPublicacoes() {
        return "Publicações: " + projeto.returnPublicacoes();
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do Projeto\n";
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + projeto + appendPublicacoes();
    }
    public String gerarRelatorio() {
        return appendRelatorioInfo();
    }

}
