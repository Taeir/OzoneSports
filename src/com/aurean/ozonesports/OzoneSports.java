package com.aurean.ozonesports;

import org.bukkit.plugin.java.JavaPlugin;

import com.aurean.ozonesports.commands.*;

public class OzoneSports extends JavaPlugin {
	private static OzoneSports instance;
	
	public void onEnable(){
		instance = this;
		getCommand("game").setExecutor(new CmdGame());
		getCommand("ozonesports").setExecutor(new CmdOzonesports());
		getCommand("referee").setExecutor(new CmdReferee());
		L.ogP("i", "OzoneSports " + getDescription().getVersion() + " enabled!");
	}
	
	public void onDisable(){
		L.ogP("i", "OzoneSports " + getDescription().getVersion() + " disabled!");
	}
	
	public static OzoneSports getInstance(){
		return instance;
	}
}
