package tk.shanebee.timejoin.command;

import com.google.common.collect.ImmutableList;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import tk.shanebee.timejoin.TimeJoin;

import java.util.Collections;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				TimeJoin.plugin.reloadTimeConfig();
				sender.sendMessage("Config reloaded!");
			}
			return false;
		}
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, org.bukkit.command.Command command, String alias, String[] args) {
		if (args.length == 1) {
			return Collections.singletonList("reload");
		} else {
			return ImmutableList.of();
		}
	}

}
