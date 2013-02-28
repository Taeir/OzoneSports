package com.aurean.mcsports;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import com.aurean.mcsports.commands.*;
import com.aurean.mcsports.config.Config;

public class MCSports extends JavaPlugin {
	private static MCSports instance;
	public File configFile = new File("plugins/OzoneSports/config.yml");
	static CustomLogLevel debugLevel;
	
	public void onEnable(){
		instance = this;
		if (!configFile.exists()){
			getLogger().info("No config.yml found. Creating one...");
			saveDefaultConfig();
		}
		debugLevel = new CustomLogLevel("DEBUG", 600);

		getCommand("game").setExecutor(new CmdGame());
		getCommand("ozonesports").setExecutor(new CmdSports());
		getCommand("referee").setExecutor(new CmdReferee());
		if (Config.getLogEnabled("enable"))
			L.ogP(Config.getLogLevel("enable"), "MCSports " + getDescription().getVersion() + " enabled!");
	}
	
	public void onDisable(){
		MCSPlayer.disablePlugin();
		if (Config.getLogEnabled("disable"))
			L.ogP(Config.getLogLevel("disable"), "OzoneSports " + getDescription().getVersion() + " disabled!");
		L.disablePlugin();
		instance = null;
	}
	
	public static MCSports getInstance(){
		return instance;
	}
}
