package controllers;

import models.Endereco;
import models.Funcionario;
import models.Usuario;
import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import views.html.funcionario.*;

import javax.inject.Inject;

/**
 * Created by Programador on 17/05/2017.
 */
public class FuncionarioController extends Controller {

    @Inject
    private FormFactory formFactory;



    public Result index() {
        return ok(funcionario_list.render());
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

        int endereco_id = Endereco.find.nextId();

        endereco.rua = form.get("endereco");
        endereco.setNumero(form.get("numero"));
        endereco.complemento = form.get("complemento");
        endereco.cep = form.get("cep");
        endereco.setCidade_id(form.get("cidade"));

        endereco.save();

        int usuario_id = Usuario.find.nextId();
        usuario.nome = form.get("nome");
        usuario.email = form.get("email");
        usuario.senha = form.get("senha");
        usuario.cpf = form.get("cpf");
        usuario.setEndereco_id(endereco_id);

        usuario.save();

        funcionario.setUsuario_id(usuario_id);
        funcionario.setCargo_id(1);

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
