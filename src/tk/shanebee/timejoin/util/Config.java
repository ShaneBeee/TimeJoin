package tk.shanebee.timejoin.util;

import org.bukkit.configuration.file.FileConfiguration;
import tk.shanebee.timejoin.TimeJoin;

public class Config {

	private FileConfiguration config;

	public int JOIN_START;
	public int JOIN_STOP;

	public String MESSAGE_CANT_JOIN;

	public boolean LOG_TO_CONSOLE;
	public String CONSOLE_MESSAGE;
	public int TIMER_REPEAT_DELAY;
	public int TIMER_KICK_DELAY;
	public String TIMER_CLOSING_MESSAGE;

	public Config(TimeJoin plugin) {
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		this.config = plugin.getConfig();
		loadConfig();
	}

	private void loadConfig() {
		this.JOIN_START = config.getInt("join-between.start");
		this.JOIN_STOP = config.getInt("join-between.stop");

		this.MESSAGE_CANT_JOIN = config.getString("message.cant-join");
		this.LOG_TO_CONSOLE = config.getBoolean("settings.log-to-console.enabled");
		this.CONSOLE_MESSAGE = config.getString("settings.log-to-console.message");
		this.TIMER_REPEAT_DELAY = config.getInt("settings.timer.repeat-delay");
		this.TIMER_KICK_DELAY = config.getInt("settings.timer.kick-delay");
		this.TIMER_CLOSING_MESSAGE = config.getString("message.server-closing");
	}

}
