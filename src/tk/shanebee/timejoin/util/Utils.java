package tk.shanebee.timejoin.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import tk.shanebee.timejoin.TimeJoin;

public class Utils {

	public static String getKickMessage() {
		return getColString(TimeJoin.plugin.getTimeConfig().MESSAGE_CANT_JOIN.replace("<b>", "\n"));
	}

	public static String getColString(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

	public static void log(String message) {
		String prefix = "&7[&3TimeJoin&7] ";
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + message));
	}

}
