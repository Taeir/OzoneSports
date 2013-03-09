package com.aurean.mcsports;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import com.aurean.mcsports.config.Config;
import com.aurean.mcsports.info.Usage;

public class L {
	/**
	 * <br><p>This <b>must</b> be called when the plugin is being disabled.<br>
	 * It clears any static references in its class and thus prevents memory leaks.</p>
	 */
	public static void disablePlugin(){
		
	}

	/**
	 * <br><p>The result type of a command.</p>
	 */
	public static enum Type{
		/**
		 * <br>A command that was executed successfully.
		 */
		success, /**
		 * <br>A command that failed because of invalid syntaxis.
		 */
		fail, /**
		 * <br>A command that failed because of too many arguments.
		 */
		tooManyArgs, /**
		 * <br>A command that failed because of not enough arguments.
		 */
		notEnoughArgs, /**
		 * <br>A command that failed because the sender has no permission for it.
		 */
		noperm, /**
		 * <br>A command that failed because the sender reached his referee limit.
		 */
		refereeLimit, /**
		 * <br>A command that failed because the sender is already a referee.
		 */
		alreadyReferee, /**
		 * @deprecated Usage of this reason is not advised.
		 * A command that failed because of an unknown reason.
		 */
		unknown
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
	
	private static Level getLevelByString(String lvl){
		//FYI this has a specific order so the most common used item should get found first.
		if (lvl.equalsIgnoreCase("info") || lvl.equalsIgnoreCase("i")) return Level.INFO;
		else if (lvl.equalsIgnoreCase("command") || lvl.equalsIgnoreCase("c")) return Level.parse("PLAYER_COMMAND");
		else if (lvl.equalsIgnoreCase("warning") || lvl.equalsIgnoreCase("w")) return Level.WARNING;
		else if (lvl.equalsIgnoreCase("fine") || lvl.equalsIgnoreCase("f")) return Level.FINE;
		else if (lvl.equalsIgnoreCase("severe") || lvl.equalsIgnoreCase("s")) return Level.SEVERE;
		else if (lvl.equalsIgnoreCase("debug")) return Level.parse("DEBUG");
		else if (lvl.equalsIgnoreCase("config")) return Level.CONFIG;
		else if (lvl.equalsIgnoreCase("finer")) return Level.FINER;
		else if (lvl.equalsIgnoreCase("finest")) return Level.FINEST;
		else return Level.INFO;
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
	
	
	/**
	 * <br>The main Logging class.<br>
	 * Usage of logging: L.og.method(arguments);
	 */
	public static class og {
		/**
		 * <br><p>The same as L.og.standard(sender, cmd, allArgs, outcome, false, false)<br>
		 * {@link #standard(CommandSender, Command, String, Type, boolean, boolean)}</p>
		 */
		public static void standard(CommandSender sender, Command cmd, String allArgs, Type outcome){
			standard(sender, cmd, allArgs, outcome, false, false);
		}
		/**
		 * <br><p>The same as L.og.standard(sender, cmd, allArgs, outcome, notify, false)
		 * {@link #standard(CommandSender, Command, String, Type, boolean, boolean)}</p>
		 */
		public static void standard(CommandSender sender, Command cmd, String allArgs, Type outcome, boolean notify){
			standard(sender, cmd, allArgs, outcome, notify, false);
		}
		/**
		 * <br><p>Logs a message to the console and server.log</p>
		 * 
		 * @param notify - Whether or not to notify the user of his mistake. Example: "You dont have permission!"
		 * @param tellUsage - Whether or not to tell the correct usage to the sender. Example: "Correct usage: /say message"
		 */
		public static void standard(CommandSender sender, Command cmd, String allArgs, Type outcome, boolean notify, boolean tellUsage){
			if (Config.Logging.getEnabled.command(outcome)){
				String logmsg = replaceExtra(Config.Logging.getFormat.command(outcome), sender, cmd, allArgs, outcome);
				Logger.getLogger("Minecraft").log(Config.Logging.getLevel.command(), logmsg);
			}
			String usage = ChatColor.RED + "Correct usage: " + Usage.game(sender, cmd.getName());
			if (outcome == Type.success || outcome == Type.noperm) tellUsage = false;
			if (notify) notifier(sender, outcome);
			if (tellUsage) sender.sendMessage(usage);
		}

		/**
		 * <br><p>Logs a message to the console and server.log</p>
		 * 
		 * @param notify - Whether or not to notify the user of his mistake. Example: "You dont have permission!"
		 */
		public static void alternate(CommandSender sender, String msg, Type outcome, boolean notify){
			if (Config.Logging.getEnabled.command(outcome)){
				String logmsg = replaceExtra(Config.Logging.getFormat.command(outcome), sender, msg, outcome);
				Logger.getLogger("Minecraft").log(Config.Logging.getLevel.command(), logmsg);
			}
			if (notify) notifier(sender, outcome);
		}
		/**
		 * <br><p>Logs a message with the plugin prefix to the console and server.log<br>
		 * Example: [LEVEL] [MCSports] message</p>
		 */
		public static void plugin(Level lvl, String msg){
			MCSports.logger.log(lvl, msg);
		}
		/**
		 * <br><p>Logs a message with the plugin prefix to the console and server.log<br>
		 * Example: [LEVEL] [MCSports] message</p>
		 */
		public static void plugin(String lvl, String msg){
			MCSports.logger.log(getLevelByString(lvl), msg);
		}
		/**
		 * <br><p>Logs a message to the console and server.log<br>
		 * Example: [LEVEL] message</p>
		 */
		public static void noPrefix(String lvl, String msg){
			Logger.getLogger("Minecraft").log(getLevelByString(lvl), msg);
		}
		/**
		 * <br><p>Logs a message to the console and server.log<br>
		 * Example: [LEVEL] message</p>
		 */
		public static void noPrefix(Level lvl, String msg){
			Logger.getLogger("Minecraft").log(lvl, msg);
		}
	}
}
