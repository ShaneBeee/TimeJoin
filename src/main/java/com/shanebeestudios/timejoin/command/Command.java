package com.shanebeestudios.timejoin.command;

import com.google.common.collect.ImmutableList;
import com.shanebeestudios.timejoin.TimeJoin;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Collections;
import java.util.List;

public class Command implements CommandExecutor, TabCompleter {

    private final TimeJoin plugin;

    public Command(TimeJoin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                this.plugin.reloadTimeConfig();
                sender.sendMessage("Config reloaded!");
            }
            return true;
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
