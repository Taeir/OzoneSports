package com.aurean.ozonesports;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.aurean.ozonesports.config.Config;
import com.aurean.ozonesports.info.Usage;

public class L {
	private static Logger log = Logger.getLogger("Minecraft");
	private static Logger logP;
	private static OzoneSports plugin = OzoneSports.getInstance();
	
	public static void disablePlugin(){
		log = null;
		logP = null;
		plugin = null;
	}
	
	public static enum Type{
		success, fail, tooManyArgs, notEnoughArgs, noperm, refereeLimit, alreadyReferee, unknown
	};
	public static String getTypeName(Type type){
		if (type == Type.noperm) return "No permission";
		else if (type == Type.fail) return "Invalid syntaxis";
		else if (type == Type.tooManyArgs) return "Too many arguments";
		else if (type == Type.notEnoughArgs) return "Not enough arguments";
		else if (type == Type.refereeLimit) return "Referee limit";
		else if (type == Type.alreadyReferee) return "Already referee";
		else if (type == Type.success) return "Success";
		else return "unknown";
	}
	
	public static void notifier(CommandSender sender, Type type){
		if (type == Type.noperm)
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
		else if (type == Type.fail)
			sender.sendMessage(ChatColor.RED + "Incorrect syntaxis!");
		else if (type == Type.tooManyArgs)
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
		else if (type == Type.notEnoughArgs)
			sender.sendMessage(ChatColor.RED + "Not enough arguments!");
		else if (type == Type.refereeLimit)
			sender.sendMessage(ChatColor.RED + "You cannot become referee for this game because it already has 2 referees!");
		else if (type == Type.alreadyReferee)
			sender.sendMessage(ChatColor.RED + "You are already a referee for a different game!");
	}
	
	public static void ogS(String lvl, String msg){
		if (lvl.equalsIgnoreCase("finest")){log.finest(msg);}
		else if (lvl.equalsIgnoreCase("finer")){log.finer(msg);}
		else if (lvl.equalsIgnoreCase("fine") || lvl.equalsIgnoreCase("f")){log.fine(msg);}
		else if (lvl.equalsIgnoreCase("info") || lvl.equalsIgnoreCase("i")){log.info(msg);}
		else if (lvl.equalsIgnoreCase("warning") || lvl.equalsIgnoreCase("w")){log.warning(msg);}
		else if (lvl.equalsIgnoreCase("severe") || lvl.equalsIgnoreCase("s")){log.severe(msg);}
		else if (lvl.equalsIgnoreCase("command") || lvl.equalsIgnoreCase("c")){log.info("[PLAYER_COMMAND] " + msg);}
		else if (lvl.equalsIgnoreCase("finecommand") || lvl.equalsIgnoreCase("fc") || lvl.equalsIgnoreCase("cf")){log.fine("[PLAYER_COMMAND] " + msg);}
		else {log.info(msg);}
	}
	public static void ogP(String lvl, String msg){
		logP = plugin.getLogger();
		if (lvl.equalsIgnoreCase("finest")){logP.finest(msg);}
		else if (lvl.equalsIgnoreCase("finer")){logP.finer(msg);}
		else if (lvl.equalsIgnoreCase("fine") || lvl.equalsIgnoreCase("f")){logP.fine(msg);}
		else if (lvl.equalsIgnoreCase("info") || lvl.equalsIgnoreCase("i")){logP.info(msg);}
		else if (lvl.equalsIgnoreCase("warning") || lvl.equalsIgnoreCase("w")){logP.warning(msg);}
		else if (lvl.equalsIgnoreCase("severe") || lvl.equalsIgnoreCase("s")){logP.severe(msg);}
		else if (lvl.equalsIgnoreCase("command") || lvl.equalsIgnoreCase("c")){logP.info("[PLAYER_COMMAND] " + msg);}
		else if (lvl.equalsIgnoreCase("finecommand") || lvl.equalsIgnoreCase("fc") || lvl.equalsIgnoreCase("cf")){logP.fine("[PLAYER_COMMAND] " + msg);}
		else {logP.info(msg);}
	}
	
	public static void ogP(Level lvl, String msg){
		plugin.getLogger().log(lvl, msg);
	}

	public static void ogC(CommandSender sender, String msg, Type outcome, boolean notify) {
		if (Config.getLogCommandEnabled(outcome)){
			String logmsg = replaceExtra(Config.getLogCommandFormat(outcome), sender, msg, outcome);
			log.log(Config.getLogCommandLevel(), "[PLAYER_COMMAND] " + logmsg);
		}
		if (notify) notifier(sender, outcome);
	}
	public static void og(CommandSender sender, Command cmd, String allArgs, Type outcome, boolean notify, boolean tellUsage){
		if (Config.getLogCommandEnabled(outcome)){
			String logmsg = replaceExtra(Config.getLogCommandFormat(outcome), sender, cmd, allArgs, outcome);
			log.log(Config.getLogCommandLevel(), "[PLAYER_COMMAND] " + logmsg);
		}
		String usage = ChatColor.RED + "Correct usage: " + Usage.game(sender, cmd.getName());
		if (outcome == Type.success || outcome == Type.noperm) tellUsage = false;
		if (notify) notifier(sender, outcome);
		if (tellUsage) sender.sendMessage(usage);
	}
	
	private static String replaceExtra(String msg, CommandSender sender, Command cmd, String allArgs, Type type){
		return msg.replace("&sender", sender.getName())
				 .replace("&command", cmd.getName())
				 .replace("&args", allArgs)
				 .replace("&extended", getTypeName(type));
	}
	private static String replaceExtra(String msg, CommandSender sender, String allArgs, Type type){
		String newAllArgs = "";
		for (int i = 1; i < allArgs.split(" ").length;i++){
			if (newAllArgs.equals("")) newAllArgs = allArgs.split(" ")[i];
			else newAllArgs = newAllArgs+" "+allArgs.split(" ")[i];
		}
		return msg.replace("&sender", sender.getName())
				 .replace("&command", allArgs.split(" ")[0])
				 .replace("&args", newAllArgs)
				 .replace("&extended", getTypeName(type));
	}
}
