package org.lucky;

import de.tr7zw.changeme.nbtapi.NBTCompound;
import de.tr7zw.changeme.nbtapi.NBTContainer;
import de.tr7zw.changeme.nbtapi.NBTEntity;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.json.simple.JSONObject;


public class Handler implements Listener {

    private JSONObject getJSONString(NBTEntity entity) {
        JSONObject jsonObject = new JSONObject();

        entity.getKeys().forEach((s) -> {
            switch (entity.getType(s)) {
                case NBTTagInt:
                    jsonObject.put(s, entity.getInteger(s));
                    break;
                case NBTTagByte:
                    jsonObject.put(s, entity.getByte(s));
                    break;
                case NBTTagLong:
                    jsonObject.put(s, entity.getLong(s));
                    break;
                case NBTTagFloat:
                    jsonObject.put(s, entity.getFloat(s));
                    break;
                case NBTTagShort:
                    jsonObject.put(s, entity.getShort(s));
                    break;
                case NBTTagDouble:
                    jsonObject.put(s, entity.getDouble(s));
                    break;
                case NBTTagString:
                    jsonObject.put(s, entity.getString(s));
                    break;
                case NBTTagByteArray:
                    jsonObject.put(s, entity.getByteArray(s));
                    break;
                case NBTTagIntArray:
                    jsonObject.put(s, entity.getIntArray(s));
                    break;
                case NBTTagList:
                    jsonObject.put(s, entity.getCompoundList(s));
                    break;
                default:
                    jsonObject.put(s, entity.getCompound(s));
            }
        });
        return jsonObject;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType() == EntityType.PLAYER) {
            Entity entity = e.getEntity();

            NBTEntity nbtEntity = new NBTEntity(entity);

            nbtEntity.mergeCompound(new NBTContainer("{Attributes:[{Name:\"minecraft:generic.max_health\", Base:1234d}],Health:1234f}"));

            getJSONString(nbtEntity).forEach((key, value) -> {
                e.getDamager().sendMessage(ChatColor.GOLD + String.valueOf(key) + ": " + ChatColor.GREEN + value + ChatColor.GRAY + ChatColor.ITALIC + " (" + value.getClass().getSimpleName() + ")");
            });

        }
    }
}
