package com.aurean.ozonesports;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import com.aurean.ozonesports.commands.*;
import com.aurean.ozonesports.config.Config;

public class OzoneSports extends JavaPlugin {
	private static OzoneSports instance;
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
		getCommand("ozonesports").setExecutor(new CmdOzonesports());
		getCommand("referee").setExecutor(new CmdReferee());
		L.ogP(Config.getLogLevel("enable"), "OzoneSports " + getDescription().getVersion() + " enabled!");
	}
	
	public void onDisable(){
		OSPlayer.disablePlugin();
		L.ogP(Config.getLogLevel("disable"), "OzoneSports " + getDescription().getVersion() + " disabled!");
		L.disablePlugin();
	}
	
	public static OzoneSports getInstance(){
		return instance;
	}
}
