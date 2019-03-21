package br.edu.ifpb.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/03/2019, 07:29:05
 */
@Named("controlador")
@RequestScoped
public class ControladorProdutos {

    @Inject
    private ConsumidorDeProdutos produtos;// = new ConsumidorDeProdutos();
    private Item item = new Item();
    private List<Item> lista;

    @PostConstruct
    public void init() {
        lista = this.produtos.todosOsProdutos();
    }

    public String editar(int id) {
        this.item = this.produtos.produtoComId(
            String.valueOf(id)
        );
        return null;
    }

    public String excluir(int id) {
        this.produtos.removerProdutoComId(
            String.valueOf(id)
        );
        this.lista = this.produtos.todosOsProdutos();
        return null;
    }

    public String atualizar() {
        Item salvarProduto = this.produtos.atualizarProduto(item);
        this.lista = this.produtos.todosOsProdutos();
        this.item = new Item();
        return null;
    }

    public String salvar() {
        Item salvarProduto = this.produtos.salvarProduto(item);
        this.lista = this.produtos.todosOsProdutos();
        this.item = new Item();
        return null;
    }

    public List<Item> todosOsProdutos() {
        return this.lista;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

//    private String mensagem;
//    private String produto;
//    public String ler() {
//        this.mensagem = this.produtos.todosOsProdutos();
//        this.item = this.produtos.produtoComId(produto);
//        return null;
//    }
//    public String getMensagem() {
//        return mensagem;
//    }
//
//    public void setMensagem(String mensagem) {
//        this.mensagem = mensagem;
//    }
//
//    public String getProduto() {
//        return produto;
//    }
//
//    public void setProduto(String produto) {
//        this.produto = produto;
//    }
}
