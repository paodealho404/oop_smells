package model;
import java.util.Collections;
import java.util.Vector;
import model.strategypattern.IStrategyRelatorio;
public class Colaborador extends Usuario{
   protected Vector<Publicacao> publicacao;
   protected Vector<Projeto> projetos;
   IStrategyRelatorio relatorio;
   protected int andamento;
   public Colaborador( String nome, String email)
   {
      super(nome, email);
      this.andamento = 0;
      this.projetos = new Vector<Projeto>();
      this.publicacao = new Vector<Publicacao>();
   }
   public Colaborador( String nome, String email, IStrategyRelatorio relatorio)
   {
      super(nome, email);
      this.andamento = 0;
      this.projetos = new Vector<Projeto>(); 
      this.publicacao = new Vector<Publicacao>();
      this.relatorio = relatorio;
   }
   public void setRelatorio(IStrategyRelatorio relatorio) {
      this.relatorio = relatorio;
   }
   public Vector<Publicacao> getPublicacao() {
      return publicacao;
   }
   public void checkProjetos() {
      int num = 0;
      for (int i = 0; i < projetos.size(); i++) {
         if(projetos.elementAt(i).getStatus().equals("Em andamento")) num++;
      }
      setAndamento(num);
   }
   public int getAndamento() {
      checkProjetos();
      return andamento;
   }
   public Vector<Projeto> getProjetosParticipados(){
      Vector<Projeto> projs = new Vector<Projeto>();
      for (int i = 0; i < projetos.size(); i++) {
         if(!projetos.elementAt(i).getStatus().equals("Em elaboração")) projs.add(projetos.elementAt(i));
      }
      return projs;
   }
   public void setAndamento(int andamento) {
      this.andamento = andamento;
   }
   public Vector<Projeto> getProjetos() {
      return projetos;
   }
   public void setProjetos(Vector<Projeto> projetos) {
      this.projetos = projetos;
   }
   public void setPublicacao(Vector<Publicacao> publicacao) {
      this.publicacao = publicacao;
   }
   public String appendNome() {
      String res = "Nome: "  + super.getNome();
      return res;
   }
   public String appendEmail() {
      String res = ", Email: "  + super.getEmail();
      return res;
   }

   public String appendUserInfo() {
      return appendNome() + appendEmail();
   }
   @Override
   public String toString() {
      return appendUserInfo();
   }
   public Vector<Projeto> ordenarProjetos() {
      Vector<Projeto> projetosValidos = new Vector<Projeto>(getProjetosParticipados());
      Collections.sort(projetosValidos, Collections.reverseOrder());
      return projetosValidos;
   }
   public String returnProjetosValidos() {
      String res="";
      Vector<Projeto> projetosValidos = ordenarProjetos();
      if(projetosValidos.size()==0) res+="Nenhum\n";
      else {
         res+="\n";
         for (int i = 0; i < projetosValidos.size(); i++) {
            res += projetosValidos.elementAt(i).getTitulo();
            res +=",\n";
         }
      }
      return res;
   }
   public String relatorioProdutividade()
   {
      return relatorio.gerarRelatorio();
   }
}