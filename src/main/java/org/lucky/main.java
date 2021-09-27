package org.lucky;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() == EntityType.PLAYER) {
            String hp = null;
            String hpm = null;
            Entity ent = e.getEntity();
            if (ent instanceof LivingEntity) {
                hp = Double.toString(((LivingEntity) ent).getHealth() - e.getDamage());
                hpm = Double.toString(((LivingEntity) ent).getMaxHealth());
            }

            e.getDamager().sendMessage(
                    ChatColor.GOLD + "Имя: " + ChatColor.GRAY + e.getEntity().getName() + "\n" +
                    ChatColor.GOLD + "Тип: " + ChatColor.GRAY + e.getEntity().getType() + "\n" +
                    ChatColor.GOLD + "HP: " + ChatColor.GRAY + hp + "/" + hpm
            );



        }
    }
}
