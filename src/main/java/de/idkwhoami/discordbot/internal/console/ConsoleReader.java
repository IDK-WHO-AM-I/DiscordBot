package de.idkwhoami.discordbot.internal.console;

import de.idkwhoami.discordbot.DiscordBot;
import de.idkwhoami.discordbot.logger.Logger;

import java.util.Scanner;

public class ConsoleReader implements Runnable {

    private Logger logger = new Logger("DiscordBot");
    private final Scanner scanner;

    public ConsoleReader() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        String line = "";
        while ((line = scanner.nextLine()) != null) {
            handleConsoleInput(line.trim().toLowerCase());
        }
    }

    private void handleConsoleInput(String input) {
        if (input.startsWith("exit")) {
            logger.log("Received Shutdown console command. Shutting down the program ..");
            DiscordBot.getInstance().getDiscordAPI().shutdownNow();
            System.exit(1);
        }
        //TODO handle console commands
    }

}
