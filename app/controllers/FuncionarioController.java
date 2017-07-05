package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import models.Cliente;
import models.Endereco;
import models.Funcionario;
import models.Usuario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import play.routing.JavaScriptReverseRouter;
import views.html.funcionario.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Roger Rezende on 17/05/2017.
 */
public class FuncionarioController extends Controller {

    @Inject
    private FormFactory formFactory;


    public Result index() {
        List<Funcionario> funcionario = Funcionario.find
                .fetch("cargo_id")
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id")
                .findList();
        return ok(funcionario_list.render(funcionario));
    }

    public Result INDEX = Results.redirect(
            routes.FuncionarioController.index()
    );


    public Result create() {
        return TODO;
    }

    public Result save() {
        DynamicForm form = formFactory.form().bindFromRequest();
        Funcionario funcionario = new Funcionario();
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


        funcionario.setUsuario_id(usuario_id);
        funcionario.setCargo_id(1);
        System.out.println(funcionario);
        funcionario.save();

        return INDEX;
    }


    public Result edit(Integer id) {
        List<Funcionario> funcionario = Funcionario.find
                .fetch("cargo_id")
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        Funcionario funcionario1 = Funcionario.find.byId(id);
        int idFunc = funcionario.indexOf(funcionario1);
        return ok(Json.toJson(funcionario.get(idFunc)));
    }

    public Result update(Integer id) {

        //Recebe os dados do formulario
        DynamicForm form = formFactory.form().bindFromRequest();

        Transaction txn = Ebean.beginTransaction();
        try {
            Funcionario funcionarioSalvo = Funcionario.find.byId(id);
            Endereco enderecoSalvo = funcionarioSalvo.usuario_id.endereco_id;
            Usuario usuarioSalvo = funcionarioSalvo.usuario_id;

            if (funcionarioSalvo != null) {
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

                funcionarioSalvo.update();
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
        Funcionario.find.ref(id).delete();

        return index();
    }


}
