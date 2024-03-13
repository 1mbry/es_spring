package it.syncroweb.logintest.utils;

import it.syncroweb.logintest.model.UserEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.Locale;

@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private final String appUrl;
    private final Locale locale;
    private final UserEntity user;

    public OnRegistrationCompleteEvent(final UserEntity user, final Locale locale, final String appUrl) {
        super(user);

        this.appUrl = appUrl;
        this.locale = locale;
        this.user = user;
    }
}
