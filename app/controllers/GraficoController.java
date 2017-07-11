package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlQuery;
import models.Cliente;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by William on 09/07/2017.
 */
@SubjectPresent
public class GraficoController extends Controller {
    public Result index() {
        return ok(views.html.funcionario.grafico.render());
    }




}
