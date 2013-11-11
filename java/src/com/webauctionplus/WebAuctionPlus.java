package com.webauctionplus;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import com.poixson.webxbukkit.PluginVersion;
import com.poixson.webxbukkit.Vault;
import com.poixson.webxbukkit.webLink.LinkManager;
import com.poixson.webxbukkit.webSettings.SettingsManager;


public class WebAuctionPlus extends JavaPlugin {

	private static volatile WebAuctionPlus instance = null;
	private static final Object lock = new Object();
//	private static volatile xLog log = null;

	// objects
	private volatile waConfig config = null;
@SuppressWarnings("unused")
	private volatile SettingsManager settings = null;
	private volatile PluginVersion version = null;
	private final waLanguage lang = new waLanguage();
	// web link
@SuppressWarnings("unused")
	private volatile LinkManager link = null;

	// database key
	private volatile String dbKey = null;

	// null=unloaded false=failed true=loaded
	private static volatile Boolean isOk = null;


	// get instance
	public static WebAuctionPlus get() {
		return instance;
	}
	public WebAuctionPlus() {
		super();
		synchronized(lock) {
			if(instance != null) throw new RuntimeException("Plugin already loaded?!");
			instance = this;
		}
	}
	public static boolean isOk() {
		return isOk;
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
			if(instance == null)
				instance = this;
		}
		// load vault (required)
		if(Vault.getEconomy() == null) {
			System.out.println("Economy plugin not found!");
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}

		// plugin version
		version = PluginVersion.get(this);
		version.update();
		// config.yml
		config = new waConfig(this);
		// connect to db
		dbKey = config.dbKey();
		// shared settings
		settings = SettingsManager.get(dbKey);
		// language
		lang.load(this, "en");
		// web link
		link = LinkManager.get(dbKey);
		LinkManager.start();







		isOk = true;
	}
	@Override
	public void onDisable() {
		isOk = false;
		// stop schedulers
		try {
			Bukkit.getScheduler().cancelTasks(this);
		} catch (Exception ignore) {}



		isOk = null;
	}


}
