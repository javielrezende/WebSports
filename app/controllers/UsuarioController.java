package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Transaction;
import models.Usuario;
import play.data.*;

import static play.data.Form.*;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;


/**
 * Created by Miguel, Roger, William on 17/04/2017.
 *
 * Crud do usuario, onde é feito o cadastro do usuario e a busca e envio de dados para o banco.
 *
 * try catch para ver se foi enviado os dados, se ok aparece a mensagem usuario cadastrado, senão a mensagem de erro.
 */
public class UsuarioController extends Controller {
    @Inject
    private FormFactory formFactory;


    public Result index() {
        return TODO;
    }


    public Result GO_HOME = Results.redirect(
            routes.UsuarioController.index()
    );

    public Result create() {
        return ok(views.html.form.render("Teste"));
    }

    public Result save() {
        Form<Usuario> form = formFactory.form(Usuario.class);
        Usuario usuario = form.bindFromRequest().get();
        if (form.hasErrors()) {
            return badRequest(views.html.form.render("Erro"));
        }
        usuario.save();
        flash("success", "Usuario" + usuario.email + "foi criado com sucesso");
        return GO_HOME;
    }

    public Result edit(Integer id) {
        Usuario user = Usuario.find.byId(id);
        return ok(views.html.formEdit.render(id, "", user));
    }

    public Result update(Integer id) {
        Form<Usuario> form = formFactory.form(Usuario.class);
        Usuario usuario = form.bindFromRequest().get();
        if (form.hasErrors()) {
            return badRequest(views.html.form.render("Erro"));
        }


        Transaction txn = Ebean.beginTransaction();
        try {
            Usuario usuarioSalvo = Usuario.find.byId(id);
            if (usuarioSalvo != null) {
                usuarioSalvo.email = usuario.email;
                usuarioSalvo.senha = usuario.senha;
                usuarioSalvo.update();
                flash("success", "Usuario " + usuarioSalvo.email + " foi atualizado");
                txn.commit();
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e);
        } finally {
            txn.end();
        }

        return GO_HOME;
    }

    public Result delete(Integer id) {
        Usuario.find.ref(id).delete();
        flash("success", "O usuario foi deletado");
        return GO_HOME;
    }

    public Result list(int page) {
        return ok(views.html.index.render(Usuario.page(page, 10)));
    }
}