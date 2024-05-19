package com.formidspike;

import com.formidspike.Classes.MaterialStorangeClass;
import com.formidspike.Commands.ManageList;
import com.formidspike.Listeners.TabCompleteListner;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public final class ForMidspike extends JavaPlugin {

    private ManageList manageList;
    public ManageList getManageList(){return this.manageList;}
    private Map<String, MaterialStorangeClass> materialStore;
    public Map<String, MaterialStorangeClass> getmaterialStore(){return materialStore;}
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("[ForMidspike] Enabling plugin");
        manageList = new ManageList(this);
        materialStore = new HashMap<>();
        this.getCommand("materials").setExecutor(new ManageList(this));
        this.getCommand("materials").setTabCompleter(new TabCompleteListner(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
