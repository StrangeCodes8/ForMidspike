package com.formidspike;

import com.formidspike.Classes.MaterialStorangeClass;
import com.formidspike.Commands.ManageList;
import com.formidspike.Listeners.TabCompleteListner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ForMidspike extends JavaPlugin {
    private static Map<String, MaterialStorangeClass> materialStore;
    public static Map<String, MaterialStorangeClass> getmaterialStore(){return materialStore;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[ForMidspike] Enabling plugin");
        materialStore = new HashMap<>();
        Objects.requireNonNull(this.getCommand("materials")).setExecutor(new ManageList());
        Objects.requireNonNull(this.getCommand("materials")).setTabCompleter(new TabCompleteListner());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
