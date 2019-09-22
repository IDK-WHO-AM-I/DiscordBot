package de.idkwhoami.discordbot;

import de.idkwhoami.discordbot.internal.config.Configuration;
import de.idkwhoami.discordbot.internal.console.ConsoleReader;
import lombok.Getter;

import java.io.File;

public class Start {

    @Getter
    private static Start instance;

    @Getter
    Thread mainThread = new Thread(new DiscordBot());
    @Getter
    Thread consoleThread = new Thread(new ConsoleReader());

    public Start() {
        this.instance = this;
        mainThread.start();
    }

    /* Program Start */
    public static void main(String[] args) {
        defaultSetup();
        new Start();
    }

    private static void defaultSetup() {
        File configurationFile = new File(System.getProperty("user.dir"), "config.json");
        if (!configurationFile.exists()) {
            Configuration c = new Configuration(null, '$', 326810112997982208L);
            c.write(configurationFile);
        }
    }

}
