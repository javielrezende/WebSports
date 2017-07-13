package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import models.Cliente;
import models.Endereco;
import models.Usuario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.funcionario.*;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Miguel, Roger, William
 *
 * Controller do Crud do funcionario
 * <p><b>Só funciona se estiver logado</b></p>
 */
@SubjectPresent
public class ClienteController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     * <p>Método para entrar na view</p>
     * <b>Route: /cliente </b>
     * @return view.listagemDeClientes com todos clientes achados
     */
    public Result index() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(cliente_list.render(clientes));
    }

    /**
     * Método para enviar um JSON para AJAX
     * <p> Retorna uma lista de clientes em modo JSON para uso com AJAX</p>
     * @return retorna um json do cliente
     */
    public Result indexJson() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(Json.toJson(clientes));
    }


    /**
     * Método para enviar dados ao grafico
     * <p> Retorna qtd de clientes por bairro e os bairros
     * em modo JSON para uso com AJAX</p>
     * @return retorna um json para o grafico
     */
    public Result grafJson() {
        SqlQuery q = Ebean.createSqlQuery("select distinct endereco.bairro, count(cliente.id) as qtdClientes from cliente inner join usuario on cliente.usuario_id = usuario.id inner join endereco on usuario.endereco_id = endereco.id group by bairro");
        List rows = q.findList();
        System.out.println(Json.toJson(rows));
        return ok(Json.toJson(rows));
    }

    /**
     *
     * @return TODO
     */
    public Result create() {
        return TODO;
    }

    /**
     * Método para criar um novo registro de Cliente
     * <p>
     * Cria um registro no banco para clientes recebendo os dados por formulario</p>
     * @return view.cliente_list
     */
    public Result save() {
        //Recebe os dados do formulario
        DynamicForm form = formFactory.form().bindFromRequest();

        Cliente cliente = new Cliente();
        Usuario usuario = new Usuario();
        Endereco endereco = new Endereco();


        endereco.rua = form.get("endereco");
        endereco.setNumero(form.get("numero"));
        endereco.complemento = form.get("complemento");
        endereco.cep = form.get("cep");
        endereco.setCidade_id(form.get("cidade"));
        endereco.bairro = form.get("bairro");
        endereco.save();
        int endereco_id = endereco.id;


        usuario.nome = form.get("nome");
        usuario.email = form.get("email");
        usuario.senha = form.get("senha");
        usuario.cpf = form.get("cpf");
        usuario.setEndereco_id(endereco_id);
        usuario.save();
        int usuario_id = usuario.id;


        cliente.setUsuario_id(usuario_id);
        cliente.save();

        return index();
    }

    /**
     * Método para editar um cliente
     * <p>
     * Recebe um id como parametro para localizar o registro</p>
     * @param id
     * Identifica qual registro e retora um JSON com os valores do registro
     * @return Objeto de cliente em JSON
     */
    public Result edit(Integer id) {
        List<Cliente> cliente = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id")
                .findList();
        Cliente cliente1 = Cliente.find.byId(id);
        int idCli = cliente.indexOf(cliente1);
        return ok(Json.toJson(cliente.get(idCli)));
    }

    /**
     * Método para atualizar um Cliente
     * <p>Recebe o parametro ID para localizar o registro</p>
     * @param id
     * Faz o atualização no banco de dados recebendo os valores por formulario.
     * @return view.index
     */
    public Result update(Integer id) {

        //Recebe os dados do formulario
        DynamicForm form = formFactory.form().bindFromRequest();

        Cliente clienteSalvo = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").where().idEq(id).findUnique();

        System.out.println(form.get("numero"));

        clienteSalvo.usuario_id.endereco_id.setRua(form.get("endereco"));

        clienteSalvo.usuario_id.endereco_id.setNumero(form.get("numero"));
        clienteSalvo.usuario_id.endereco_id.setComplemento(form.get("complemento"));
        clienteSalvo.usuario_id.endereco_id.setCep(form.get("cep"));
        clienteSalvo.usuario_id.endereco_id.setCidade_id(form.get("cidade"));
        clienteSalvo.usuario_id.endereco_id.setBairro(form.get("bairro"));

        clienteSalvo.usuario_id.setNome(form.get("nome"));
        clienteSalvo.usuario_id.setEmail(form.get("email"));
        clienteSalvo.usuario_id.setSenha(form.get("senha"));
        clienteSalvo.usuario_id.setCpf(form.get("cpf"));

        clienteSalvo.update();
        clienteSalvo.usuario_id.update();
        clienteSalvo.usuario_id.endereco_id.update();

        return index();


    }

    /**
     * Método para deletar um Cliente
     * <p>Recebe os o parametro ID para localizar o registro</p>
     * @param id
     * Deleta o registro
     * @return view.index()
     */
    public Result delete(Integer id) {

        Cliente.find.ref(id).delete();

        return index();
    }
}
