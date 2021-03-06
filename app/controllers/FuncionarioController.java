package controllers;

import models.Endereco;
import models.Funcionario;
import models.Usuario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import scala.collection.script.End;
import views.html.funcionario.*;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Miguel, Roger, William  on 17/05/2017.
 *
 * Controller do funcionario.
 *
 * Nesse controller é feito o crud da view do funcionario,
 * e traz e envia dados para o banco de dados.
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
        return TODO;
    }

    public Result update(Integer id) {
        return TODO;
    }

    public Result delete(Integer id) {
        return TODO;
    }
}
