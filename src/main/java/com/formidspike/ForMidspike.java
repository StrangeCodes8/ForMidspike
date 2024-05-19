package com.formidspike;

import com.formidspike.Commands.ManageList;
import com.formidspike.Listeners.TabCompleteListner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class ForMidspike extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("[ForMidspike] Enabling plugin");
        Objects.requireNonNull(this.getCommand("materials")).setExecutor(new ManageList());
        Objects.requireNonNull(this.getCommand("materials")).setTabCompleter(new TabCompleteListner());
    }
}
