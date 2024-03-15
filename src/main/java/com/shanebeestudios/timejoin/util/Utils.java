package com.shanebeestudios.timejoin.util;

import com.shanebeestudios.timejoin.TimeJoin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Utils {

    public static String getKickMessage() {
        return getColString(TimeJoin.getPlugin().getTimeConfig().messageCantJoin.replace("<b>", "\n"));
    }

    public static String getColString(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void log(String message) {
        String prefix = "&7[&bTime&3Join&7] ";
        Bukkit.getConsoleSender().sendMessage(getColString(prefix + message));
    }

}
