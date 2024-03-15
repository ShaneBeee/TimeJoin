package com.shanebeestudios.timejoin.timer;

import com.shanebeestudios.timejoin.TimeJoin;
import com.shanebeestudios.timejoin.util.Config;
import com.shanebeestudios.timejoin.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Timer extends BukkitRunnable {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH");
    private final TimeJoin plugin;
    private final int kickDelay;
    private final String closingMessage;
    private final int joinStopHour;

    public Timer(TimeJoin plugin) {
        this.plugin = plugin;
        Config timeConfig = plugin.getTimeConfig();
        int repeatDelay = timeConfig.timerRepeatDelay;
        this.kickDelay = timeConfig.timerKickDelay < repeatDelay ? timeConfig.timerKickDelay : repeatDelay - 1;
        this.closingMessage = Utils.getColString(timeConfig.timerClosingMessage
            .replace("<min>", "" + this.kickDelay));
        this.joinStopHour = timeConfig.joinStopHour;
        int delay = 20 * 60 * repeatDelay;
        this.runTaskTimer(plugin, delay, delay);
    }

    @Override
    public void run() {
        int currentHour = Integer.parseInt(this.dateFormat.format(new Date()));
        if (currentHour < this.joinStopHour) return;

        List<Player> playersToKick = new ArrayList<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.hasPermission("timejoin.canjoin")) continue;

            player.sendMessage(this.closingMessage);
            playersToKick.add(player);
        }

        if (playersToKick.isEmpty()) return;

        new BukkitRunnable() {
            @Override
            public void run() {
                playersToKick.forEach(player -> {
                    player.kickPlayer(Utils.getKickMessage());
                });
            }
        }.runTaskLater(this.plugin, 20L * 60 * kickDelay);
    }

}
