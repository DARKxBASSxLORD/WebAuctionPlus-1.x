package com.webauctionplus;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.poixson.webxbukkit.PluginVersion;


public class WebAuctionPlus extends JavaPlugin {

	private static volatile WebAuctionPlus plugin = null;
	private static final Object lock = new Object();

	// objects
	private volatile PluginVersion version = null;
	private final waLanguage lang = new waLanguage();

	// run state
	private static volatile Boolean isOk = null;
	private static volatile boolean debug = false;


	public WebAuctionPlus() {
		super();
		synchronized(lock) {
			if(plugin != null) throw new RuntimeException("Plugin plugin already loaded?!");
			plugin = this;
		}
	}
	@Override
	public void onEnable() {
		synchronized(lock) {
			if(isOk != null) {
				getServer().getConsoleSender().sendMessage(ChatColor.RED+"********************************************");
				getServer().getConsoleSender().sendMessage(ChatColor.RED+"*** WebAuctionPlus is already running!!! ***");
				getServer().getConsoleSender().sendMessage(ChatColor.RED+"********************************************");
				return;
			}
			isOk = false;
		}
		// language
		lang.load(this, "en");
		// plugin version
		version = PluginVersion.get(this);
		version.update();




		isOk = true;
	}
	@Override
	public void onDisable() {
		isOk = false;
		// stop schedulers
		try {
			getServer().getScheduler().cancelTasks(this);
		} catch (Exception ignore) {}




		isOk = null;
	}


	public static boolean isOk() {
		return isOk;
	}
	public static boolean isDebug() {
		return debug;
	}


}
