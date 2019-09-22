package de.idkwhoami.discordbot.internal.listener;

import de.idkwhoami.discordbot.DiscordBot;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@Nonnull PrivateMessageReceivedEvent event) {
        String message = event.getMessage().getContentStripped();
        DiscordBot.getInstance().getCommandRegistry().routeCommand(message, event.getChannel(), event.getAuthor());
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event) {
        String message = event.getMessage().getContentStripped();
        DiscordBot.getInstance().getCommandRegistry().routeCommand(message, event.getChannel(), event.getAuthor());
    }

}
