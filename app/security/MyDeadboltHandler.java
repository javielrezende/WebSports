package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Subject;
import controllers.AuthController;
import models.AuthorisedUser;
import models.Funcionario;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

/**
 * @author Miguel
 * Classe que cuida da autenticação
 */
public class MyDeadboltHandler implements DeadboltHandler {
    @Override
    public CompletionStage<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(Http.Context context) {
        String email = Controller.session("connected");
        return CompletableFuture.supplyAsync(() -> Optional.ofNullable(Funcionario.findByEmail(email)));
    }

    @Override
    public CompletionStage<Result> onAuthFailure(Http.Context context, Optional<String> optional) {
        return CompletableFuture.completedFuture(new AuthController().index());
    }

    @Override
    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null;
    }

    @Override
    public String handlerName() {
        return HandlerKeys.DEFAULT.key;
    }
}
