package model;
import java.util.Vector;
public class Orientacao extends ProducaoAcademica{
    private Professor professor;
    private Vector<Aluno> alunos;
    public Orientacao(String titulo, int anoPublicacao, Professor professor, Vector<Aluno> alunos)
    {
        super(titulo, anoPublicacao);
        this.professor = professor; ///*** */
        this.alunos = alunos;
    }
    public void addAluno(Aluno a)
    {
        if(alunos.contains(a)){
            System.out.println("Aluno já está associado à orientação");
            return;
        }
        else {
            System.out.println("Aluno adicionado com sucesso");
            this.alunos.add(a);
        }
    }
    public Professor getProfessor() {
        return professor;
    }
    public Vector<Aluno> getAlunos() {
        return alunos;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public void setAlunos(Vector<Aluno> alunos) {
        this.alunos = alunos;
    }
    @Override
    public String toString() {
        String res = "Orientação| " + super.toString() + ", Professor Orientador: "+ professor.getNome()+ ", Orientandos: ";
        for (int i = 0; i < alunos.size(); i++) {
            res+= alunos.elementAt(i).getNome();
            if(i<alunos.size()-1) res+=", ";
        }
        return res;
    }
}