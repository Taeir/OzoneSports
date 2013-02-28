package com.aurean.ozonesports;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.aurean.ozonesports.info.Usage;

public class L {
	private static Logger log = Logger.getLogger("Minecraft");
	private static Logger logP;
	private static OzoneSports plugin = OzoneSports.getInstance();
	
	public static enum Type{
		success, fail, tooManyArgs, notEnoughArgs, noperm, unknown, failTried, refereeLimit
	};
	
	public static void notifier(CommandSender sender, Type type){
		if (type == Type.noperm)
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
		else if (type == Type.fail || type == Type.failTried)
			sender.sendMessage(ChatColor.RED + "Incorrect syntaxis!");
		else if (type == Type.tooManyArgs)
			sender.sendMessage(ChatColor.RED + "Too many arguments!");
		else if (type == Type.notEnoughArgs)
			sender.sendMessage(ChatColor.RED + "Not enough arguments!");
		else if (type == Type.refereeLimit)
			sender.sendMessage(ChatColor.RED + "Referee Limit reached!");
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

	public static void ogC(CommandSender sender, String msg, Type outcome, boolean notify) {
		if (outcome == Type.success)
			msg = sender.getName() + " Successfully used " + msg;
		else if (outcome == Type.fail || outcome == Type.tooManyArgs || outcome == Type.notEnoughArgs)
			msg = sender.getName() + " Failed (invalid syntaxis) to use " + msg;
		else if (outcome == Type.noperm)
			msg = sender.getName() + " was denied (no permission) to use " + msg; 
		else if (outcome == Type.failTried)
			msg = sender.getName() + " Failed/Tried to use " + msg;
		else if (outcome == Type.unknown)
			msg = sender.getName() + " used " + msg;
		else //IMPOSSIBRU
			msg = sender.getName() + " used " + msg;
		ogS("c", msg);
		if (notify)
			notifier(sender, outcome);
	}
	public static void og(CommandSender sender, Command cmd, String allArgs, Type outcome, boolean notify, boolean tellUsage){
		String logmsg = "/" + cmd.getName() + " " + allArgs;
		String usage = ChatColor.RED + "Correct usage: " + Usage.game(sender, cmd.getName());
		if (outcome == Type.success){
			logmsg = sender.getName() + " successfully used " + logmsg;
			tellUsage = false;
		} else if (outcome == Type.fail || outcome == Type.tooManyArgs || outcome == Type.notEnoughArgs)
			logmsg = sender.getName() + " Failed (invalid syntaxis) to use " + logmsg;
		else if (outcome == Type.noperm){
			logmsg = sender.getName() + " was denied (no permission) to use " + logmsg;
			tellUsage = false;
		} else if (outcome == Type.failTried)
			logmsg = sender.getName() + " Failed/Tried to use " + logmsg;
		else if (outcome == Type.refereeLimit)
			logmsg = sender.getName() + " Failed (referee limit) to use " + logmsg;
		else if (outcome == Type.unknown){
			logmsg = sender.getName() + " used " + logmsg;
			tellUsage = false;
		}
		else {
			logmsg = sender.getName() + " used " + logmsg;
			tellUsage = false;
		}
		ogS("c", logmsg);
		if (notify){
			notifier(sender, outcome);
			if (tellUsage)
				sender.sendMessage(usage);
		}
			
	}	
}
