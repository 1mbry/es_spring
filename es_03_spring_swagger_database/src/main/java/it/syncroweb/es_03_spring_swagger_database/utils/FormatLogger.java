package it.syncroweb.es_03_spring_swagger_database.utils;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class FormatLogger {
    private final Logger log;

    public FormatLogger(Logger log)
    {
        this.log = log;
    }

    public void debug(String formatter, Object... args)
    {
        log(Level.DEBUG, formatter, args);
    }

    public void error(String formatter, Object... args)
    {
        log(Level.ERROR, formatter, args);
    }

    public void info(String formatter, Object... args)
    {
        log(Level.INFO, formatter, args);
    }

    public void trace(String formatter, Object... args)
    {
        log(Level.TRACE, formatter, args);
    }

    public void warn(String formatter, Object... args)
    {
        log(Level.WARN, formatter, args);
    }

    public void log(Level level, String formatter, Object... args)
    {
        if (log.isEnabled(level)) {
            log.log(level, String.format(formatter, args));
        }
    }
}
