package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ItemInteract implements Listener {

    @EventHandler
    public void Click(PlayerInteractEvent event) {
        if (!event.getAction().isLeftClick()
                || !event.hasItem()
                || event.getClickedBlock() instanceof ShulkerBox
        ) return;

        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        if (!player.getInventory().getItemInMainHand().equals(item)) return;

        if (!(item.getItemMeta() instanceof BlockStateMeta blockStateMeta)) return;
        if (!(blockStateMeta.getBlockState() instanceof ShulkerBox shulkerBox)) return;

        if (player.getCooldown(item.getType()) > 0) return;

        if (!player.getOpenInventory().title().contains(
                Component.text(player.getName() + "'s shulker box")
        )) return;

        event.setCancelled(true);

        Inventory shulkerInventory = Bukkit.createInventory(
                null, InventoryType.SHULKER_BOX,
                Component.text(player.getName() + "'s shulker box")
        );

        shulkerInventory.setContents(shulkerBox.getInventory().getContents());

        String identifier = UUID.randomUUID().toString();

        NamespacedKey key = new NamespacedKey(ShulkerPreview.getInstance(), "shulker-id");

        item.getItemMeta().getPersistentDataContainer().set(key, PersistentDataType.STRING, identifier);

        player.openInventory(shulkerInventory);
        player.playSound(
                player, Sound.BLOCK_SHULKER_BOX_OPEN, 1, 1
        );


    }
}
