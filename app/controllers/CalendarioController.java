package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import models.Calendario;
import play.routing.JavaScriptReverseRouter;

import javax.inject.Inject;

import java.util.*;

/**
 * Created by Miguel on 07/05/2017.
 */
public class CalendarioController extends Controller {
    @Inject
    private FormFactory formFactory;

    public Result index() {
        return ok(views.html.funcionario.index.render());
    }


    /*
     * @return a lista de todos registros dentro do calendario em formato JSON
     */
    public Result json()  {
        List<Calendario> lista = Calendario.find.all();
        return ok(Json.toJson(lista));
    }

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
        calendario.color = body.findPath("color").asText();

        // Salva um novo registro
        calendario.save();

        // Apenas um retorno que não vai ser mostrado na página
        return ok(body);
    }


    /* Criação de rotas para usar dentro do javascript
    * Sempre que for usar um dos metodos do controller dentro do javascript,
    * Precisa adicionar o método dentro desse aqui
    * Exemplo: Se eu precisasse chamar um metodo delete lá no Javascript
    * Teria que adicionar uma rota nova para o método e
    * adicionar uma linha no método abaixo.
    * Ficaria:
    * JavaScriptReverseRouter.create("jsRoutes",
    *      routes.javascript.CalendarioController.update(),
    *      routes.javascript.CalendarioController.save(),
    *      routes.javascript.CalendarioController.delete(),
    * )
    */
    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.CalendarioController.update(),
                        routes.javascript.CalendarioController.save()
                )
        ).as("text/javascript");
    }

}