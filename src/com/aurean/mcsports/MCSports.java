package com.aurean.mcsports;

import java.io.File;

import org.bukkit.plugin.PluginManager;
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
		new CustomLogLevel("DEBUG", 801);
		new CustomLogLevel("PLAYER_COMMAND", 802);

		getCommand("game").setExecutor(new CmdGame());
		getCommand("ozonesports").setExecutor(new CmdSports());
		getCommand("referee").setExecutor(new CmdReferee());
		PluginManager pluginManager = this.getServer().getPluginManager();
		MCSListener PlayerEventHandler = new MCSListener();
		pluginManager.registerEvents(PlayerEventHandler, this);
		if (Config.Logging.getEnabled.plugin("enable"))
			L.og.plugin(Config.Logging.getLevel.plugin("enable"), "MCSports " + getDescription().getVersion() + " enabled!");
	}
	
	public void onDisable(){
		MCSPlayer.disablePlugin();
		if (Config.Logging.getEnabled.plugin("disable"))
			L.og.plugin(Config.Logging.getLevel.plugin("disable"), "MCSports " + getDescription().getVersion() + " disabled!");
		L.disablePlugin();
		instance = null;
	}
	
	public static MCSports getInstance(){
		return instance;
	}

}
