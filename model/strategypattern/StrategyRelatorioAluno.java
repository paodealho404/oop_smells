package model.strategypattern;

import model.Aluno;

public class StrategyRelatorioAluno implements IStrategyRelatorio {
    Aluno aluno;
    public StrategyRelatorioAluno(Aluno a) {
        this.aluno = a;
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do(a) Colaborador(a)\n";
     }
    public String appendProjetosParticipados() {
        return "\nProjetos Participados: ";
    }
    public String appendProjetosValidos() {
        return aluno.returnProjetosValidos();
    }
    
    public String appendProducaoAcademica() {
        return "Produção Academica associada: " + appendPublicacoes();
    }
    public String appendPublicacoes() {
        if(aluno.getPublicacao().size() == 0) return "Nenhuma\n";
        else {
            return aluno.returnListaPublicacoes();
        }
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + aluno + appendProjetosParticipados() + appendProjetosValidos() + appendProducaoAcademica();
    }
    public String gerarRelatorio() {
        return appendRelatorioInfo();
    }
}
