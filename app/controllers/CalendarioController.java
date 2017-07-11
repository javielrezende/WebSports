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
 * Created by Miguel on 07/05/2017.
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
     *
     * @return view.funcionario.index
     */
    public Result index() {
        return ok(views.html.funcionario.index.render());
    }


    /**
     * @return a lista de todos registros dentro do calendario em formato JSON
     */

    public Result json()  {
        List<Calendario> lista = Calendario.find.all();
        return ok(Json.toJson(lista));
    }

    /**
     * Faz a atualização de um registro
     * @return OK
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
     * Cria um registro novo
     * @return OK
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


    /**
    * Criação de rotas para usar dentro do javascript
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

    public Result delete(Integer id){
        // Busco o id no BD
        // Deleto o registro
        Calendario.find.ref(id).delete();


        return ok();
    }

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