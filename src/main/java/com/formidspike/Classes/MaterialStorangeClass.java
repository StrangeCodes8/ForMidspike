package com.formidspike.Classes;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MaterialStorangeClass {
    private Player player;
    private List<Material> materials;

    public Player getPlayer() {
        return player;
    }

    public MaterialStorangeClass(){
        this.materials = new ArrayList<>();
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public boolean addMaterial(Material material){
        if (materials.contains(material)){
            throw new RuntimeException("Material is already in the list");
        }
        materials.add(material);
        return materials.contains(material);
    }

    public boolean removeMaterial(Material material){
        if (materials.contains(material)) {
            throw new RuntimeException("Materrial is not currently in the list");
        } else {
            materials.remove(material);
        }
        return materials.contains(material);
    }
    public void setPLayer(Player player){
        this.player = player;
    }
}
