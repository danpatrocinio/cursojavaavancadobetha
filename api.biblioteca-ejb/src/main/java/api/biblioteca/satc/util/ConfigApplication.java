package api.biblioteca.satc.util;

import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.TimeZone;

@Startup
@Singleton
public class ConfigApplication {

    private static final Logger LOG = Logger.getLogger(ConfigApplication.class);

    public static void logInfo(String info) {
        if (LOG.isInfoEnabled()) {
            LOG.info(info);
        }
    }
    public static void logError(String error) {
        if (LOG.isEnabled(Logger.Level.ERROR)) {
            LOG.info(error);
        }
    }

    @PostConstruct
    public void config() {

        logInfo("\n\n\tSubindo backend...\n\n");

        // configura o timezone na JVM do Java
        TimeZone.setDefault(TimeZone.getTimeZone("UTC-3"));
    }


}
