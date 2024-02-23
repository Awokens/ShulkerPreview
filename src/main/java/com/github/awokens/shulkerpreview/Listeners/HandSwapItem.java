package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class HandSwapItem implements Listener {

    @EventHandler
    public void ItemChange(PlayerItemHeldEvent event) {

        Player player = event.getPlayer();

        if (!player.hasPermission(ShulkerPreview.getShulkerConfig().getShulkerPreviewPermission())) return;

        if (player.getOpenInventory().getType() != InventoryType.SHULKER_BOX) return;

        if (!player.getMetadata("shulker-reason").toString().equalsIgnoreCase("virtual")) return;

        String player_identifier = player.getMetadata("shulker-id").toString();

        ItemStack mainHand = player.getInventory().getItemInMainHand();

        NamespacedKey key = new NamespacedKey(ShulkerPreview.getInstance(), "shulker-id");
        String item_identifier = mainHand.getItemMeta().getPersistentDataContainer().getOrDefault(
                key, PersistentDataType.STRING, ""
        );

        if (!item_identifier.equalsIgnoreCase(player_identifier)) {
            player.closeInventory();
            event.setCancelled(true);
        }

    }


}
