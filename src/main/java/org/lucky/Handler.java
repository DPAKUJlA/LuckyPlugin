package org.lucky;

import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class Handler implements Listener {

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
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

            ArrayList<String> nbtEntityListValues = new ArrayList(Arrays.asList(nbtEntity.toString().split(",")));

            for(String person : nbtEntityListValues){
                e.getDamager().sendMessage(person.
                        replace("{", "").
                        replace("}", "").
                        replace("[", "").
                        replace("]", "").
                        replace(":", " = ")
                );
            }
        }
    }

}
