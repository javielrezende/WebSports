package security;

import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Subject;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

/**
 * Created by Miguel on 09/07/2017.
 */
public class MyAlternativeDeadboltHandler implements be.objectify.deadbolt.java.DeadboltHandler {
    @Override
    public CompletionStage<Optional<Result>> beforeAuthCheck(Http.Context context) {
        return null;
    }

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(Http.Context context) {
        return null;
    }

    @Override
    public CompletionStage<Result> onAuthFailure(Http.Context context, Optional<String> optional) {
        return null;
    }

    @Override
    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(Http.Context context) {
        return null;
    }

    @Override
    public String handlerName() {
        return null;
    }
}
