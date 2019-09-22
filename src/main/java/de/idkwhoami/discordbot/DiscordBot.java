package de.idkwhoami.discordbot;

import de.idkwhoami.discordbot.commands.PingCommand;
import de.idkwhoami.discordbot.commands.InfoCommand;
import de.idkwhoami.discordbot.commands.RegisterCommand;
import de.idkwhoami.discordbot.commands.TestCommand;
import de.idkwhoami.discordbot.internal.config.Configuration;
import de.idkwhoami.discordbot.internal.listener.CommandListener;
import de.idkwhoami.discordbot.internal.listener.StartupListener;
import de.idkwhoami.discordbot.logger.Logger;
import de.idkwhoami.discordbot.internal.registry.CommandRegistry;
import de.idkwhoami.discordbot.internal.registry.OperatorRegistry;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.File;
import java.util.EnumSet;

public class DiscordBot implements Runnable {

    @Getter
    private static DiscordBot instance;

    @Getter
    private final Logger logger = new Logger("DiscordBot");
    @Getter
    private final Configuration configuration = Configuration.load(new File(System.getProperty("user.dir"), "config.json"));
    @Getter
    private JDA discordAPI;
    @Getter
    private CommandRegistry commandRegistry;
    @Getter
    private OperatorRegistry operatorRegistry;

    public DiscordBot() {
        instance = this;
        commandRegistry = new CommandRegistry();
        operatorRegistry = new OperatorRegistry();

        registerCommands();
    }

    private void initializeJDA() {
        JDABuilder builder = new JDABuilder(configuration.getBotToken())
                .setDisabledCacheFlags(EnumSet.of(CacheFlag.VOICE_STATE, CacheFlag.ACTIVITY))
                .setBulkDeleteSplittingEnabled(false)
                .setActivity(Activity.listening("your complaints"))
                .setAutoReconnect(true)
                .setStatus(OnlineStatus.ONLINE)
                /* Listeners */
                .addEventListeners(new CommandListener())
                .addEventListeners(new StartupListener());

        try {
            this.discordAPI = builder.build().awaitReady();
        } catch (Exception e) {
            logger.error(String.format("Unable to connect to the discord bot for following reason:", e.getMessage()));
            logger.warning("Shutting down the program due to an exception at the connection process");
            System.exit(1);
        }
    }

    private void registerCommands() {
        commandRegistry.registerCommand(new PingCommand());
        commandRegistry.registerCommand(new RegisterCommand());
        commandRegistry.registerCommand(new InfoCommand());
        commandRegistry.registerCommand(new TestCommand());
    }

    @Override
    public void run() {
        initializeJDA();
    }

}
