package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import models.Calendario;
import play.routing.JavaScriptReverseRouter;

import javax.inject.Inject;
import java.text.ParseException;
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
    public Result json() throws ParseException {
        List<Calendario> lista = Calendario.find.all();
        return ok(Json.toJson(lista));
    }
    // TODO
    public Result update() {
        JsonNode body = request().body().asJson();
        //Calendario reserva = Calendario.find.byId(body.findValue("id").asInt());
        Calendario reserva = Calendario.find.byId(body.findPath("id").asInt());
        reserva.id = body.findPath("id").asInt();
        reserva.title = body.findPath("title").asText();
        reserva.start ="2017-05-01T14:00:00";
        reserva.end = "2017-05-01T15:00:00";
        reserva.color = body.findPath("color").asText();
        System.out.println(reserva.id);
        System.out.println(reserva.title);
        System.out.println(reserva.start);
        System.out.println(reserva.end);
        System.out.println(reserva.color);

        return ok(Json.toJson(reserva));
    }

    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.CalendarioController.update()
                )
        ).as("text/javascript");
    }

}