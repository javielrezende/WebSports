package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
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
@SubjectPresent
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


    public Result delete(Integer id) {

        Cliente.find.ref(id).delete();

        return index();
    }
}
