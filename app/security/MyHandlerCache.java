package security;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.cache.HandlerCache;
import security.HandlerKeys;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Miguel
 * Classe de configuração de Email
 */
@Singleton
public class MyHandlerCache implements HandlerCache
{
    private final DeadboltHandler defaultHandler;
    private final Map<String, DeadboltHandler> handlers = new HashMap<>();

    @Inject
    public MyHandlerCache(final DeadboltHandler handler)
    {
        this.defaultHandler = handler;
        handlers.put(defaultHandler.handlerName(), defaultHandler);
        final DeadboltHandler altHandler = new MyAlternativeDeadboltHandler();
        handlers.put(altHandler.handlerName(), altHandler);
    }

    @Override
    public DeadboltHandler apply(final String key)
    {
        return handlers.get(key);
    }

    @Override
    public DeadboltHandler get()
    {
        return defaultHandler;
    }
}