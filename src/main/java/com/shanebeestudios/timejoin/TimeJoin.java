package com.shanebeestudios.timejoin;

import com.shanebeestudios.timejoin.listener.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import com.shanebeestudios.timejoin.command.Command;
import com.shanebeestudios.timejoin.timer.Timer;
import com.shanebeestudios.timejoin.util.Config;
import com.shanebeestudios.timejoin.util.Utils;

public class TimeJoin extends JavaPlugin {

    private static TimeJoin plugin;
    private Config timeConfig;
    private BukkitRunnable timer = null;
    private JoinListener joinListener;

    @Override
    public void onEnable() {
        plugin = this;
        this.timeConfig = new Config(this);
        registerEvents();
        registerCommands();

        if (this.timeConfig.timerRepeatDelay > 0) {
            this.timer = new Timer(this);
        }
        Utils.log("Successfully loaded!");
    }

    @Override
    public void onDisable() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
        }
        plugin = null;
    }

    public Config getTimeConfig() {
        return this.timeConfig;
    }

    public void reloadTimeConfig() {
        this.timeConfig = new Config(this);
        if (this.timer != null) {
            this.timer.cancel();
            if (this.timeConfig.timerRepeatDelay > 0) {
                this.timer = new Timer(this);
            } else {
                this.timer = null;
            }
        }
        this.joinListener.reload(this);
    }

    @SuppressWarnings("ConstantConditions")
    private void registerCommands() {
        Command command = new Command(this);
        getCommand("timejoin").setExecutor(command);
        getCommand("timejoin").setTabCompleter(command);
    }

    private void registerEvents() {
        this.joinListener = new JoinListener(this);
        getServer().getPluginManager().registerEvents(this.joinListener, this);
    }

    public static TimeJoin getPlugin() {
        return plugin;
    }

}
