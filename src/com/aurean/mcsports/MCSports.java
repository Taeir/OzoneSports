package com.aurean.mcsports;

import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

import com.aurean.mcsports.commands.*;
import com.aurean.mcsports.config.Config;

public class MCSports extends JavaPlugin {
	private static MCSports instance;
	
	public void onEnable(){
		instance = this;
		if (!(new File("plugins/MCSports/config.yml")).exists()){
			getLogger().info("No config.yml found. Creating one...");
			saveDefaultConfig();
		}
		new CustomLogLevel("DEBUG", 600);
		new CustomLogLevel("PLAYER_COMMAND", 801);

		getCommand("game").setExecutor(new CmdGame());
		getCommand("ozonesports").setExecutor(new CmdSports());
		getCommand("referee").setExecutor(new CmdReferee());
		if (Config.Logging.getEnabled.plugin("enable"))
			L.og.plugin(Config.getLogLevel("enable"), "MCSports " + getDescription().getVersion() + " enabled!");
	}
	
	public void onDisable(){
		MCSPlayer.disablePlugin();
		if (Config.Logging.getEnabled.plugin("disable"))
			L.og.plugin(Config.getLogLevel("disable"), "OzoneSports " + getDescription().getVersion() + " disabled!");
		L.disablePlugin();
		instance = null;
	}
	
	public static MCSports getInstance(){
		return instance;
	}
}
