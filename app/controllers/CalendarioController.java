package controllers;

import play.mvc.*;
import models.Calendario;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Miguel on 07/05/2017.
 */
public class CalendarioController extends Controller {


    public Result index() {
        return ok(views.html.funcionario.index.render());
    }


    /*
     * @return a lista de todos registros dentro do calendario em formato JSON
     */
    public Result json() throws ParseException {
        List<Calendario> lista = Calendario.find.all();
        return ok(play.libs.Json.toJson(lista));
    }

}