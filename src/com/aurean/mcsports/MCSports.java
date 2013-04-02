package com.aurean.mcsports;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.aurean.mcsports.commands.*;
import com.aurean.mcsports.config.Config;

public class MCSports extends JavaPlugin {
	private static MCSports instance;
	public static Logger logger;
	
	public void onEnable(){
		instance = this;
		logger = getLogger();
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
		/*##############################################################
		 
		 We need to decide what kind of ball we want to use.
		 Thats really the main thing holding us back.
		 We can use:
		
		 1) A block that moves if you click it (Ender egg, normal block)
		 2) An item that moves on the ground in the direction you were facing when you picked it up.
		 		Downfall: tackling/stealing the ball is kinda difficult.
		 3) A bow to shoot special balls
		 		I think it might be good for basketball. You can pass it by hitting other players,
		 		and others can intercept it quite easy that way (just like in normal basketball).
		 		
		 		Aiming is also very good this way and it has a charge time.
 		4) If you have any other ideas please say so.
 		
 		##############################################################*/
	}
	
	public void onDisable(){
		MCSPlayer.disablePlugin();
		if (Config.Logging.getEnabled.plugin("disable"))
			L.og.plugin(Config.Logging.getLevel.plugin("disable"), "MCSports " + getDescription().getVersion() + " disabled!");
		HandlerList.unregisterAll(this);
		Config.disablePlugin();
		logger = null;
		instance = null;
	}
	
	public static MCSports getInstance(){
		return instance;
	}
	

}
