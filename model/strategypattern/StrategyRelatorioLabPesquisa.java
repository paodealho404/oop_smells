package model.strategypattern;

import model.LabPesquisa;

public class StrategyRelatorioLabPesquisa implements IStrategyRelatorio {
    LabPesquisa labPesquisa;
    public StrategyRelatorioLabPesquisa(LabPesquisa lab) {
        this.labPesquisa = lab;
    }
    public String appendColaboradores() {
        return "Número de colaboradores: " + labPesquisa.getColaboradores().size();
    }
    public String appendProjetosElaboracao() {
        return "\nNúmero de projetos em elaboração: "+ labPesquisa.getProjetosElaboracao();
    }
    public String appendProjetosAndamento() {
        return "\nNúmero de projetos em andamento: " + labPesquisa.getProjetosAndamento();
    }
    public String appendProjetosConcluido() {
        return "\nNúmero de projetos concluídos: " + labPesquisa.getProjetosConcluido();
    }
    public String appendTotalProjetos() { 
        return "\nNúmero total de projetos: " + labPesquisa.getProjetos().size();
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do Laboratório de Pesquisa\n\n";
    }
    public String appendProducaoAcademica() {
        return "\nOrientações: " + labPesquisa.getNumOrientacoes() + "\nPublicacoes: " + labPesquisa.getPublicacoes().size();
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + appendColaboradores() + appendProjetosElaboracao() + appendProjetosAndamento() + appendProjetosConcluido() + appendTotalProjetos() 
        + appendProducaoAcademica();
    }
    public String gerarRelatorio() {
        return appendRelatorioInfo();
    }
}
