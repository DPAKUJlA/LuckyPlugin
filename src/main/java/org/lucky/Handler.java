package org.lucky;


import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


public class Handler implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) throws ParseException {
        if (e.getDamager().getType() == EntityType.PLAYER) {
            String entityHealth = null;
            String entityMaxHealth = null;
            Entity entity = e.getEntity();
            NBTEntity nbtEntity = new NBTEntity(entity);
            if (entity instanceof LivingEntity) {
                Double health = ((LivingEntity) entity).getHealth() - e.getDamage();
                entityHealth = health < 0 ? "0" : Double.toString(health);
                entityMaxHealth = Double.toString(((LivingEntity) entity).getMaxHealth());
            }

            e.getDamager().sendMessage(
                    ChatColor.GOLD + "Имя: " + ChatColor.GRAY + e.getEntity().getName() + "\n" +
                            ChatColor.GOLD + "Тип: " + ChatColor.GRAY + e.getEntity().getType() + "\n" +
                            ChatColor.GOLD + "HP: " + ChatColor.GRAY + entityHealth + "/" + entityMaxHealth
            );
            //e.getDamager().sendMessage(nbtEntity.getCompoundList("Attributes").toString());

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(nbtEntity.toString());

            jsonObject.forEach((key, value) -> {
                e.getDamager().sendMessage(key + "= " + value + "(" + value.getClass().getName() + ")");
            });


        }
    }

}
