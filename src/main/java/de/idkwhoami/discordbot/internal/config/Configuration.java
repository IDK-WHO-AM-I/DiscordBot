package de.idkwhoami.discordbot.internal.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.idkwhoami.discordbot.logger.Logger;
import lombok.Getter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class Configuration {

    private static transient Logger logger = new Logger("Configuration");
    private static transient final Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();

    @Getter
    private final String botToken;

    @Getter
    private final long adminUserId;

    @Getter
    private final Character commandPrefix;

    public Configuration(String botToken, Character commandPrefix, long adminUserId) {
        this.botToken = botToken;
        this.commandPrefix = commandPrefix;
        this.adminUserId = adminUserId;
    }

    public static Configuration load(File file) {
        try (BufferedReader reader = Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            logger.error(String.format("Could not write configuration to the file %s for following reason:", file.getAbsolutePath()), e.getMessage());
        } finally {
            logger.success(String.format("Successfully loaded configuration from file %s", file.getAbsolutePath()));
        }
        return null;
    }

    public void write(File file) {
        try (BufferedWriter writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
            gson.toJson(this, writer);
            writer.flush();
        } catch (IOException e) {
            logger.error(String.format("Could not write configuration to the file %s for following reason:", file.getAbsolutePath()), e.getMessage());
        } finally {
            logger.success(String.format("Successfully written configuration to file %s", file.getAbsolutePath()));
        }
    }

}
