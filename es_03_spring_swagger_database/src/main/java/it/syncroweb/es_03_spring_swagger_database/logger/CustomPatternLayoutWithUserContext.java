package it.syncroweb.es_03_spring_swagger_database.logger;

import ch.qos.logback.classic.PatternLayout;

public class CustomPatternLayoutWithUserContext extends PatternLayout {
    static {
        PatternLayout.DEFAULT_CONVERTER_MAP.put("user", "UserConverter.class.getName()");
        PatternLayout.DEFAULT_CONVERTER_MAP.put("session", "SessionConverter.class.getName()");
    }
}
