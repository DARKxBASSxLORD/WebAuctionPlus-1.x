package com.webauctionplus;

import com.poixson.commonjava.Language.xLanguage;


public class waLanguage extends xLanguage {


	@Override
	protected void defaults() {
		addDefault("reloading",       "Reloading..");
		addDefault("reload_finished", "Finished reloading.");
		addDefault("no_permission",   "You don't have permission to do that.");
	}


}
