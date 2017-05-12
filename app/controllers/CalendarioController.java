package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import com.fasterxml.jackson.databind.JsonNode;
import models.Usuario;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.*;
import models.Calendario;
import play.routing.JavaScriptReverseRouter;

import javax.inject.Inject;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
        JsonNode body = request().body().asJson();
        String start = body.findPath("start").asText();
        String end = body.findPath("end").asText();

        List<Calendario> lista = Calendario.find.all();
        start = start.substring(0,19);
        end = end.substring(0,19);

        System.out.println(start);
        System.out.println(end);

        String dml = "update calendario set title=:title, start=:start, end=:end, color=:color where id = :id";
        SqlUpdate update = Ebean.createSqlUpdate(dml)
                .setParameter("title", body.findPath("title").asText())
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("color", body.findPath("color").asText())
                .setParameter("id", body.findPath("id").asText());
        update.execute();
        return ok(Json.toJson(lista));


    }

    public Result save() {
        JsonNode body = request().body().asJson();
        Calendario calendario = new Calendario();
        calendario.title = body.findPath("title").asText();
        calendario.setStart(body.findPath("start").asText());
        calendario.setEnd(body.findPath("end").asText());
        calendario.color = body.findPath("color").asText();
        calendario.save();


        return ok(body);
    }



    public Result javascriptRoutes() {
        return ok(
                JavaScriptReverseRouter.create("jsRoutes",
                        routes.javascript.CalendarioController.update(),
                        routes.javascript.CalendarioController.save()
                )
        ).as("text/javascript");
    }

}