package com.webauctionplus;

import org.bukkit.plugin.Plugin;

import com.poixson.webxbukkit.ConfigLoader.xBukkitConfig;


public class waConfig extends xBukkitConfig {

	public static final String PATH_Inventory_AllowCommand = "Inventory.AllowCommand";
	public static final String PATH_Inventory_AllowSign    = "Inventory.AllowSign";


	public waConfig(Plugin plugin) {
		super(plugin);
	}


	@Override
	protected void defaults() {
		// database
		defaultDatabase();
		config.addDefault(xBukkitConfig.PATH_Database_Prefix, "wa_");
		// inventory
		config.addDefault(PATH_Inventory_AllowCommand, true);
		config.addDefault(PATH_Inventory_AllowSign,    true);
	}


}
