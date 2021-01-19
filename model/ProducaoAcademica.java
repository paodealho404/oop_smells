package model;

public class ProducaoAcademica implements Comparable<ProducaoAcademica>{
    private int anoPublicacao;
    private String titulo;
    public ProducaoAcademica(String titulo, int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
        this.titulo = titulo;
    }
    public int getAnoPublicacao() {
        return anoPublicacao;
    }
    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String appendTitulo() {
        return "Titulo: " + getTitulo();
    }
    public String appendDataPublicacao() {
        return ", Data de Publicação: " + getAnoPublicacao();
    }
    public String appendProducaoAcademicaInfo() {
        return appendTitulo() + appendDataPublicacao();
    }
    @Override
    public String toString() {
        return appendProducaoAcademicaInfo();
    }
    @Override
    public int compareTo(ProducaoAcademica x) {
        Integer ano_prod1 = Integer.valueOf(this.getAnoPublicacao());
        Integer ano_prod2 = Integer.valueOf(x.getAnoPublicacao());
        return ano_prod1.compareTo(ano_prod2);
    }
}
