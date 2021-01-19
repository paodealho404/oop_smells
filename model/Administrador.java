package model;

import java.util.Vector;

import model.commandpattern.ICommandProjeto;

public class Administrador extends Usuario{
    private String login;
    private String senha;
    private ICommandProjeto mudancaProjeto;
    public Administrador( String nome, String email, String login, String senha)
    {
        super(nome,email);
        setLogin(login);
        setSenha(senha);
    }
    public String getLogin() {
        return login;
    }
    public String getSenha() {
        return senha;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public String toString() {
        return "{" + super.getEmail() + ", "+ super.getNome() + "}";
    }
    public boolean logar(String senha)
    {
        if(this.senha.equals(senha)) return true;
        else{
            System.out.println("Senha incorreta");
            return false;
        }
    }
    
    public void setMudancaProjeto(ICommandProjeto novaMudanca) {
        this.mudancaProjeto = novaMudanca;
    }
    public void mudarStatusProjeto() {
        mudancaProjeto.mudarStatus();
    }  
    
    public void cadastroProjetoLab(Projeto p, LabPesquisa lab) {
        if( p.isProjectValid() && p.getStatus().equals("Em elaboração")) {
            if(!lab.getProjetos().contains(p)) lab.addProjeto(p);
        }
        else System.out.println("Projeto inválido");
    }
    
    public void addPublicacaoProjeto(Publicacao pb, Projeto proj) {
        Vector<Publicacao> publicacoes = proj.getPublicacoes();
        publicacoes.add(pb);
        proj.setPublicacoes(publicacoes);
    }
    public void addPublicacaoLabPesquisa(Publicacao p, LabPesquisa lab) {
        Vector<Publicacao> publicacoes = lab.getPublicacoes();
        if(p.getProjeto().getStatus().equals("Em andamento")) {
            if(!publicacoes.contains(p)){
                publicacoes.add(p);
                System.out.println("Publicação "+p.getTitulo()+" adicionada com sucesso!");
                lab.setPublicacoes(publicacoes);
                addPublicacaoProjeto(p, p.getProjeto());
                addPublicacaoColaboradores(p, p.getAutores());
            }
            else {
                System.out.println("Publicação já existente");
            }
            
        }
        else {
            System.out.println("Não foi possível adicionar a Publicação. Projeto não está em fase de andamento");
        }
    }
    public void addPublicacaoColaboradores(Publicacao p, Vector<Colaborador> colaboradores) {
        Vector<Colaborador> novosColaboradores = new Vector<Colaborador>(colaboradores);
        novosColaboradores.forEach(colaborador->{
            addPublicacaoColaborador(p, colaborador);
        });
        p.setAutores(novosColaboradores);
    }
    public void addPublicacaoColaborador(Publicacao p, Colaborador c) {
        Vector<Publicacao> colabPublicacao = c.getPublicacao();
        if(!colabPublicacao.contains(p)) {
            colabPublicacao.add(p);
        }
    }
    public void addOrientacaoProfessor(Orientacao o, Professor p) {
        Vector<Orientacao> orientacoes = new Vector<Orientacao>(p.getOrientacao());
        if(!orientacoes.contains(o)) {
            System.out.println("Orientação adicionada");
            orientacoes.add(o);
            p.setOrientacao(orientacoes);
        }
    }
    public void addProjetoColaboradores(Projeto p, Vector<Colaborador> colaboradores) {
        Vector<Colaborador> novosColaboradores = new Vector<Colaborador>(colaboradores);
        novosColaboradores.forEach(colab->{
            addProjetoColaborador(p, colab);
        });
        p.setParticipantes(novosColaboradores);
    }
    public void addColaboradorProjeto(Colaborador c, Projeto p) {
        if(p.getParticipantes().contains(c)) {
            System.out.println("Colaborador já está no projeto");
            return;
        }
        else {
            p.getParticipantes().add(c);
            System.out.println("Colaborador adicionado com sucesso ao Projeto!");
        }
    }

    public void addProjetoColaborador(Projeto p, Colaborador c) {
        if(!p.getStatus().equals("Em elaboração")) {
            System.out.println("Não foi possível inserir colaborador. Projeto não está mais em fase de elaboração");
            return;
        }
        Vector<Projeto> projetos = c.getProjetos();
        if(projetos.contains(p)) {
            System.out.println("Colaborador já possui o projeto na sua lista");
            return;
        } 
        else {
            if(c instanceof Aluno){
                Aluno aux = (Aluno) c;
                if(aux.getTipo().equals("Graduação")) {
                    if(aux.getAndamento()>=2) System.out.println("Não é possível inserir o aluno "+ aux.getNome() + ". Capacidade máxima de projetos em andamento atingida");
                    else {
                        projetos.add(p);
                        c.setProjetos(projetos);
                        System.out.println("Projeto adicionado com sucesso ao colaborador!");
                        addColaboradorProjeto(c, p);
                        
                    }
                }
                else {
                    projetos.add(p);
                    c.setProjetos(projetos);
                    System.out.println("Projeto adicionado com sucesso ao colaborador!");
                    addColaboradorProjeto(c, p);
                }
            }
            else {
                projetos.add(p);
                c.setProjetos(projetos);
                System.out.println("Projeto adicionado com sucesso ao colaborador!");
                addColaboradorProjeto(c, p);
            }
            
        }
        
    }
}
