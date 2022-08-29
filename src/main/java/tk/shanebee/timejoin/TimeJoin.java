package tk.shanebee.timejoin;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import tk.shanebee.timejoin.command.Command;
import tk.shanebee.timejoin.listener.JoinListener;
import tk.shanebee.timejoin.timer.Timer;
import tk.shanebee.timejoin.util.Config;
import tk.shanebee.timejoin.util.Utils;

public class TimeJoin extends JavaPlugin {

	public static TimeJoin plugin;
	private Config timeConfig;
	private BukkitRunnable timer = null;

	@Override
	public void onEnable() {
		plugin = this;
		this.timeConfig = new Config(this);
		registerEvents();
		registerCommands();

		if (timeConfig.TIMER_REPEAT_DELAY > 0) {
			timer = new Timer();
		}
		Utils.log("Successfully loaded!");
	}

	@Override
	public void onDisable() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	public Config getTimeConfig() {
		return this.timeConfig;
	}

	public void reloadTimeConfig() {
		this.timeConfig = new Config(this);
		if (timer != null) {
			timer.cancel();
			if (timeConfig.TIMER_REPEAT_DELAY > 0) {
				timer = new Timer();
			} else {
				timer = null;
			}
		}
	}

	@SuppressWarnings("ConstantConditions")
	private void registerCommands() {
		Command command = new Command();
		getCommand("timejoin").setExecutor(command);
		getCommand("timejoin").setTabCompleter(command);
	}

	private void registerEvents() {
		getServer().getPluginManager().registerEvents(new JoinListener(), this);
	}

}
