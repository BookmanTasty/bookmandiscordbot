package org.leyvadev.bookmandiscordbot.services;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.utils.FileUpload;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Objects;

@ApplicationScoped
public class BookmanBot extends ListenerAdapter implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(BookmanBot.class);
    @Inject
    @ConfigProperty(name = "discord.api.key")
    String apiKey;
    @Inject
    SummonerService summonerService;
    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("Bot connected to Discord server: " + event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.isFromType(ChannelType.PRIVATE))
        {
            LOGGER.info("Mensaje privado recibido de " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        }else{
            LOGGER.info("Mensaje recibido de " + event.getAuthor().getName() + " en el canal " + event.getChannel() + ": " + event.getMessage().getContentDisplay());
        }
    }
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        if (event.getName().equals("stats")) {
            String summonerName = Objects.requireNonNull(event.getOption("summoner")).getAsString();
            Response  response = summonerService.getSummonerImageByName(summonerName);
            if (response.getStatus() != 200) {
                event.getHook().editOriginal("No se encontró el summoner " + summonerName).queue();
                return;
            }
            byte[] imageData = response.readEntity(byte[].class);
            event.getHook().editOriginal("Imagen MA MA LO NA " )
                    .setFiles(FileUpload.fromData(imageData,"data.png"))
                    .setEmbeds(
                            new EmbedBuilder()
                                    .setTitle("Imagen de " + summonerName)
                                    .setImage("attachment://data.png")
                                    .build()
                    ).queue();
        }
    }

    @Override
    public void run() {
        try {
            JDABuilder.createDefault(apiKey)
                    .addEventListeners(this)
                    .setActivity(Activity.watching("Videos kChondos"))
                    .build()
                    .awaitReady()
                    .updateCommands()
                    .addCommands(
                            Commands.slash("stats", "Regresa una iman MA MA LO NA de la cuenta de League of Legends")
                                    .addOption(OptionType.STRING, "summoner", "Summoner name", true)
                    ).queue();

        } catch (Exception e) {
            LOGGER.error("Error al iniciar el bot", e);
        }
    }
}
