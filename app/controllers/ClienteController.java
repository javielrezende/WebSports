package controllers;

import models.Cliente;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.funcionario.*;

import java.util.List;

/*
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
    public Result index() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(cliente_list.render(clientes));
    }

    public Result indexJson() {
        List<Cliente> clientes = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        return ok(Json.toJson(clientes));
    }

    public Result create() {
        return TODO;
    }
    public Result save() {
        return TODO;
    }
    public Result edit(Integer id) {
        List<Cliente> cliente = Cliente.find
                .fetch("usuario_id")
                .fetch("usuario_id.endereco_id").findList();
        Cliente cliente1 = Cliente.find.byId(id);
        int idCli = cliente.indexOf(cliente1);
        return ok(Json.toJson(cliente.get(idCli)));
    }
    public Result update(Integer id) {
        return TODO;
    }
    public Result delete(Integer id) {
        return TODO;
    }
}
