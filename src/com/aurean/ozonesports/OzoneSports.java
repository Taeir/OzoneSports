package com.aurean.ozonesports;

import org.bukkit.plugin.java.JavaPlugin;

public class OzoneSports extends JavaPlugin {
	private static OzoneSports instance;
	
	public void onEnable(){
		instance = this;
		L.ogP("i", "OzoneSports " + getDescription().getVersion() + " enabled!");
		getCommand("osreload").setExecutor(new OzoneCmdExecutor());
	}
	
	public void onDisable(){
		L.ogP("i", "OzoneSports " + getDescription().getVersion() + " disabled!");
	}
	
	public static OzoneSports getInstance(){
		return instance;
	}
}
