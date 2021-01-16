package model;
import java.util.Vector;
public class LabPesquisa {
    private String nome;
    private Vector<Colaborador> colaboradores;
    private Vector<Projeto> projetos;
    private Vector<Publicacao> publicacoes;
    private Administrador admin;
    public LabPesquisa(String nome, Administrador admin) {
        this.nome = nome;
        this.admin = admin;
        this.colaboradores = new Vector<Colaborador>();
        this.publicacoes = new Vector<Publicacao>();
        this.projetos = new Vector<Projeto>();
    }
    public Administrador getAdmin() {
        return admin;
    }
    public int getNumOrientacoes() {
        int total = 0;
        for (int i = 0; i < colaboradores.size(); i++) {
            if(colaboradores.elementAt(i) instanceof Professor) {
                Professor x = (Professor) colaboradores.elementAt(i);
                total+= x.getOrientacao().size();
            }
        }
        return total;
    }
    public Vector<Colaborador> getColaboradores() {
        return colaboradores;
    }
    public String getNome() {
        return nome;
    }
    public Vector<Projeto> getProjetos() {
        return projetos;
    }
    public int getProjetosElaboracao() {
        Vector<Projeto> projs = getProjetos();
        int total=0;
        for (int i = 0; i < projs.size(); i++) {
            if(projs.elementAt(i).getStatus().equals("Em elaboração")) total++;
        }
        return total;
    }
    public int getProjetosAndamento() {
        Vector<Projeto> projs = getProjetos();
        int total=0;
        for (int i = 0; i < projs.size(); i++) {
            if(projs.elementAt(i).getStatus().equals("Em andamento")) total++;
        }
        return total;
    }
    public int getProjetosConcluido() {
        Vector<Projeto> projs = getProjetos();
        int total=0;
        for (int i = 0; i < projs.size(); i++) {
            if(projs.elementAt(i).getStatus().equals("Concluído")) total++;
        }
        return total;
    }
    public Vector<Publicacao> getPublicacoes() {
        return publicacoes;
    }
    public void setPublicacoes(Vector<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }
    public String relatorioProdutividade() {
        int projAnd = getProjetosAndamento();
        int projConc = getProjetosConcluido();
        int projElab = getProjetosElaboracao();
        String res = "Número de colaboradores: " + getColaboradores().size() + "\nNúmero de projetos em elaboração: "+ projElab +
        "\nNúmero de projetos em andamento: " + projAnd + "\nNúmero de projetos concluídos: " + projConc +"\nNúmero total de projetos: "
        +getProjetos().size() + "\nOrientações: " + getNumOrientacoes()
        + "\nPublicacoes: " + publicacoes.size();
        return res;
    }
    public void addProjeto(Projeto p) {
        if(projetos!=null) {
            if(projetos.contains(p)) {
                System.out.println("Projeto já existente");
                return;
            }
        }
        projetos.add(p);
        System.out.println("Projeto adicionado com sucesso ao laboratório!");
    }
    public void addColaborador(Colaborador c) {
        if(colaboradores!=null) {
           if(colaboradores.contains(c)) {
               System.out.println("Colaborador já existente");
               return;
           }
        }
        colaboradores.add(c);
        System.out.println("Colaborador adicionado com sucesso ao laboratório!");
    }
    public Projeto getProjeto(String titulo) {
        for (int i = 0; i < projetos.size(); i++) {
            if(titulo.equals(projetos.elementAt(i).getTitulo())) return projetos.elementAt(i);
        }
        return null;
    }
    public Projeto getProjeto(int i) {
        if( i < projetos.size()) return projetos.elementAt(i);
        else return null;
    }
    public boolean hasProfessor() {
        for (int i = 0; i < colaboradores.size(); i++) {
            if(colaboradores.elementAt(i) instanceof Professor) return true;
        }
        return false;
    }
    public boolean hasAluno() {
        for (int i = 0; i < colaboradores.size(); i++) {
            if(colaboradores.elementAt(i) instanceof Aluno) return true;
        }
        return false;
    }
    public Colaborador getColaborador(String nome) {
        for (int i = 0; i < colaboradores.size(); i++) {
            if(nome.equals(colaboradores.elementAt(i).getNome())) return colaboradores.elementAt(i);
        }
        return null;
    }
    public Colaborador getColaborador(int i) {
        if( i < colaboradores.size()) return colaboradores.elementAt(i);
        else return null;
    }
    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    public void setColaboradores(Vector<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setProjetos(Vector<Projeto> projetos) {
        this.projetos = projetos;
    }
}
