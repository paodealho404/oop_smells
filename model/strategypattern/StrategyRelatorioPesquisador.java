package model.strategypattern;

import model.Pesquisador;

public class StrategyRelatorioPesquisador implements IStrategyRelatorio {
    Pesquisador pesquisador;
    public StrategyRelatorioPesquisador(Pesquisador a) {
        this.pesquisador = a;
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do(a) Colaborador(a)\n";
        }
    public String appendProjetosParticipados() {
        return "\nProjetos Participados: ";
    }
    public String appendProjetosValidos() {
        return pesquisador.returnProjetosValidos();
    }
    
    public String appendProducaoAcademica() {
        return "Produção Academica associada: " + appendPublicacoes();
    }
    public String appendPublicacoes() {
        if(pesquisador.getPublicacao().size() == 0) return "Nenhuma\n";
        else {
            return pesquisador.returnListaPublicacoes();
        }
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + pesquisador + appendProjetosParticipados() + appendProjetosValidos() + appendProducaoAcademica();
    }
    public String gerarRelatorio() {
        return appendRelatorioInfo();
    }
}
