package com.github.awokens.shulkerpreview.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class Format {

    public static Component Color(String s) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(s.replaceAll("\n", "\n&r"));
    }
}
