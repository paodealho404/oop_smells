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
    @Override
    public String toString() {
        String res = "Publicação| "+super.toString() + ", Autores: ";
        for (int i = 0; i < autores.size(); i++) {
                res+=autores.elementAt(i).getNome();
                if(i<autores.size()-1) res+=", ";
        }
        res+=", Conferência: " + getConferencia() +", Projeto Relacionado: "+ projeto.getTitulo();
        return res;
    }
}