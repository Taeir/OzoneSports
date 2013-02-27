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
		if (cmd.getName().equalsIgnoreCase("game")){
			if (!Permission.has(sender, "game")){
				// If no perm, then log the command and return so it stops executing more code.
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			switch (args.length){
			case 0:
				L.og(sender, cmd, allArgs + " (help)", Type.success, false, false);
				//Add help for /game here
				break;
			case 1:
				if (args[0].equalsIgnoreCase("allowjoin")){
					
				}
					
				break;
			case 2:
				break;
			}
			
			
			return true;
		}
		else if (cmd.getName().equalsIgnoreCase("osreload")){
			if (!Permission.has(sender, "mod.reload")){
				// If no perm, then log the command and return so it stops executing more code.
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			L.og(sender, cmd, allArgs, Type.success, false, false); // Log the command
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "OzoneSports " + plugin.getDescription().getVersion() + " reloaded!");
			return true;
		}
		return true;
	}

}
