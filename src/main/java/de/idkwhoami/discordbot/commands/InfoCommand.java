package de.idkwhoami.discordbot.commands;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.internal.authentication.Operator;
import de.idkwhoami.discordbot.internal.command.Command;
import de.idkwhoami.discordbot.internal.command.CommandType;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class InfoCommand extends Command {

    public InfoCommand() {
        super("command.info", DiscordBot.getInstance().getConfiguration().getCommandPrefix(), "info", CommandType.PUBLIC);
    }

    @Override
    public boolean execute(String command, MessageChannel channel, User author) {
        Operator operator = DiscordBot.getInstance().getOperatorRegistry().find(op -> op.id() == author.getIdLong());
        if (operator != null) {
            if (hasAccess(operator)) {
                channel.sendMessage(new EmbedBuilder()
                        .setTitle("▬▬▬▬▬▬▬▬▬▬ Information About Me  ▬▬▬▬▬▬▬▬▬▬")
                        .setColor(Color.GREEN)
                        .addField("Who am I?", "Well, my maker said i am a good boy. So i would guess thats what i am!", true)
                        .addField("Who is your maker?", "The biggest genius alive, at least the called himself that all the time", true)
                        .addField("How do I work?", String.format("My basic communication protocol tells me to give you following information:\nFor help with commands please use the %shelp to get more information", DiscordBot.getInstance().getConfiguration().getCommandPrefix()), true)
                        .build()
                ).complete();
                return true;
            } else {
                channel.sendMessage(String.format("Nope! Not happening %s", author.getAsMention())).complete();
                return false;
            }
        } else {
            author.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessage(String.format("Please use %sregister first if you want to use my commands!", DiscordBot.getInstance().getConfiguration().getCommandPrefix())).queue();
            });
        }
        return false;
    }

}
