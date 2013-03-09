package com.aurean.mcsports.config;

import java.util.logging.Level;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import com.aurean.mcsports.L;
import com.aurean.mcsports.MCSports;
import com.aurean.mcsports.L.Type;

public class Config {
	public static void disablePlugin(){
		config = null;
	}
	private static int alreadyChecked = 0;
	private static FileConfiguration config = MCSports.getInstance().getConfig();
	
	private static Level getLevelFromString(String Value, String path){
		if (alreadyChecked>0) alreadyChecked--;
		if (Value == null) {if (alreadyChecked>0) return Level.INFO;}
		if (Value.equals("info")) return Level.INFO;
		else if (Value.equals("command")) return Level.parse("PLAYER_COMMAND");
		else if (Value.equals("config")) return Level.CONFIG;
		else if (Value.equals("fine")) return Level.FINE;
		else if (Value.equals("warning")) return Level.WARNING;
		else if (Value.equals("severe")) return Level.SEVERE;
		else if (Value.equals("finer")) return Level.FINER;
		else if (Value.equals("finest")) return Level.FINEST;
		else if (Value.equals("debug")) return Level.parse("DEBUG");
		else {
			try { return Level.parse((Integer.parseInt(Value)*100) + ""); }
			catch (Exception e){
				if (config.getBoolean(path+".Enabled", true) && config.getBoolean("Log.Errors.Config.Enabled", true) && alreadyChecked == 0)
					L.og.plugin("i", "The "+path+".Level value set in the config is invalid. Using default value.");
				Configuration Defaults = config.getDefaults();
				if (Defaults != null && alreadyChecked == 0){
					alreadyChecked = 2;
					return getLevelFromString(Defaults.getString(path), path);
				}
				return Level.INFO;
			}
		}
	}
	
	public static class Logging {
		public static class getFormat {
			public static String command(Type type){
				if (type == Type.success) return config.getString("Log.Commands.Success.Format", "&sender Successfully used: /&command &args");
				else if (type == Type.noperm) return config.getString("Log.Commands.NoPermission.Format", "&sender was denied access to command: /&command &args");
				else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
					return config.getString("Log.Commands.Failed.Format", "&sender Failed (&extended) to use: /&command &args");
				else return "&sender used: /&command &args";
			}
		}
		
		public static class getEnabled {
			public static boolean command(Type type){
				if (type == Type.success) return config.getBoolean("Log.Commands.Success.Enabled", true);
				else if (type == Type.noperm) return config.getBoolean("Log.Commands.NoPermission.Enabled", true);
				else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
					return config.getBoolean("Log.Commands.Failed.Enabled", false);
				else return true;
			}
			public static boolean plugin(String type){
				boolean setValue = true;
				String path = "";
				if (type.equalsIgnoreCase("enable")){
					path = "Log.Initialisation.Enable";
					setValue = config.getBoolean(path+".Enabled", true); 
				}
				else if (type.equalsIgnoreCase("disable")){
					path = "Log.Initialisation.Disable";
					setValue = config.getBoolean(path+".Enabled", true);
				}
				else if (type.equalsIgnoreCase("configerror")) {
					path = "Log.Errors.Config";
					setValue = config.getBoolean(path+".Enabled", true);
				}
				else if (type.equalsIgnoreCase("warning")) {
					path = "Log.Errors.Plugin.Warning";
					setValue = config.getBoolean(path+".Enabled", true);
				}
				else if (type.equalsIgnoreCase("severe")) {
					path = "Log.Errors.Plugin.Severe";
					setValue = config.getBoolean(path+".Enabled", true);
				}
				
				return setValue;
			}
		}
		
		public static class getToFile {
			public static boolean command(Type type){
				if (type == Type.success) return config.getBoolean("Log.Commands.Success.ToFile", false);
				else if (type == Type.noperm) return config.getBoolean("Log.Commands.NoPermission.ToFile", false);
				else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
					return config.getBoolean("Log.Commands.Failed.ToFile", false);
				else return false;
			}
		}
		
		public static class getLevel {
			public static Level command(){
				String path = "Log.Commands";
				String Value = config.getString(path+".Level", "command").toLowerCase();
				return getLevelFromString(Value, path);
			}
			public static Level plugin(String type){
				String setValue = "", path = "";
				if (type.equalsIgnoreCase("enable")){
					path = "Log.Initialisation.Enable";
					setValue = config.getString(path+".Level", "info").toLowerCase(); 
				}
				else if (type.equalsIgnoreCase("disable")){
					path = "Log.Initialisation.Disable";
					setValue = config.getString(path+".Level", "info").toLowerCase();
				}
				else if (type.equalsIgnoreCase("configerror")) {
					path = "Log.Errors.Config";
					setValue = config.getString(path+".Level", "info").toLowerCase();
				}
				else if (type.equalsIgnoreCase("warning")) {
					path = "Log.Errors.Plugin.Warning";
					setValue = config.getString(path+".Level", "warning").toLowerCase();
				}
				else if (type.equalsIgnoreCase("severe")) {
					path = "Log.Errors.Plugin.Severe";
					setValue = config.getString(path+".Level", "severe").toLowerCase();
				}
				else if (type.equalsIgnoreCase("command")){
					path = "Log.Commands";
					setValue = config.getString(path+".Level", "command").toLowerCase();
				}
				return getLevelFromString(setValue, path);
			}
		}
	}
	
}
