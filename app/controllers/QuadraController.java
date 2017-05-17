package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by Miguel on 15/05/2017.
 */
public class QuadraController extends Controller {
    public Result index() {
        return ok(views.html.funcionario.quadra_list.render());
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
