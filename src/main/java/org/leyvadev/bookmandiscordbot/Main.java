package org.leyvadev.bookmandiscordbot;
import io.quarkus.runtime.StartupEvent;
import org.leyvadev.bookmandiscordbot.services.BookmanBot;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class Main {

    @Inject
    BookmanBot discordBotService;

    void onStart(@Observes StartupEvent ev) {
        new Thread(discordBotService).start();
    }
}
