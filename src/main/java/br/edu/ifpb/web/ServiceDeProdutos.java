package br.edu.ifpb.web;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/03/2019, 09:12:40
 */
public interface ServiceDeProdutos {

    Item atualizarProduto(Item item);

    Item produtoComId(String id);

    Item salvarProduto(Item item);

    List<Item> todosOsProdutos();

}
