package controllers;

import be.objectify.deadbolt.java.actions.SubjectPresent;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * @author Miguel, Roger, William
 *
 * Controller do funcionamento do grafico.
 * <p><b>Só funciona se estiver logado</b></p>
 */
@SubjectPresent
public class GraficoController extends Controller {
    /**
     * Método para entrar na view de gráfico
     * @return view.grafico
     */
    public Result index() {
        return ok(views.html.funcionario.grafico.render());
    }
}
