package com.aurean.mcsports.config;

import java.util.logging.Level;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import com.aurean.mcsports.L;
import com.aurean.mcsports.MCSports;
import com.aurean.mcsports.L.Type;

public class Config {
	private static int alreadyChecked = 0;
	private static FileConfiguration config = MCSports.getInstance().getConfig();
	public static String getLogCommandFormat(Type type){
		if (type == Type.success) return config.getString("Log.Commands.Success.Format", "&sender Successfully used: /&command &args");
		else if (type == Type.noperm) return config.getString("Log.Commands.NoPermission.Format", "&sender was denied access to command: /&command &args");
		else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
			return config.getString("Log.Commands.Failed.Format", "&sender Failed (&extended) to use: /&command &args");
		else return "&sender used: /&command &args";
	}
	
	public static boolean getLogCommandEnabled(Type type){
		if (type == Type.success) return config.getBoolean("Log.Commands.Success.Enabled", true);
		else if (type == Type.noperm) return config.getBoolean("Log.Commands.NoPermission.Enabled", true);
		else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
			return config.getBoolean("Log.Commands.Failed.Enabled", false);
		else return true;
	}
	
	public static boolean getLogCommandToFile(Type type){
		if (type == Type.success) return config.getBoolean("Log.Commands.Success.ToFile", false);
		else if (type == Type.noperm) return config.getBoolean("Log.Commands.NoPermission.ToFile", false);
		else if (type == Type.fail || type == Type.tooManyArgs || type == Type.notEnoughArgs || type == Type.refereeLimit || type == Type.alreadyReferee)
			return config.getBoolean("Log.Commands.Failed.ToFile", false);
		else return false;
	}
	
	public static Level getLogCommandLevel(){
		String path = "Log.Commands";
		String Value = config.getString(path+".Level", "info").toLowerCase();
		return getLevelFromString(Value, path);
	}
	
	public static Level getLogLevel(String type){
		String setValue = "", path = "";
		if (type.equalsIgnoreCase("enable")){
			path = "Log.Initialisation.Enable";
			setValue = config.getString(path+".Level", "info"); 
		}
		else if (type.equalsIgnoreCase("disable")){
			path = "Log.Initialisation.Disable";
			setValue = config.getString(path+".Level", "info");
		}
		else if (type.equalsIgnoreCase("configerror")) {
			path = "Log.Errors.Config";
			setValue = config.getString(path+".Level", "info");
		}
		else if (type.equalsIgnoreCase("warning")) {
			path = "Log.Errors.Plugin.Warning";
			setValue = config.getString(path+".Level", "warning");
		}
		else if (type.equalsIgnoreCase("severe")) {
			path = "Log.Errors.Plugin.Severe";
			setValue = config.getString(path+".Level", "severe");
		}
		return getLevelFromString(setValue, path);
	}
	
	private static Level getLevelFromString(String Value, String path){
		if (alreadyChecked>0) alreadyChecked--;
		if (Value == null) {if (alreadyChecked>0) return Level.INFO;}
		if (Value.equals("info")) return Level.INFO;
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
					L.ogP("i", "The "+path+".Level value set in the config is invalid. Using default value.");
				Configuration Defaults = config.getDefaults();
				if (Defaults != null && alreadyChecked == 0){
					alreadyChecked = 2;
					return getLevelFromString(Defaults.getString(path), path);
				}
				return Level.INFO;
			}
		}
	}
	
	public static boolean getLogEnabled(String type){
		boolean setValue = true;
		String path = "";
		if (type.equalsIgnoreCase("enable")){
			path = "Log.Initialisation.Enable";
			setValue = config.getBoolean(path+".Level", true); 
		}
		else if (type.equalsIgnoreCase("disable")){
			path = "Log.Initialisation.Disable";
			setValue = config.getBoolean(path+".Level", true);
		}
		else if (type.equalsIgnoreCase("configerror")) {
			path = "Log.Errors.Config";
			setValue = config.getBoolean(path+".Level", true);
		}
		else if (type.equalsIgnoreCase("warning")) {
			path = "Log.Errors.Plugin.Warning";
			setValue = config.getBoolean(path+".Level", true);
		}
		else if (type.equalsIgnoreCase("severe")) {
			path = "Log.Errors.Plugin.Severe";
			setValue = config.getBoolean(path+".Level", true);
		}
		
		return setValue;
	}
	
}
