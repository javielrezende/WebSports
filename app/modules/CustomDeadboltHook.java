package modules;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;
import security.MyDeadboltHandler;
import security.MyHandlerCache;

import javax.inject.Singleton;

/**
 * Created by Miguel, Roger, William on 09/07/2017.
 *
 *
 */
public class CustomDeadboltHook extends Module {
    @Override
    public Seq<Binding<?>> bindings(final Environment environment, final Configuration configuration) {
        return seq(bind(HandlerCache.class).to(MyHandlerCache.class).in(Singleton.class),
                   bind(DeadboltHandler.class).to(MyDeadboltHandler.class).in(Singleton.class));
    }
}
