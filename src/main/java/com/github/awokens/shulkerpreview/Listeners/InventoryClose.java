package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.BlockState;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class InventoryClose implements Listener {

    @EventHandler
    public void Close(InventoryCloseEvent event) {

        if (!(event.getPlayer() instanceof Player player)) return;

        if (!player.hasPermission(
                ShulkerPreview.getShulkerConfig().getShulkerPreviewPermission()
        )) return;

        if (event.getInventory().getType() != InventoryType.SHULKER_BOX) return;

        if (!player.getMetadata("shulker-reason").toString().equalsIgnoreCase("virtual")) return;

        String player_identifier = player.getMetadata("shulker-id").toString();

        ItemStack mainHand = player.getInventory().getItemInMainHand();
        PersistentDataContainer container = mainHand.getItemMeta().getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(ShulkerPreview.getInstance(), "shulker-id");

        String item_identifier = container.getOrDefault(key, PersistentDataType.STRING, "");

        if  (!item_identifier.equalsIgnoreCase(player_identifier)) return;

        player.removeMetadata("shulker-reason", ShulkerPreview.getInstance());

        if (!(mainHand.getItemMeta() instanceof BlockStateMeta blockStateMeta)) return;
        if (!(blockStateMeta.getBlockState() instanceof ShulkerBox shulkerBox)) return;

        shulkerBox.getInventory().setContents(
                player.getOpenInventory()
                        .getTopInventory()
                        .getContents()
        );

        blockStateMeta.setBlockState(shulkerBox);
        mainHand.setItemMeta(blockStateMeta);

        player.getInventory().setItemInMainHand(mainHand);

        player.playSound(
                player, Sound.BLOCK_SHULKER_BOX_CLOSE, 1, 1
        );

        player.setCooldown(mainHand.getType(), 10);

    }

}
