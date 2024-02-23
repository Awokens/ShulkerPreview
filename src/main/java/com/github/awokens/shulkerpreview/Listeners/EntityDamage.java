package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryType;

public class EntityDamage implements Listener {

    @EventHandler
    public void Damage(EntityDamageEvent event) {

        if (!(event.getEntity() instanceof Player player)) return;

        if (!player.hasPermission(
                ShulkerPreview.getShulkerConfig().getShulkerPreviewPermission()
        )) return;

        if (player.getOpenInventory().getType() != InventoryType.SHULKER_BOX) return;

        player.closeInventory();

    }
}
