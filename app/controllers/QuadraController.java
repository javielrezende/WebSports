package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Miguel, Roger, William.
 *
 * Controller do crud da Quadra.
 * <p><b>Só funciona se estiver logado</b></p>
 */
@SubjectPresent
public class QuadraController extends Controller {
    /**
     * Método para entrar na view Quadra
     * @return view.listarQuadras
     */
    public Result index() {
        return ok(views.html.funcionario.quadra_list.render());
    }

//    public Result create() {
//        return TODO;
//    }
//
//    public Result save() {
//        return TODO;
//    }
//
//    public Result edit(Integer id) {
//        return TODO;
//    }
//
//    public Result update(Integer id) {
//        return TODO;
//    }
//
//    public Result delete(Integer id) {
//        return TODO;
//    }
}
