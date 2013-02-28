package com.aurean.ozonesports.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.aurean.ozonesports.L;
import com.aurean.ozonesports.OzoneSports;
import com.aurean.ozonesports.PermHandler;
import com.aurean.ozonesports.L.Type;

public class CmdOzonesports implements CommandExecutor {
	private String allArgs = "";
	private CommandSender sender;
	@SuppressWarnings("unused")
	private Command cmd;
	@SuppressWarnings("unused")
	private String[] args;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("ozonesports")) return false; //Only the ozonesports command should get executed here so this should never trigger.
		this.allArgs = "";
		this.sender = sender;
		this.cmd = cmd;
		this.args = args;
		for (String arg : args) {
			if (!allArgs.equals("")) allArgs = allArgs + " " + arg;
			else allArgs = arg;
		}
		
		if (!PermHandler.has(sender, "ozonesports")){
			L.og(sender, cmd, allArgs, Type.noperm, true, false);
			return true;
		}
		
		if (args.length==0){
			L.og(sender, cmd, allArgs, Type.success, false, false);
			sender.sendMessage(ChatColor.GREEN + "Server Plugin Coded By: Taeir and Mayoz");
			sender.sendMessage(ChatColor.GREEN + "Server Maps Created By: Ozoneman, Bugsrus");
			return true;
		}
		else if (args.length == 1){
			if (args[0].equalsIgnoreCase("help")){
				L.og(sender, cmd, allArgs, Type.success, false, false);
				help("main");
				return true;
			} else if (args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("credits")){
				L.og(sender, cmd, allArgs, Type.success, false, false);
				sender.sendMessage(ChatColor.GREEN + "Server Plugin Coded By: Taeir and Mayoz");
				sender.sendMessage(ChatColor.GREEN + "Server Maps Created By: Ozoneman, Bugsrus");
				return true;
			} else if (args[0].equalsIgnoreCase("reload")){
				if (!PermHandler.has(sender, "admin.reload")){
					L.og(sender, cmd, allArgs, Type.noperm, true, false);
					return true;
				}
				
				L.og(sender, cmd, allArgs, Type.success, false, false);
				OzoneSports.getInstance().reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "OzoneSports " + OzoneSports.getInstance().getDescription().getVersion() + " reloaded!");
				return true;
			}
		}
		//If still not returned, the subcommand was invalid or the amount of arguments too big.
		L.og(sender, cmd, allArgs, Type.fail, true, true);
		return true;
	}
	
	private void help(String subject){
		if (subject.equalsIgnoreCase("main")){
			sender.sendMessage(ChatColor.YELLOW + "WIP");//placeholder
		}
	}
}