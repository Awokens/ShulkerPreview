package com.github.awokens.shulkerpreview;

import co.aikar.commands.PaperCommandManager;
import com.github.awokens.shulkerpreview.Commands.ShulkerPreviewCommand;
import com.github.awokens.shulkerpreview.Listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ShulkerPreview extends JavaPlugin {

    public static Config config;
    private static ShulkerPreview instance;


    public static ShulkerPreview getInstance() {
        return instance;
    }

    public static Config getShulkerConfig() {
        return config;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;
        config = new Config(getDataFolder());

        PluginManager manager = getServer().getPluginManager();
        PaperCommandManager commandManager = new PaperCommandManager(this);

        // Registering events and commands
        try {
            // Registering events
            getLogger().log(Level.INFO, "Registering events");
            manager.registerEvents(new EntityDamage(), this);
            manager.registerEvents(new HandSwapItem(), this);
            manager.registerEvents(new InventoryClick(), this);
            manager.registerEvents(new InventoryClose(), this);
            manager.registerEvents(new InventoryClose(), this);
            manager.registerEvents(new ItemHandSwap(), this);
            manager.registerEvents(new ItemInteract(), this);
            manager.registerEvents(new PlaceBlock(), this);

            // Registering commands
            getLogger().log(Level.INFO, "Registering commands");
            commandManager.registerCommand(new ShulkerPreviewCommand());

        } catch (Exception e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
        getLogger().log(Level.INFO, "Loaded plugin");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO, "Disabling ShulkerPreview");
    }
}
