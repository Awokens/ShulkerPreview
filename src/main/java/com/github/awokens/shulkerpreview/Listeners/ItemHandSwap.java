package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.ShulkerPreview;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

public class ItemHandSwap implements Listener {

    @EventHandler
    public void Swap(PlayerSwapHandItemsEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission(ShulkerPreview.getShulkerConfig().getShulkerPreviewPermission())) return;

        if (player.getCooldown(event.getMainHandItem().getType()) > 0) {
            event.setCancelled(true);
        }
    }
}
