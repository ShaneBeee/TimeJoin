package com.shanebeestudios.timejoin.util;

import com.shanebeestudios.timejoin.TimeJoin;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    public int joinStartHour;
    public int joinStopHour;
    public String messageCantJoin;
    public boolean logToConsole;
    public String consoleMessage;
    public int timerRepeatDelay;
    public int timerKickDelay;
    public String timerClosingMessage;

    public Config(TimeJoin plugin) {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        this.joinStartHour = config.getInt("join-between.start");
        this.joinStopHour = config.getInt("join-between.stop");
        this.messageCantJoin = config.getString("message.cant-join");
        this.logToConsole = config.getBoolean("settings.log-to-console.enabled");
        this.consoleMessage = config.getString("settings.log-to-console.message");
        this.timerRepeatDelay = config.getInt("settings.timer.repeat-delay");
        this.timerKickDelay = config.getInt("settings.timer.kick-delay");
        this.timerClosingMessage = config.getString("message.server-closing");
    }

}
