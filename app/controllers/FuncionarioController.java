package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import be.objectify.deadbolt.java.actions.Unrestricted;
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
 * Created by Miguel, Roger, William  on 17/05/2017.
 *
 * Controller da view funcionanrio para fazer o crud
 */
@Unrestricted
public class FuncionarioController extends Controller {

    @Inject
    private FormFactory formFactory;

    /**
     *
     * @return se ok retorna para funcionario_list
     */
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


    /**
     *
     * @return retorna para index
     */
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

    /**
     *
     * @param id do funcionario
     * @return retorna um json com id funcionario
     */
    public Result edit(Integer id) {
        List<Funcionario> funcionario = Funcionario.find
                .fetch("cargo_id")
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        Funcionario funcionario1 = Funcionario.find.byId(id);
        int idFunc = funcionario.indexOf(funcionario1);
        return ok(Json.toJson(funcionario.get(idFunc)));
    }

    /**
     *
     * @param id do funcionario
     * @return retorna para index
     */
    public Result update(Integer id) {
        //Recebe os dados do formulario
        DynamicForm form = formFactory.form().bindFromRequest();

        Funcionario funcionarioSalvo = Funcionario.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").where().idEq(id).findUnique();

        funcionarioSalvo.usuario_id.endereco_id.setRua(form.get("endereco"));
        funcionarioSalvo.usuario_id.endereco_id.setNumero(form.get("numero"));
        funcionarioSalvo.usuario_id.endereco_id.setComplemento(form.get("complemento"));
        funcionarioSalvo.usuario_id.endereco_id.setCep(form.get("cep"));
        funcionarioSalvo.usuario_id.endereco_id.setCidade_id(form.get("cidade"));
        funcionarioSalvo.usuario_id.endereco_id.setBairro(form.get("bairro"));

        funcionarioSalvo.usuario_id.setNome(form.get("nome"));
        funcionarioSalvo.usuario_id.setEmail(form.get("email"));
        funcionarioSalvo.usuario_id.setSenha(form.get("senha"));
        funcionarioSalvo.usuario_id.setCpf(form.get("cpf"));

        funcionarioSalvo.update();
        funcionarioSalvo.usuario_id.update();
        funcionarioSalvo.usuario_id.endereco_id.update();

        return index();
    }

    /**
     *
     * @param id do funcionario
     * @return retorna para index
     */

    public Result delete(Integer id) {
        Funcionario.find.ref(id).delete();

        return index();
    }


}
