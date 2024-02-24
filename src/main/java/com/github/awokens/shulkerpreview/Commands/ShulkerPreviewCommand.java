package com.github.awokens.shulkerpreview.Commands;


import com.github.awokens.shulkerpreview.ShulkerPreview;
import com.github.awokens.shulkerpreview.Utils.Format;
import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.annotations.*;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import io.papermc.paper.plugin.configuration.PluginMeta;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
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

        Component content = Format.Color(
                "\n&bShulker Preview"
                + "\nAPIVersion: &a" + APIVersion
                + "\nAuthors: &a" + authors
                + "\nSource: &a" + website
                + "\nDescription: &a" + Description
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
            sender.sendMessage(Format.Color("&aSuccessfully reload ShulkerPreview config.yml"));
        } else {
            sender.sendMessage(Format.Color("&cFailed to reload ShulkerPreview config.yml"));
        }

    }

}
