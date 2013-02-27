package com.aurean.ozonesports;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.aurean.ozonesports.L.Type;

public class OzoneCmdExecutor implements CommandExecutor {
	private static OzoneSports plugin = OzoneSports.getInstance();
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		String allArgs = "";
		for (String arg : args) {
			if (!allArgs.equals(""))
				allArgs = allArgs + " " + arg;
			else
				allArgs = arg;
		}
		if (cmd.getName().equalsIgnoreCase("osreload")){
			if (!Permission.has(sender, "mod.reload")){
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
				
			L.og(sender, cmd, allArgs, Type.success, false, false);
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "OzoneSports " + plugin.getDescription().getVersion() + " reloaded!");
			return true;
		}
		return true;
	}

}
