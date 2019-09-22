package de.idkwhoami.discordbot.commands;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.internal.authentication.Operator;
import de.idkwhoami.discordbot.internal.command.Command;
import de.idkwhoami.discordbot.internal.command.CommandType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.io.IOException;

public class RegisterCommand extends Command {

    public RegisterCommand() {
        super(DiscordBot.getInstance().getConfiguration().getCommandPrefix(), "register", CommandType.PRIVATE);
    }

    @Override
    public boolean execute(String command, MessageChannel channel, User author) {
        if (!DiscordBot.getInstance().getOperatorRegistry().isRegistered(author.getIdLong())) {
            Operator operator = new Operator(author.getIdLong());
            try {
                operator.write();
                channel.sendMessage("Thank you very much for registering. You currently have no permissions. To change that request them from the bot administrator or if you have access add them in your operator file").complete();
                DiscordBot.getInstance().getLogger().log(String.format("Registered new operator %s", author.getId()));
            } catch (IOException e) {
                DiscordBot.getInstance().getLogger().warning(String.format("Unable to write operator file for %s", author.getId()));
            }
        } else {
            channel.sendMessage("Look at this guy, trying to register a second time :D").complete();
        }
        return true;
    }
}
