package modules;

import com.google.inject.AbstractModule;
import com.typesafe.config.Config;

/**
 * @author Miguel, Roger, William
 * Classe de configuração do email
 */
public class ConfigModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Config.class).toProvider(ConfigProvider.class);
    }
}