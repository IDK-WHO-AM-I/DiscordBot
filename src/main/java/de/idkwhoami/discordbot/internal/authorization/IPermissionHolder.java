package de.idkwhoami.discordbot.internal.authorization;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public interface IPermissionHolder {

    @SerializedName("id")
    long id();

    @SerializedName("superuser")
    boolean isSuperuser();

    @SerializedName("permissions")
    List<String> permissions();
}