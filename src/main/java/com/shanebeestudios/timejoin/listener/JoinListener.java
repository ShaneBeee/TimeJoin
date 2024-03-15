package com.shanebeestudios.timejoin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import com.shanebeestudios.timejoin.TimeJoin;
import com.shanebeestudios.timejoin.util.Config;
import com.shanebeestudios.timejoin.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinListener implements org.bukkit.event.Listener {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
    private boolean logToConsole;
    private String consoleMessage;
    private int joinStartHour;
    private int joinStopHour;

    public JoinListener(TimeJoin plugin) {
        reload(plugin);
    }

    public void reload(TimeJoin plugin) {
        Config timeConfig = plugin.getTimeConfig();
        this.logToConsole = timeConfig.logToConsole;
        this.consoleMessage = timeConfig.consoleMessage;
        this.joinStartHour = timeConfig.joinStartHour;
        this.joinStopHour = timeConfig.joinStopHour;
    }

    @EventHandler
    private void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("timejoin.canjoin")) return;
        int currentHour = Integer.parseInt(this.dateFormat.format(new Date()));
        if (currentHour >= this.joinStartHour && currentHour < this.joinStopHour) return;

        player.kickPlayer(Utils.getKickMessage());
        if (this.logToConsole) {
            String message = this.consoleMessage.replace("<player>", player.getName());
            Utils.log(message + " Time: " + currentHour);
        }
    }

}
