package com.formidspike.Listeners;

import com.formidspike.ForMidspike;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.*;

public class TabCompleteListner implements TabCompleter {

    private ForMidspike plugin;
    public TabCompleteListner(ForMidspike plugin){
        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission("notes.default")) return null;
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            // Tab complete player names if the first argument is empty
            String input = args[0].toLowerCase();
            if ("add".startsWith(input)) {
                completions.add("Add");
            }
            if ("remove".startsWith(input)) {
                completions.add("Remove");
            }
            if ("get".startsWith(input)) {
                completions.add("Get");
            }
        } else if (args.length == 2) {
            String input = args[1].toLowerCase();
            for (Player player : Bukkit.getOnlinePlayers()) {
                String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(input)) {
                    completions.add(playerName);
                }
            }
        } else if (args.length == 3) {
            final String arg = args[2];
            final List<String> materials = plugin.getManageList().getMaterials();

            final int size = materials.size();
            int i = Collections.binarySearch(materials, arg, String.CASE_INSENSITIVE_ORDER);

            if (i < 0) {
                i = -1 - i;
            }

            for ( ; i < size; i++) {
                String material = materials.get(i);
                if (StringUtil.startsWithIgnoreCase(material, arg)) {
                   completions.add("minecraft:" + material.toLowerCase());
                } else {
                    break;
                }
            }

        }
        return completions;
    }
}