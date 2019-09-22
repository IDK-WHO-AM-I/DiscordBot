package de.idkwhoami.discordbot.internal.listener;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.Start;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class StartupListener extends ListenerAdapter {

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        Start.getInstance().getConsoleThread().start();
        DiscordBot.getInstance().getLogger().log("DiscordBot started successfully and can receive commands");
    }
}
