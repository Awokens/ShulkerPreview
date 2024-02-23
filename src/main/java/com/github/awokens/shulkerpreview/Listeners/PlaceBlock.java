package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlaceBlock implements Listener {


    @EventHandler
    public void Place(BlockPlaceEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission(
                ShulkerPreview.getShulkerConfig().getShulkerPreviewPermission()
        )) return;

        if (player.getInventory().getType() != InventoryType.SHULKER_BOX) return;

        if (!player.getMetadata("shulker_reason").toString().equalsIgnoreCase("virtual")) return;

        String player_identifier = player.getMetadata("shulker-id").toString();

        PersistentDataContainer container = event
                .getItemInHand()
                .getItemMeta()
                .getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(ShulkerPreview.getInstance(), "shulker-id");

        String item_identifier = container.getOrDefault(key, PersistentDataType.STRING, "");

        if  (item_identifier.equalsIgnoreCase(player_identifier)) event.setCancelled(true);

    }

}
