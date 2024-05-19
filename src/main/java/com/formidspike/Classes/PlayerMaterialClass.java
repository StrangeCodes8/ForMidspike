package com.formidspike.Classes;

import org.bukkit.Material;

import java.util.*;

public class PlayerMaterialClass {
    private static Map<UUID, List<Material>> storage = new HashMap<>();
    private static void ensurePlayer(UUID uuid){
        if (!storage.containsKey(uuid))
            storage.put(uuid, new ArrayList<>());
    }
    public static List<Material> getMaterials(UUID uuid, boolean returnEmpty){
        ensurePlayer(uuid);
        List<Material> materials = storage.get(uuid);
        if (materials.isEmpty() && !returnEmpty){
            throw new CustomRuntimeException(1, "There are no materials in the list");
        }
        return storage.get(uuid);
    }
    public static List<Material> getMaterials(UUID uuid){
        return getMaterials(uuid,false);
    }
    public static boolean addMaterial(UUID uuid, Material material){
        List<Material> materials = getMaterials(uuid,true);
        if (materials.contains(material)){
            throw new CustomRuntimeException(2, "Material is already in the list");
        }
        materials.add(material);
        return materials.contains(material);
    }
    public static boolean addMaterial(UUID uuid, String material){
        Material matchedMaterial = Material.matchMaterial(material);
        if (matchedMaterial == null){
            throw new CustomRuntimeException(3, "Invalid material: " + material);
        }
        return addMaterial(uuid, matchedMaterial);
    }

    public static boolean removeMaterial(UUID uuid, Material material){
        List<Material> materials = getMaterials(uuid);
        if (!materials.contains(material)) {
            throw new CustomRuntimeException(4, "Material is not currently in the list");
        } else {
            materials.remove(material);
        }
        return materials.contains(material);
    }
    public static boolean removeMaterial(UUID uuid, String material){
        Material matchedMaterial = Material.matchMaterial(material);
        if (matchedMaterial == null){
            throw new CustomRuntimeException(5,"Invalid material: " + material);
        }
        return removeMaterial(uuid, matchedMaterial);
    }
}
