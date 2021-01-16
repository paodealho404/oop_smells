package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Vector;
public class Projeto implements Comparable<Projeto>{
    private String titulo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String agenciaFinanciadora;
    private double valorFinanciado;
    private String objetivo;
    private String descricao;
    private Vector<Colaborador> participantes;
    private Vector<Publicacao> publicacoes;
    private boolean valid;
    private String status;
    public Projeto(String titulo, LocalDate dataInicio, LocalDate dataFim, String agenciaFinanciadora, double valorFinanciado, String objetivo, String descricao, Vector<Colaborador> participantes)
    {
        this.titulo = titulo;
        this.dataFim = dataFim;
        this.dataInicio = dataInicio;
        this.agenciaFinanciadora = agenciaFinanciadora;
        this.valorFinanciado = valorFinanciado;
        this.objetivo = objetivo;
        this.descricao = descricao;
        this.participantes = participantes;
        this.publicacoes = new Vector<Publicacao>();
        this.valid = checkValid();
    }
    public boolean getValid() {
        return valid;
    }
    public String getAgenciaFinanciadora() {
        return agenciaFinanciadora;
    }
    public LocalDate getDataFim() {
        return dataFim;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public String getDescricao() {
        return descricao;
    }
    public String getObjetivo() {
        return objetivo;
    }
    public Vector<Colaborador> getParticipantes() {
        return participantes;
    }
    public String getTitulo() {
        return titulo;
    }
    public String getStatus() {
        return status;
    }
    public double getValorFinanciado() {
        return valorFinanciado;
    }
    public void setAgenciaFinanciadora(String agenciaFinanciadora) {
        this.agenciaFinanciadora = agenciaFinanciadora;
    }
    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }
    public void setParticipantes(Vector<Colaborador> participantes) {
        this.participantes = participantes;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setValorFinanciado(double valorFinanciado) {
        this.valorFinanciado = valorFinanciado;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Vector<Publicacao> getPublicacoes() {
        return publicacoes;
    }
    public void setPublicacoes(Vector<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
    @Override
    public String toString() {
        return appendProjetoInfo();
    }
    public boolean checkValid(){
        setStatus("Em elaboração");
        boolean flag=false;
        for (int i = 0; i < participantes.size(); i++) {
            if(participantes.elementAt(i) instanceof Professor) flag = true;
        }
        if(!flag) System.out.println("Não há professor cadastrado para o projeto");
        return flag;
    }
    @Override
    public int compareTo(Projeto a) {
        return this.getDataFim().compareTo(a.getDataFim());
    }
    public void removerAlunosIrregulares() {
        Vector<Colaborador> colab = getParticipantes();
        for (int i = 0; i < colab.size(); i++) {
            if(colab.elementAt(i) instanceof Aluno) {
            Aluno aux = (Aluno)colab.elementAt(i);
                if(aux.getTipo().equals("Graduação")){
                    if(aux.getAndamento()==2) { // removendo alunos de graduação com 2 projetos em andamento
                        colab.removeElementAt(i);
                        Vector<Projeto> projs = aux.getProjetos();
                        projs.remove(this);
                        System.out.println("Removendo alunos de graduação com 2 projetos em andamento");
                    }
                }
            }
        }
    }
    public void mudarProjetoAndamento() {
        if(isProjectValid() &&  this.getStatus().equals("Em elaboração")) {
            removerAlunosIrregulares();
            setStatus("Em andamento");
            System.out.println("Mudança realizada com sucesso!");
        }
        else {
                System.out.println("Não foi possível alterar o status do projeto. Operação negada");
        }
    }
    public void mudarProjetoConcluido() {
        if(getPublicacoes().size()>0) {
            setStatus("Concluído");
            System.out.println("Mudança realizada com sucesso!");
        } 
        else System.out.println("Não foi possível alterar o status do projeto. Ainda não há publicações vinculadas a esse projeto");
    }
    public Vector<ProducaoAcademica> ordenarProducoesAcademicas() {
        Vector<ProducaoAcademica> producoesAcademicasOrdenadas = new Vector<ProducaoAcademica>();
        producoesAcademicasOrdenadas.addAll(this.getPublicacoes());
        Collections.sort(producoesAcademicasOrdenadas, Collections.reverseOrder());
        return producoesAcademicasOrdenadas;
    }
    public String appendProjeto() {
        return "Projeto| ";
    }
    public String appendTitulo() {
        return "Título: " + getTitulo();
    }
    public String appendStatus() {
        return "Status: " + getStatus();
    }
    public String appendObjetivo() {
        return "Objetivo: "+ getObjetivo();
    }
    public String appendDescricao() {
        return "Descrição: " + getDescricao();
    }
    public String appendDataInicio() {
        return "Data de Ínicio: " + getDataInicio();
    }
    public String appendDataFim() {
        return "Data de Conclusão: " + getDataFim();
    }
    public String appendParticipantes() {
        return "Participantes: ";
    }
    public String appendProjetoInfo() {
        return appendProjeto() + appendTitulo() + "\n" + appendStatus() + "\n" + appendObjetivo() + "\n" + appendDescricao() + "\n" +
        appendDataInicio() + "\n" + appendDataFim() + "\n" + appendParticipantes() + "("+ getParticipantes().size()+ ") "+ appendListaParticipantes() + "\n";
    }
    public String appendListaParticipantes () {
        String res = "";
        Vector<Colaborador> participantes = getParticipantes();

        for (int i = 0; i < participantes.size(); i++) {
            res += participantes.elementAt(i).getNome();
            if(i!=participantes.size()-1) res+=", ";
            else res+="\n";
        }
        return res;
    }
    public String appendListaPublicacoes() {
        Vector<ProducaoAcademica> producoes = ordenarProducoesAcademicas();
        String res = "\n";
        for (int i = 0; i < producoes.size(); i++) {
            res+=producoes.elementAt(i)+"\n";
        }
        return res;
    }
    public String appendInicioRelatorio() {
        return "\nRelatório de Produtividade do Projeto\n";
    }
    public String appendPublicacoes() {
        String res="\nPublicações: ";
        if(publicacoes.size()==0) res+="Nenhuma";
        else {
            res+="\n";
            res += appendListaPublicacoes();
        }
        return res;
    }
    public String appendRelatorioInfo() {
        return appendInicioRelatorio() + toString() + appendPublicacoes() + "\n";
    }
    public String relatorioProdutividade() {
        return appendRelatorioInfo();
    }
    public boolean isProjectValid() {
        if(getValid() && !getTitulo().isEmpty() && getDataFim()!=null && getDataInicio()!=null
        && getValorFinanciado()>=0 && !getAgenciaFinanciadora().isEmpty() && !getObjetivo().isEmpty() && !getDescricao().isEmpty() && getParticipantes().size()>0) return true;
        return false;
    }
}