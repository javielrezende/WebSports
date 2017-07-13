package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import be.objectify.deadbolt.java.actions.Unrestricted;
import com.fasterxml.jackson.databind.JsonNode;

import play.data.FormFactory;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.*;
import models.Calendario;
import play.mvc.Controller;
import play.routing.JavaScriptReverseRouter;

import javax.inject.Inject;

import java.util.*;

/**
 * @author Miguel, Roger, William
 *
 * Controller para funcionamento do calendario.
 * <p><b>Só funciona se estiver logado</b></p>
 */
@SubjectPresent
public class CalendarioController extends Controller {
    @Inject
    private FormFactory formFactory;

    private final HttpExecutionContext ec;

    @Inject
    public CalendarioController(HttpExecutionContext ec) {
        this.ec = ec;
    }


    /**
     * Método para renderizar a view de login.
     *
     * <strong><p>Route: / </p></strong>
     *
     * @return view.funcionario.index
     */
    public Result index() {
        return ok(views.html.funcionario.index.render());
    }


    /**
     * <p>Método para o funcionamento do calendario</p>
     * Retorna todos os registros no banco para preencher o calendario
     * @return Json com todos os registros do calendario
     */
    public Result json()  {
        List<Calendario> lista = Calendario.find.all();
        return ok(Json.toJson(lista));
    }

    /**
     * Método para atualizar o registro no banco
     * @return Json com o registro encontrado
     */
    public Result update() {
        // Recebe um JSON contendo os campos do formulário
        JsonNode body = request().body().asJson();

        // Acha a reserva atual através do ID recebido por Json
        Calendario calendario = Calendario.find.byId(body.findPath("id").asInt());

        // Muda os valores dos atributos da reserva
        // body.findPath("Campo do formulario").comoString
        calendario.title = body.findPath("title").asText();
        calendario.setStart(body.findPath("start").asText());
        calendario.setEnd(body.findPath("end").asText());
        calendario.color = body.findPath("color").asText();

        // Realiza o Update
        calendario.update();

        // Apenas um retorno que não vai ser mostrado na página
        return ok(Json.toJson(calendario));
    }
    /**
     * <p>Método para salvar os dados recebidos no banco</p>
     * Recebe os dados através de AJAX com JSON
     * @return dados recebidos por AJAX em JSON
     */
    public Result save() {
        // Recebe um JSON contendo os campos do formulário
        JsonNode body = request().body().asJson();

        // Cria uma reserva nova
        Calendario calendario = new Calendario();

        // Atribui os valores recebidos aos atributos da reserva
        // body.findPath("Campo do formulario").comoString
        calendario.title = body.findPath("title").asText();
        calendario.setStart(body.findPath("start").asText());
        calendario.setEnd(body.findPath("end").asText());
        calendario.color = "#990000";

        // Salva um novo registro
        calendario.save();

        // Apenas um retorno que não vai ser mostrado na página
        return ok(body);
    }




    public Result delete(Integer id){
        // Busco o id no BD
        // Deleto o registro
        Calendario.find.ref(id).delete();


        return ok();
    }
    /**
     *
     * <p>Método para criação das javascriptRoutes</p>
     * @implNote Criação de rotas para usar dentro do javascript
     *           Sempre que for usar um dos metodos do controller dentro do javascript,
     *           Precisa adicionar o método dentro desse aqui
     *           Exemplo: Se eu precisasse chamar um metodo delete lá no Javascript
     *           Teria que adicionar uma rota nova para o método e
     *           adicionar uma linha no método abaixo.
     *           Ficaria:
     *           JavaScriptReverseRouter.create("jsRoutes",
     *                routes.javascript.CalendarioController.update(),
     *                routes.javascript.CalendarioController.save(),
     *                routes.javascript.CalendarioController.delete(),
     *           )
     *
     * @return Criação de todas javascriptRoutes
     */
    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.CalendarioController.update(),
                        routes.javascript.CalendarioController.save(),
                        routes.javascript.FuncionarioController.edit(),
                        routes.javascript.ClienteController.edit(),
                        routes.javascript.ClienteController.indexJson(),
                        routes.javascript.CalendarioController.delete(),
                        routes.javascript.ClienteController.grafJson()

                )
        ).as("text/javascript");
    }

}