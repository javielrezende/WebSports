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
 * @author Miguel, Roger, William.
 *
 * Controller para fazer o login no sistema.
 *
 */
public class AuthController {
    @Inject
    private FormFactory formFactory;
    @Inject
    MailerClient mailer;

    /**
     * Método para renderizar a view de login.
     *
     * <strong><p>Route: /login </p></strong>
     *
     * @return view.auth
     */
    public Result index() {
        return ok(views.html.auth.index.render(""));
    }

    /**
     * <p>Método para realizar o login</p>
     * Recebe as informações do usuário através do formulario
     * Confirma se os dados recebidos existem no sistema
     * Caso sim, renderiza a index com as sessoes
     * <ul>
     * <li>connectedName</li>
     * <li>connected</li>
     * </ul>
     * Caso não
     * @return view.auth
     *
     */
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

    /**
     * <p>Método para recuperação de senha</p>
     * Recebe os dados por formulario,
     * acha esses dados através do email,
     * caso localize
     *
     * @return Email com a senha
     *
     */
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

    /**
     * Método para entrar na view de esqueceu sua senha
     * @return view.forgot
     */
    public Result forgotPassword() {
        return ok(views.html.auth.forgot.render(""));
    }

    /**
     * Método para fazer logoff
     * @return view.auth
     */
    public Result logoff() {
        session().clear();
        return index();
    }
}
