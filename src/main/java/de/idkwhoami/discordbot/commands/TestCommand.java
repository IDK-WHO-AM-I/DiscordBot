package de.idkwhoami.discordbot.commands;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.internal.command.Command;
import de.idkwhoami.discordbot.internal.command.CommandType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class TestCommand extends Command {

    public TestCommand() {
        super(DiscordBot.getInstance().getConfiguration().getCommandPrefix(), "test", CommandType.PUBLIC);
    }

    @Override
    public boolean execute(String command, MessageChannel channel, User author) {

        EmbedBuilder builder = new EmbedBuilder()
                .setAuthor("DiscordBot")
                .setColor(Color.ORANGE)
                .setFooter("This is Footer Text")
                .setTitle("This is a Title")
                .addField("Test Field", "Hello World!", true)
                .addField("Other Test Field", "Other Value", true)
                .addField("Third Test Field", "Is this working", true)
                .setDescription("This is a Discription");

        channel.sendMessage(builder.build()).complete();

        return true;
    }
}
