package de.idkwhoami.discordbot.internal.authentication;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import de.idkwhoami.discordbot.internal.authorization.IPermissionHolder;
import lombok.Getter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Operator implements IPermissionHolder {

    @Getter
    private transient static final Gson GSON = new GsonBuilder().serializeNulls().setPrettyPrinting().disableHtmlEscaping().disableInnerClassSerialization().create();

    private final long id;
    private boolean superuser;
    private List<String> permissions;

    /**
     * This constructor is thought of too load the data from the file.
     * That for you enter the file name which should be the corresponding {@link Long}
     *
     * @param idString the file name of which file the data is loaded. Has to be a {@link Long} formatted string
     */
    public Operator(String idString) {
        this.id = Long.parseLong(idString);
        try {
            load();
        } catch (IOException e) {
            System.err.println("Unable to load data from file.");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public Operator(long id) {
        this(id, false, new ArrayList<>());
    }

    public Operator(long id, boolean superuser) {
        this(id, superuser, new ArrayList<>());
    }

    public Operator(long id, boolean superuser, List<String> permissions) {
        this.id = id;
        this.superuser = superuser;
        this.permissions = permissions;
        try {
            init();
        } catch (IOException e) {
            System.err.println("Unable to create file for user. Continuing without saving data!");
            e.printStackTrace();
        }
    }

    private void load() throws IOException {
        LinkedTreeMap map = GSON.fromJson(Files.newBufferedReader(Paths.get(System.getProperty("user.dir"), "user", this.id + ".json"), StandardCharsets.UTF_8), LinkedTreeMap.class);
        this.superuser = (boolean) map.getOrDefault("superuser", false);
        this.permissions = (List<String>) map.getOrDefault("permissions", new ArrayList<>());
    }

    public void write() throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(System.getProperty("user.dir"), "user", this.id + ".json"), StandardCharsets.UTF_8);
        GSON.toJson(this, writer);
        writer.flush();
        writer.close();
    }

    private void init() throws IOException {
        Files.createDirectories(Paths.get(System.getProperty("user.dir"), "user"));
        if (Files.notExists(Paths.get(System.getProperty("user.dir"), "user", this.id + ".json")))
            Files.createFile(Paths.get(System.getProperty("user.dir"), "user", id + ".json"));
        write();
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public boolean isSuperuser() {
        return this.superuser;
    }

    @Override
    public List<String> permissions() {
        return permissions;
    }

    public static Operator fromFile(File file) throws IOException {
        return GSON.fromJson(Files.newBufferedReader(file.toPath(), StandardCharsets.UTF_8), Operator.class);
    }

}