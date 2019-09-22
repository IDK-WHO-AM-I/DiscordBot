package de.idkwhoami.discordbot.internal.registry;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.internal.authentication.Operator;
import lombok.Getter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class OperatorRegistry {

    @Getter
    private ArrayList<Operator> operators;

    public OperatorRegistry() {
        this.operators = new ArrayList<>();
        try {
            load();
            if(operators.isEmpty()) {
                this.registerOperator(new Operator(DiscordBot.getInstance().getConfiguration().getAdminUserId(), true, Arrays.asList("$OVERWRITE$")));
            }
        } catch (IOException e) {
            System.err.println("Unable to load previous users from files");
        }
    }

    public Operator find(Predicate<Operator> predicate) {
        return operators.stream().filter(predicate).findAny().orElse(null);
    }

    public void load() throws IOException {
        for (File file : Paths.get(System.getProperty("user.dir"), "user").toFile().listFiles(File::isFile)) {
            if (file.getName().endsWith(".json")) {
                if (!isRegistered(Long.parseLong(file.getName().replace(".json", ""))))
                    this.operators.add(Operator.fromFile(file));
            }
        }
    }

    public boolean isRegistered(long id) {
        return this.operators.stream().anyMatch(operator -> operator.id() == id);
    }

    public void registerOperator(Operator operator) {
        this.operators.add(operator);
    }

}
