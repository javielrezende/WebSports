package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import models.Cliente;
import models.Endereco;
import models.Funcionario;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.funcionario.*;

import javax.inject.Inject;
import java.util.List;

/*
 * Created by Miguel on 14/05/2017.
 *
 *   public Result create() {
 *      return TODO;
 *   }
 *   public Result save() {
 *      return TODO;
 *   }
 *   public Result edit() {
 *      return TODO;
 *   }
 *    public Result update() {
 *      return TODO;
 *   }
 *   public Result delete() {
 *      return TODO;
 *   }
 */
public class ClienteController extends Controller {

    @Inject
    private FormFactory formFactory;

    public Result index() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(cliente_list.render(clientes));
    }

    public Result indexJson() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(Json.toJson(clientes));
    }

    public Result create() {
        return TODO;
    }
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

    public Result edit(Integer id) {
        List<Cliente> cliente = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        Cliente cliente1 = Cliente.find.byId(id);
        int idCli = cliente.indexOf(cliente1);
        return ok(Json.toJson(cliente.get(idCli)));
    }
    public Result update(Integer id) {

        //Recebe os dados do formulario
        DynamicForm form = formFactory.form().bindFromRequest();

        Transaction txn = Ebean.beginTransaction();
        try {
            Cliente clienteSalvo = Cliente.find.byId(id);
            Endereco enderecoSalvo = clienteSalvo.usuario_id.endereco_id;
            Usuario usuarioSalvo = clienteSalvo.usuario_id;

            if (clienteSalvo != null) {
                enderecoSalvo.rua = form.get("endereco");
                enderecoSalvo.setNumero(form.get("numero"));
                enderecoSalvo.complemento = form.get("complemento");
                enderecoSalvo.cep = form.get("cep");
                enderecoSalvo.setCidade_id(form.get("cidade"));
                enderecoSalvo.bairro = form.get("bairro");

                usuarioSalvo.nome = form.get("nome");
                usuarioSalvo.email = form.get("email");
                usuarioSalvo.senha = form.get("senha");
                usuarioSalvo.cpf = form.get("cpf");

                clienteSalvo.update();
                flash("success", "Usuario " + usuarioSalvo.email + " foi atualizado");
                txn.commit();
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        } finally {
            txn.end();
        }

        return index();


    }


    public Result delete(Integer id) {

        Cliente.find.ref(id).delete();

        return ok();
    }
}
