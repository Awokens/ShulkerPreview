package com.github.awokens.shulkerpreview.Commands;


import com.github.awokens.shulkerpreview.ShulkerPreview;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.annotations.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import io.papermc.paper.plugin.configuration.PluginMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Color;
import org.bukkit.command.CommandSender;

@Command("shulkerpreview")
@Alias({ "sp" })
@Help("Shulker Preview command")
public class ShulkerPreviewCommand {


    @Default
    public static void shulkerPreview(CommandSender sender) {

        PluginMeta pluginMeta = ShulkerPreview.getInstance().getPluginMeta(); // Paper API will be making this official API some day!

        String APIVersion = pluginMeta.getAPIVersion(); // support base version
        String Description = pluginMeta.getDescription(); // about the plugin
        String authors = String.join(", ", pluginMeta.getAuthors());
        String website = pluginMeta.getWebsite();

        final Component content = MiniMessage.miniMessage().deserialize(
                "\nShulker Preview"
                + "\nAPIVersion: " + APIVersion
                + "\nDescription: " + Description
                + "\nAuthors: " + authors
                + "\nSource: " + website
                + "\n"
        );
        sender.sendMessage(content);

    }

    @Subcommand("reload")
    @Permission("shulkerpreview.reload")
    public static void reloadConfig(CommandSender sender) throws WrapperCommandSyntaxException {

        if (!sender.hasPermission("shulkerpreview.reload")) {
            throw CommandAPI.failWithString(ShulkerPreview.config.getConfigReloadPermissionMessage());
        }

        if (ShulkerPreview.getShulkerConfig().reloadConfig()) {
            sender.sendMessage(
                    Component.text(Color.RED + "Successfully reload ShulkerPreview config.yml")
            );
        } else {
            sender.sendMessage(
                    Component.text(Color.RED + "Failed to reload ShulkerPreview config.yml")
            );
        }

    }

}
