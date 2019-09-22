package de.idkwhoami.discordbot.internal.authorization;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Permitable {

    @Getter
    private boolean permissionLess;

    @Getter
    @SerializedName("required")
    protected String requiredPermission;
    @Getter
    private transient List<Long> permitCache;

    public Permitable() {
        permissionLess = true;
        permitCache = new ArrayList<>();
    }

    public Permitable(String required) {
        requiredPermission = required;
        permitCache = new ArrayList<>();
    }

    public boolean hasAccess(IPermissionHolder holder) {
        if (permissionLess)
            return true;
        boolean permitted = permitCache.contains(holder.id()) || holder.permissions().stream().anyMatch(permission -> permission.matches("\\$OVERWRITE\\$") || permission.matches(requiredPermission + ".*"));
        if (permitted)
            permitCache.add(holder.id());
        return permitted;
    }


}