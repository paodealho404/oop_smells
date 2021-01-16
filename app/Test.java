package app;

import model.*;
import model.commandPattern.*;
import java.time.LocalDate;
import java.util.Vector;
public class Test {
    public static void main(String[] args) {

        Administrador admin = new Administrador("Fulano Beltrano", "fulanhinho@gmail.com", "beltraninho", "senha");
        LabPesquisa lab = new LabPesquisa("LabTeste", admin);

        Colaborador colab1 = new Professor("Jose", "jose@gmail.com");
        Colaborador colab2 = new Aluno("Paulo", "paulo@gmail.com", "Graduação");
        Colaborador colab3 = new Aluno("Fabiana", "fabiana@gmail.com", "Mestrado");
        Colaborador colab4 = new Aluno("Marcos", "marcos@gmail.com", "Doutorado");
        Colaborador colab5 = new Aluno("Vitor", "vitor@gmail.com", "Graduação");
        Colaborador colab6 = new Professor("Leonardo", "leo@gmail.com");
        Colaborador colab7 = new Aluno("Rosa", "rosa@gmail.com", "Graduação");
        Colaborador colab8 = new Aluno("Marta", "marta@uol.com.br", "Mestrado");
        Colaborador colab9 = new Aluno("Ruan", "ruan@ig.com.br", "Graduação");

        System.out.println("\n\nExecutando procedimentos...\n\n");
        System.out.println("\n\nAdicionando colaboradores de teste ao laboratório");
        
        lab.addColaborador(colab1);
        lab.addColaborador(colab2);
        lab.addColaborador(colab3);
        lab.addColaborador(colab4);
        lab.addColaborador(colab5);
        lab.addColaborador(colab6);
        lab.addColaborador(colab7);
        lab.addColaborador(colab8);
        lab.addColaborador(colab9);

        Vector<Colaborador> grupo1 = new Vector<Colaborador>();
        Vector<Colaborador> grupo2 = new Vector<Colaborador>();
        Vector<Colaborador> grupo3 = new Vector<Colaborador>();
        
        grupo1.add(colab1);
        grupo1.add(colab2);
        grupo1.add(colab3);

        grupo2.add(colab4);
        grupo2.add(colab1);
        grupo2.add(colab5);
        

        grupo3.add(colab6);
        grupo3.add(colab7);
        grupo3.add(colab8);
        grupo3.add(colab9);

        LocalDate dataIni1 =  LocalDate.of(2000,02,10), dataFim1 = LocalDate.of(2000,03,10);
        LocalDate dataIni2 = LocalDate.of(2001,03,11), dataFim2 = LocalDate.of(2011,04,11);
        LocalDate dataIni3 = LocalDate.of(2001,12,04), dataFim3 = LocalDate.of(2009,05,13);

        
        Projeto proj1 = new Projeto("BIOTECNOLOGIA", dataIni1, dataFim1, "CNPQ", 4800, "objetivo", "descricao", grupo1);
        Projeto proj2 = new Projeto("TECNOBIOLOGIA", dataIni2, dataFim2, "CNPQ", 4800, "objetivo", "descricao", grupo2);
        Projeto proj3 = new Projeto("USO DE PLANTAS", dataIni3, dataFim3, "CNPQ", 4800, "objetivo", "descricao", grupo3);


        System.out.println("Adicionando os projetos de teste ao laboratório\n");
        admin.cadastroProjetoLab(proj1, lab);
        admin.cadastroProjetoLab(proj2, lab);
        admin.cadastroProjetoLab(proj3, lab);


        System.out.println("\n\nTentando adicionar o projeto aos colaboradores\n");
        admin.addProjetoColaboradores(proj1, proj1.getParticipantes());
        admin.addProjetoColaboradores(proj2, proj2.getParticipantes());
        admin.addProjetoColaboradores(proj3, proj3.getParticipantes());

        
        

        Vector<Aluno> grupoOrientandos1 = new Vector<Aluno>();
        Vector<Aluno> grupoOrientandos2 = new Vector<Aluno>();

        grupoOrientandos1.add((Aluno)colab2);
        grupoOrientandos1.add((Aluno)colab3);
        grupoOrientandos2.add((Aluno)colab8);
        grupoOrientandos2.add((Aluno)colab9);
        
        Vector<Colaborador> autores1 = new Vector<Colaborador>();
        autores1.addAll(grupoOrientandos1);
        autores1.add(colab1);

        Vector<Colaborador> autores2 = new Vector<Colaborador>();
        autores2.addAll(grupoOrientandos2);
        autores2.add(colab6);


        //É pra dar certo
        
        Publicacao p1 = new Publicacao("PLANTINHAS1","CONFERENCIA 1", 2000, lab.getProjeto("BIOTECNOLOGIA"), autores1);
        Publicacao p2 = new Publicacao("PLANTINHAS2","CONFERENCIA 2", 2001, lab.getProjeto("TECNOBIOLOGIA"), autores2);
        Publicacao p3 = new Publicacao("NÃO É MAIS SOBRE PLANTAS", "CONFERENCIA 3", 2010, lab.getProjeto("USO DE PLANTAS"), autores2);
        Publicacao p4 = new Publicacao("É SOBRE PLANTAS", "CONFERENCIA 4", 2009, lab.getProjeto("USO DE PLANTAS"), autores2);

        Orientacao o1 = new Orientacao("DISSERTAÇÃO", 2000, (Professor)lab.getColaborador("Jose"), grupoOrientandos1);
        Orientacao o2 = new Orientacao("DISSERTAÇÃO", 2001, (Professor)lab.getColaborador("Leonardo"), grupoOrientandos2);
        Orientacao o3 = new Orientacao("ANÁLISE", 2002, (Professor)lab.getColaborador("Jose"), grupoOrientandos2);
        

        System.out.println("\n\nIniciando próximos testes\n\nMudando estados dos projetos criados anteriormente");
        ProjetoAndamento novaMudanca1 = new ProjetoAndamento(lab.getProjeto("BIOTECNOLOGIA"));
        ProjetoAndamento novaMudanca2 = new ProjetoAndamento(lab.getProjeto("TECNOBIOLOGIA"));
        admin.setMudancaProjeto(novaMudanca1);
        admin.mudarStatusProjeto();
        admin.setMudancaProjeto(novaMudanca2 );
        admin.mudarStatusProjeto();
        
        //É pra dar certo pois ambas as publicações estão associadas a projetos válidos e em andamento
        System.out.println("\nTentando adicionar Publicações de Teste");
        admin.addPublicacaoLabPesquisa(p1, lab);
        admin.addPublicacaoLabPesquisa(p2, lab);

        //É pra dar errado, pois p3 está associada a um projeto em elaboração
        System.out.println("Tentando adicionar publicação 3");
        admin.addPublicacaoLabPesquisa(p3, lab);
        
        //Agora tornando o projeto em andamento para vincular corretamente
        System.out.println("Alterando estado do projeto para tentar vincular publicação");
        ProjetoAndamento novaMudanca3 = new ProjetoAndamento(lab.getProjeto("USO DE PLANTAS"));
        admin.setMudancaProjeto(novaMudanca3);
        admin.mudarStatusProjeto();

        System.out.println("\nTentando adicionar publicação 3 novamente");
        admin.addPublicacaoLabPesquisa(p3, lab);

        System.out.println("\nTentando adicionar publicação 4");
        admin.addPublicacaoLabPesquisa(p4, lab);

        //Adicionando orientações de teste
        System.out.println("\nAdicionando orientações de teste");
        admin.addOrientacaoProfessor(o1, o1.getProfessor());
        admin.addOrientacaoProfessor(o2, o2.getProfessor());
        admin.addOrientacaoProfessor(o3, o3.getProfessor());
        //Concluindo os projetos
        System.out.println("\n\nConcluindo os projetos 1 e 2...\n");
        
        ProjetoConcluido novaMudanca4 = new ProjetoConcluido(proj1);
        admin.setMudancaProjeto(novaMudanca4);
        admin.mudarStatusProjeto();
        ProjetoConcluido novaMudanca5 = new ProjetoConcluido(proj2);
        admin.setMudancaProjeto(novaMudanca5);
        admin.mudarStatusProjeto();

        //Testando os projetos inseridos
        System.out.println("Testando os projetos inseridos\n\n");
        System.out.println(lab.getProjeto("BIOTECNOLOGIA")); 
        System.out.println(lab.getProjeto("TECNOBIOLOGIA")); 
        System.out.println(lab.getProjeto("USO DE PLANTAS")); 

        //Testando os colaboradores inseridos
        System.out.println("\n\nImprimindo todos os colaboradores inseridos no laboratório\n\n");
        lab.getColaboradores().forEach(colaborador->{
            System.out.println(colaborador);
        });

        //Testando as publicacões inseridas
        System.out.println("\n\nImprimindo todas as publicacões inseridas no laboratório\n\n");
        lab.getPublicacoes().forEach(publicacao->{
            System.out.println(publicacao);
        });

        System.out.println("\n\nTOTAL DE PUBLICACOES NO FINAL: "+ lab.getPublicacoes().size());
        System.out.println("TOTAL DE ORIENTAÇÕES NO FINAL: "+ lab.getNumOrientacoes());
        //Relatório laboratório
        System.out.println("\n\nRelatório de Produtividade do laboratório\n\n"+lab.relatorioProdutividade());

        //Relatório colaboradores
       lab.getColaboradores().forEach(colab->{
        System.out.println("\n\nRelatório de Produtividade do colaborador "+colab.getNome()+"\n"+ colab.relatorioProdutividade());
       });

       //Relatório dos Projetos
       lab.getProjetos().forEach(projeto->{
           System.out.println("\n\nRelatório de Produção Academica do Projeto "+ projeto.getTitulo()+"\n"+projeto.relatorioProdutividade());
       });

       System.out.println(lab.relatorioProdutividade());
    }
}
