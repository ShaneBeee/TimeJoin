package tk.shanebee.timejoin.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import tk.shanebee.timejoin.TimeJoin;
import tk.shanebee.timejoin.util.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class JoinListener implements org.bukkit.event.Listener {

	@EventHandler
	private void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		int time = Integer.parseInt(sdf.format(new Date()));
		if (time < TimeJoin.plugin.getTimeConfig().JOIN_START || time >= TimeJoin.plugin.getTimeConfig().JOIN_STOP) {
			if (!player.hasPermission("timejoin.canjoin")) {
				player.kickPlayer(Utils.getKickMessage());
				if (TimeJoin.plugin.getTimeConfig().LOG_TO_CONSOLE) {
					String message = TimeJoin.plugin.getTimeConfig().CONSOLE_MESSAGE.replace("<player>", player.getName());
					Utils.log(message + " Time: " + time);
				}
			}
		}
	}

}
