package com.github.awokens.shulkerpreview.Listeners;

import com.github.awokens.shulkerpreview.Config;
import com.github.awokens.shulkerpreview.ShulkerPreview;
import net.kyori.adventure.text.Component;
import org.bukkit.block.ShulkerBox;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

import static com.github.awokens.shulkerpreview.ShulkerPreview.getShulkerConfig;

public class InventoryClick implements Listener {

    ShulkerPreview shulkerPreview;

    @EventHandler
    public void Click(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) return;

        if (player.hasPermission(getShulkerConfig().getShulkerPreviewPermission())) {
//            player.sendMessage(
//                    Component.text(getShulkerConfig().getShulkerPreviewPermission())
//            );
            return;
        }

        if (!player.getOpenInventory().title().contains(
                Component.text(player.getName() + "'s shulker box")
        )) {
            return;
        }

        ItemStack mainHand = player.getInventory().getItemInMainHand();

        if (mainHand.getItemMeta() instanceof BlockStateMeta blockStateMeta) {
            if (!(blockStateMeta.getBlockState() instanceof ShulkerBox)) {
                return;
            }
        }

        ItemStack selectItem = player.getInventory().getItem(event.getHotbarButton());
        if (selectItem.getItemMeta() instanceof BlockStateMeta blockStateMeta) {
            if (!(blockStateMeta.getBlockState() instanceof ShulkerBox)) {
                return;
            }
        }

        ItemStack eventItem = event.getCurrentItem();
        if (eventItem.getItemMeta() instanceof BlockStateMeta blockStateMeta) {
            if (blockStateMeta.getBlockState() instanceof ShulkerBox) {
                event.setCancelled(true);
            }
        }

        if (event.getClickedInventory() != null) {
            ItemStack slotItem = event.getClickedInventory().getItem(event.getSlot());
            if (slotItem.getItemMeta() instanceof BlockStateMeta blockStateMeta) {
                if (blockStateMeta.getBlockState() instanceof ShulkerBox) {
                    event.setCancelled(true);
                }
            }
        }

        if (event.isCancelled()) {
            player.closeInventory();
        }


    }
}
