package com.formidspike.Commands;

import com.formidspike.Classes.MaterialStorangeClass;
import com.formidspike.ForMidspike;
import com.google.common.collect.ImmutableList;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManageList implements CommandExecutor {

    public ManageList(){}
    public static List<String> getMaterials(){return materials;}
    private static List<String> materials;
    static {
        ArrayList<String> materialList = Arrays.stream(Material.values()).map(Enum::name).sorted().collect(Collectors.toCollection(ArrayList::new));
        materials = ImmutableList.copyOf(materialList);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("materials")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }
            if (args.length == 0) {
                return false;
            }
            switch (args[0].toLowerCase()) {
                case "add":
                    try {
                        if (args.length == 3) {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player != null) {
                                Material material = Material.matchMaterial(args[2]);
                                if (material == null) {
                                    sender.sendMessage(c("Invalid material: " + args[2]));
                                    return true;
                                }
                                MaterialStorangeClass mat = ForMidspike.getmaterialStore().get(player.getUniqueId().toString());
                                if (mat == null) {
                                    mat = new MaterialStorangeClass();
                                }
                                mat.addMaterial(material);
                                mat.setPLayer(player);
                                ForMidspike.getmaterialStore().put(player.getUniqueId().toString(), mat);
                                sender.sendMessage(c(material.name() + " has been added to " + player.getName() + "'s list"));
                            }
                        } else {return false;}
                    } catch (RuntimeException e){
                        sender.sendMessage(c(e.getMessage()));
                        return false;
                    }
                    return true;
                case "get":
                    try {
                        if (args.length == 2) {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player != null) {
                                MaterialStorangeClass mat = ForMidspike.getmaterialStore().get(player.getUniqueId().toString());
                                if (mat == null) {
                                    sender.sendMessage(c("There is no materials list for " + player.getName()));
                                } else {
                                    sender.sendMessage(c("Materialas on " + player.getName() + "'s list"));
                                    for (Material m : mat.getMaterials()) {
                                        sender.sendMessage(c("   " + m.name()));
                                    }
                                }
                            }
                        } else {return false;}
                    } catch (RuntimeException e){
                        sender.sendMessage(c(e.getMessage()));
                    }
                    return true;
                case "remove":
                    try {
                        if (args.length == 3) {
                            Player player = Bukkit.getPlayer(args[1]);
                            if (player != null) {
                                Material material = Material.matchMaterial(args[2]);
                                if (material == null) {
                                    sender.sendMessage(c("Invalid material: " + args[2]));
                                    return true;
                                }
                                MaterialStorangeClass mat = ForMidspike.getmaterialStore().get(player.getUniqueId().toString());
                                if (mat == null) {
                                    sender.sendMessage(c("List does not exist for " + player.getName()));
                                } else {
                                    mat.removeMaterial(material);
                                    ForMidspike.getmaterialStore().put(player.getUniqueId().toString(), mat);
                                    sender.sendMessage(c(material.name() + " has been removed from " + player.getName()));
                                }
                            }
                        } else {return false;}
                    } catch (RuntimeException e){
                        sender.sendMessage(c(e.getMessage()));
                    }
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
    public Component c(String message){
        return Component.text(message);
    }
}
