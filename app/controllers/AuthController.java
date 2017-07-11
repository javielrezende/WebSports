package controllers;

import models.Funcionario;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Result;

import javax.inject.Inject;

import static play.mvc.Controller.session;
import static play.mvc.Results.ok;

/**
 * Created by Miguel, Roger, William on 09/07/2017.
 *
 * Controller para fazer o login do cliente.
 *
 */
public class AuthController {
    @Inject
    private FormFactory formFactory;
    @Inject
    MailerClient mailer;
    public Result index() {
        return ok(views.html.auth.index.render(""));
    }

    public Result login() {
        DynamicForm form = formFactory.form().bindFromRequest();

        Funcionario funcionario = Funcionario.findByEmail(form.get("email"));
        if(funcionario.usuario_id.senha.equals(form.get("password"))) {
            try {
                if (funcionario.usuario_id.email != null) {
                    session("connectedName", funcionario.usuario_id.getNome());
                    session("connected", funcionario.usuario_id.email);
                    return ok(views.html.funcionario.quadra_list.render());
                } else {
                    return index();
                }
            } catch (NullPointerException e) {
                return ok(views.html.auth.index.render("Email ou senha inválidos"));
            }
        } else {
            return ok(views.html.auth.index.render("Email ou senha inválidos"));
        }
    }

    public Result forgotPasswordMail() {
        DynamicForm form = formFactory.form().bindFromRequest();
        String emailFunc = form.get("email");

        Funcionario funcionario = Funcionario.findByEmail(emailFunc);
        if (funcionario != null) {
            final Email email = new Email()
                    .setSubject("Recuperação de Senha")
                    .setFrom("WebSports <admin@websports.com.br>")
                    .addTo(funcionario.usuario_id.getNome() + " <" + emailFunc + ">")
                    .setBodyText("Sua senha é: " + funcionario.usuario_id.senha)
                    .setBodyHtml("<html><body> <p>Sua senha é: " + funcionario.usuario_id.senha + " </p></body></html>");
            String id = mailer.send(email);
            return ok(views.html.auth.index.render("Email enviado com sucesso"));
        } else {
            return ok(views.html.auth.forgot.render("Esse usuário não existe"));
        }
    }

    public Result forgotPassword() {
        return ok(views.html.auth.forgot.render(""));
    }
    public Result logoff() {
        session().clear();
        return index();
    }
}
