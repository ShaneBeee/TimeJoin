package tk.shanebee.timejoin.timer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.timejoin.TimeJoin;
import tk.shanebee.timejoin.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Timer extends BukkitRunnable {

	private int kick_delay;
	private final String closing_message;

	public Timer() {
		int repeat_delay = TimeJoin.plugin.getTimeConfig().TIMER_REPEAT_DELAY;
		this.kick_delay = TimeJoin.plugin.getTimeConfig().TIMER_KICK_DELAY;
		if (kick_delay >= repeat_delay) kick_delay = repeat_delay - 1;
		this.closing_message = TimeJoin.plugin.getTimeConfig().TIMER_CLOSING_MESSAGE;
        int delay = 20 * 60 * repeat_delay;
		this.runTaskTimer(TimeJoin.plugin, delay, delay);
	}

	@Override
	public void run() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int time = Integer.parseInt(sdf.format(new Date()));
		if (time >= TimeJoin.plugin.getTimeConfig().JOIN_STOP) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (!player.hasPermission("timejoin.canjoin")) {
					player.sendMessage(Utils.getColString(closing_message.replace("<min>", String.valueOf(kick_delay))));
					new BukkitRunnable() {
						@Override
						public void run() {
							player.kickPlayer(Utils.getKickMessage());
						}
					}.runTaskLater(TimeJoin.plugin, 20L * 60 * kick_delay);
				}
			}
		}
	}

}
