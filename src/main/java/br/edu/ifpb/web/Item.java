package br.edu.ifpb.web;

import java.io.Serializable;
import javax.json.JsonObject;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/03/2019, 07:52:22
 */
public class Item implements Serializable {

    private String descricao;
    private int id;
    private double valor;

    public Item() {
    }

    public Item(String descricao,int id,double valor) {
        this.descricao = descricao;
        this.id = id;
        this.valor = valor;
    }
    public Item(JsonObject json) {
        this.descricao = json.getString("descricao");
        this.id = json.getInt("id");
        this.valor = json.getJsonNumber("valor").doubleValue();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Item{" + "descricao=" + descricao + ", id=" + id + ", valor=" + valor + '}';
    }
    
    

}
