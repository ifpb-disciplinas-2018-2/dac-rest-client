package br.edu.ifpb.web;

//import javax.json.bind.Jsonb;
//import javax.json.bind.JsonbBuilder;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 21/03/2019, 07:40:44
 */
@Stateless
public class ConsumidorDeProdutos implements ServiceDeProdutos {

    private final String url = "http://localhost:8080/dac-webservice/api/produtos";
    private final Client client = ClientBuilder.newClient();
    private final WebTarget produtos = client.target(url);

    @Override
    public List<Item> todosOsProdutos() {
        Response resposta = produtos.request().get();
        String json = resposta.readEntity(String.class);
        JsonArray array = Json.createReader(
            new StringReader(json)
        ).readArray();

        return array.stream()
            .map(obj -> new Item((JsonObject) obj))
            .collect(Collectors.toList());
    }

    @Override
    public Item produtoComId(String id) {
        //http://localhost:8080/dac-webservice/api/produtos/id;
        WebTarget produtoComId = produtos.path("{id}").resolveTemplate("id",id);
        Response resposta = produtoComId.request().get();
        String corpo = resposta.readEntity(String.class);
        return converterPara(corpo);
    }

    @Override
    public Item salvarProduto(Item item) {
        Response resposta = produtos.request().
            post(
                Entity.json(item)
            );

        String json = resposta.readEntity(String.class);
        return converterPara(json);

    }

    @Override
    public Item atualizarProduto(Item item) {
        WebTarget produtoComId = produtos.path("{id}")
            .resolveTemplate("id",item.getId());
        Response resposta = produtoComId.request().
            put(
                Entity.json(item)
            );
        String json = resposta.readEntity(String.class);
        return converterPara(json);

    }

    public void removerProdutoComId(String id) {
        WebTarget produtoComId = produtos.path("{id}")
            .resolveTemplate("id",id);

        Response delete = produtoComId.request().delete();
        System.out.println(delete.getStatus());
    }

    public Item converterPara(String json) {
        JsonObject jsonObject = Json.createReader(
            new StringReader(json)
        ).readObject();

        return new Item(jsonObject);
    }

}
