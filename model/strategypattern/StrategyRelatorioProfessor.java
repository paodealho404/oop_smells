package model.strategypattern;

import model.Professor;

public class StrategyRelatorioProfessor implements IStrategyRelatorio {
    Professor professor;
    public StrategyRelatorioProfessor(Professor a) {
        this.professor = a;
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do(a) Colaborador(a)\n";
     }
    public String appendProjetosParticipados() {
        return "\nProjetos Participados: ";
    }
    public String appendProjetosValidos() {
        return professor.returnProjetosValidos();
    }
    
    public String appendProducaoAcademica() {
        return "Produção Academica associada: " + appendProducoes();
    }
    public String appendProducoes() {
        if(professor.getPublicacao().size() == 0) return "Nenhuma\n";
        else {
            return professor.returnListaProducaoAcademica();
        }
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + professor + appendProjetosParticipados() + appendProjetosValidos() + appendProducaoAcademica();
    }
    public String gerarRelatorio() {    
        return appendRelatorioInfo();
    }
}
