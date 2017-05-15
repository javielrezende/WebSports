package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.funcionario.*;
/**
 * Created by Miguel on 14/05/2017.
 *
 *   public Result create() {
 *      return TODO;
 *   }
 *   public Result save() {
 *      return TODO;
 *   }
 *   public Result edit() {
 *      return TODO;
 *   }
 *    public Result update() {
 *      return TODO;
 *   }
 *   public Result delete() {
 *      return TODO;
 *   }
 */
public class ClienteController extends Controller {
    public Result index() {return ok(cliente_list.render());}
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
