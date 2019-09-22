package de.idkwhoami.discordbot.internal.command;

import com.google.gson.annotations.SerializedName;
import de.idkwhoami.discordbot.internal.authorization.Permitable;
import lombok.Getter;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

import java.util.*;

public abstract class Command extends Permitable {

    @Getter
    private CommandType type;

    @Getter
    @SerializedName("prefix")
    private Character prefix;

    @Getter
    @SerializedName("base")
    private String baseCommand;

    @SerializedName("args")
    private LinkedList<Set<String>> arguments;

    public Command(Character prefix, String command, CommandType type) {
        String[] args = command.split("\\s");
        this.prefix = prefix;
        this.type = type;
        this.baseCommand = command.toLowerCase();
        this.arguments = new LinkedList<>();
        this.arguments.add(Collections.singleton(baseCommand));
    }

    public Command(String permission, Character prefix, String command, CommandType type) {
        super(permission);
        this.prefix = prefix;
        this.type = type;
        this.baseCommand = command.toLowerCase();
        this.arguments = new LinkedList<>();
        this.arguments.add(Collections.singleton(baseCommand));
    }

    protected boolean exists(String... args) {
        if (args == null)
            return false;
        boolean exists = false;
        for (int j = 0; j < args.length; j++) {
            exists = arguments.get(j).contains(args[j].toLowerCase());
        }
        return exists;
    }

    public void registerArguments(String... args) {
        if (args == null)
            return;
        if (exists(args))
            return;
        for (int i = 0; i < args.length; i++) {
            if (!arguments.get(i).contains(args[i].toLowerCase()))
                arguments.get(i).add(args[i].toLowerCase());
        }
    }

    public boolean validate(String input) {
        input = input.trim().toLowerCase();
        String regex = String.format("^%s[^\\s]+(\\s([^\\s]+|$))?", "\\" + prefix);
        if (!input.trim().matches(regex))
            return false;
        String[] array = input.substring(1).trim().split("\\s");
        return exists(array);
    }

    public abstract boolean execute(String command, MessageChannel channel, User author);

}