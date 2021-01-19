package model;
import java.util.Vector;
public class Publicacao extends ProducaoAcademica{
    private Vector<Colaborador> autores;
    private String conferencia;
    private Projeto projeto;
    public Publicacao(String titulo, String conferencia, int anoPublicacao, Projeto projeto, Vector<Colaborador> autores)
    {
        super(titulo, anoPublicacao);
        this.conferencia = conferencia;
        this.projeto = projeto;
        this.autores = autores;
    }
    public String getConferencia() {
        return conferencia;
    }
    public Vector<Colaborador> getAutores() {
        return autores;
    }
    public Projeto getProjeto() {
        return projeto;
    }
    public void setAutores(Vector<Colaborador> autores) {
        this.autores = autores;
    }
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    public void setConferencia(String conferencia) {
        this.conferencia = conferencia;
    }
    public void addAutor(Colaborador autor)
    {
        if(autores.contains(autor)) {
            System.out.println("Autor já vinculado à publicação");
            return;
        } else {
            System.out.println("Autor vinculado com sucesso");
            autores.add(autor);
        }
        
    }
    public String appendInicio() {
        return "Publicação| "+ super.toString();
    }
    public String appendAutores() {
        return ", Autores: " + appendListaAutores();
    }
    public String appendListaAutores() {
        String res = "";
        for (int i = 0; i < autores.size(); i++) {
            res+=autores.elementAt(i).getNome();
            if(i<autores.size()-1) res+=", ";
        }
        return res;
    }
    public String appendConferencia() {
        return ", Conferência: " + getConferencia();
    }
    public String appendProjeto() {
        return ", Projeto Relacionado: "+ projeto.getTitulo();
    }
    public String appendPublicacaoInfo() {
        return appendInicio() + appendAutores() + appendConferencia() + appendProjeto();
    }
    @Override
    public String toString() {
        return appendPublicacaoInfo();
    }
}