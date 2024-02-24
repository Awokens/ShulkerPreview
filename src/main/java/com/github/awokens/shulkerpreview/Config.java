package com.github.awokens.shulkerpreview;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {
    private File configFile;

    private YamlConfiguration config;
    public Config(File dataFolder) {
        this.configFile = new File(dataFolder, "config.yml");
        this.config = new YamlConfiguration();
        createConfig();
        loadConfig();
    }

    private void createConfig() {
        if (!configFile.exists()) {
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();

                config.set("toggle-shulker-edits", true);
                config.set("shulker-preview-permission",  "");
                config.set("shulker-preview-permission-message", "&cNo permission to preview shulkers");
                config.set("config-reload-permission-message", "&cNo permission to reload ShulkerPreview config");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean loadConfig() {
        try {
            config.load(configFile);
            return true;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
            return false;
        }
    }

    // reload config
    public boolean reloadConfig() {
        return loadConfig();
    }

    public String getConfigValue(String path) {
        if (config != null) {
            return config.getString(path);
        } else {
            return null;
        }
    }
    public Boolean getToggleEdits() {
        return Boolean.parseBoolean(getConfigValue("toggle-shulker-edits"));
    }

    public String getShulkerPreviewPermission() {
        return getConfigValue("shulker-preview-permission");
    }

    public String getShulkerPreviewPermissionMessage() {
        return getConfigValue("shulker-preview-permission-message");
    }

    public String  getConfigReloadPermissionMessage() {
        return getConfigValue("config-reload-permission-message");
    }

}
