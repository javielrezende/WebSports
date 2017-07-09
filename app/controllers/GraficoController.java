package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by William on 09/07/2017.
 */
@SubjectPresent
public class GraficoController extends Controller {
    public Result index() {
        return ok(views.html.funcionario.grafico.render());
    }

    public Result create() {
        return TODO;
    }

    public Result save() {
        return TODO;
    }

    public Result edit(Integer id) {
        return TODO;
    }

    public Result update(Integer id) {
        return TODO;
    }

    public Result delete(Integer id) {
        return TODO;
    }
}
