package model;

import java.util.Collections;
import java.util.Vector;

public class Pesquisador extends Colaborador {
    private String vinculo;
    public Pesquisador(String nome, String email) {
        super(nome, email);
    }
    public String getVinculo() {
        return vinculo;
    }
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
    @Override
    public String toString() {
        String res = "Pesquisador| ";
        res+= super.toString();
        return res;
    }
    public String relatorioProdutividade() {
        String res=super.relatorioProdutividade();
        res+= "Produção Academica associada: ";    
        Vector<ProducaoAcademica> producoes = new Vector<ProducaoAcademica>(super.getPublicacao());   
        if(producoes.size()==0) res+="Nenhuma";
        else {
            res+="\n";
            Collections.sort(producoes, Collections.reverseOrder());
            for (int i = 0; i < producoes.size(); i++) {
                res+=producoes.elementAt(i)+"\n";
            }
        }
        return res;
    }
}
