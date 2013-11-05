package com.webauctionplus;

import org.bukkit.plugin.Plugin;

import com.poixson.webxbukkit.ConfigLoader.xBukkitConfig;


public class waConfig extends xBukkitConfig {


	public waConfig(Plugin plugin) {
		super(plugin);
	}


	@Override
	protected void defaults() {
		// database
		defaultDatabase();
		config.addDefault("Database.Prefix",	"wa_");
		// inventory
		config.addDefault("Inventory.AllowCommand", true);
		config.addDefault("Inventory.AllowSign",    true);
	}


}
