package de.idkwhoami.discordbot.commands;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.internal.command.Command;
import de.idkwhoami.discordbot.internal.command.CommandType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class PingCommand extends Command {

    public PingCommand() {
        super(DiscordBot.getInstance().getConfiguration().getCommandPrefix(), "ping", CommandType.PRIVATE);
    }

    @Override
    public boolean execute(String command, MessageChannel channel, User author) {
        channel.sendMessage("Pong!").complete();
        return true;
    }
}
