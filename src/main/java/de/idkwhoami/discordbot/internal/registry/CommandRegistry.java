package de.idkwhoami.discordbot.internal.registry;

import de.idkwhoami.discordbot.internal.command.Command;
import de.idkwhoami.discordbot.internal.command.CommandType;
import lombok.Getter;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.ArrayList;

public class CommandRegistry {

    @Getter
    private ArrayList<Command> commands;

    public CommandRegistry() {
        commands = new ArrayList<>();
    }

    public void routeCommand(String input, MessageChannel channel, User author) {
        Command cmd = commands.stream().filter(command -> command.validate(input)).findFirst().orElse(null);
        if (cmd != null)
            if ((cmd.getType().equals(CommandType.PRIVATE) && channel.getType().equals(ChannelType.PRIVATE)) || (cmd.getType().equals(CommandType.PUBLIC))) {
                cmd.execute(input, channel, author);
            }
    }

    public void registerCommand(Command command) {
        commands.add(command);
    }

}
