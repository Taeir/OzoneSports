package com.aurean.ozonesports.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.aurean.ozonesports.Game;
import com.aurean.ozonesports.L;
import com.aurean.ozonesports.PermHandler;
import com.aurean.ozonesports.Sporter;
import com.aurean.ozonesports.L.Type;

public class CmdGame implements CommandExecutor {
	private String allArgs = "";
	private CommandSender sender;
	private Command cmd;
	@SuppressWarnings("unused")
	private String label = "";
	private String[] args;
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("game")) return false; //Only the game command should get executed here so this should never trigger.
		this.sender = sender;
		this.cmd = cmd;
		this.label = label;
		this.args = args;
		for (String arg : args) {
			if (!allArgs.equals("")) allArgs = allArgs + " " + arg;
			else allArgs = arg;
		}

		if (!PermHandler.has(sender, "game")){
			L.og(sender, cmd, allArgs, Type.noperm, true, false);
			return true;
		}
		
		switch (args.length){
		case 0:
			L.og(sender, cmd, allArgs + " (help)", Type.success, false, false);
			help(sender, "main");
			break;
		case 1:
			if (args[0].equalsIgnoreCase("help")){
				L.og(sender, cmd, allArgs, Type.success, false, false);
				help(sender, "main");
			}
			else if (args[0].equalsIgnoreCase("allowjoin")) allowJoin();
			else if (args[0].equalsIgnoreCase("start")) start();
			else L.og(sender, cmd, allArgs, Type.fail, true, true);
			break;
		case 2:
			if (args[0].equalsIgnoreCase("start")) start();
			break;
		case 3:
			if (args[0].equalsIgnoreCase("allowjoin")) allowJoin();
			else if (args[0].equalsIgnoreCase("start")) start();
			else L.og(sender, cmd, allArgs, Type.fail, true, true);
			break;
		}
		return true;
	}
	
	private void allowJoin(){
		if (!PermHandler.has(sender, "mod.game.allowjoin")){
			L.og(sender, cmd, allArgs, Type.noperm, true, false);
			return;
		}
		if (args.length < 3){
			L.og(sender, cmd, allArgs, Type.notEnoughArgs, true, false);
			return;
		}
		if (args.length > 3){
			L.og(sender, cmd, allArgs, Type.tooManyArgs, true, false);
			return;
		}
		
		L.og(sender, cmd, allArgs, Type.success, false, false);
		boolean setTo = false;
		if (args.length == 3)
		if (args[2].equalsIgnoreCase("no") || args[2].equalsIgnoreCase("false"))
			setTo = false;
		else if (args[2].equalsIgnoreCase("yes") || args[2].equalsIgnoreCase("true"))
			setTo = true;
		else {
			L.og(sender, cmd, allArgs, Type.fail, true, false);
			sender.sendMessage(ChatColor.RED + "Correct usage: /game allowjoin <name> <yes/true/no/false>");
			return;
		}
		Game.getGameByType(Sporter.getGameTypeByString(args[1])).setJoinable(setTo);
	}
	
	private void start(){
		
	}
	
	private void help(CommandSender sender, String subject){
		if (subject.equalsIgnoreCase("main")){
			sender.sendMessage("Right click a sign to Join that Game Type.");
			sender.sendMessage(ChatColor.AQUA + "Normal Game Types (Everyone)");
			sender.sendMessage(ChatColor.AQUA + "American Football, Soccer, Golf, Tennis");
			sender.sendMessage(ChatColor.GREEN + "Donator Game Types (Purchase @ donate.aurean.com)");
			sender.sendMessage(ChatColor.GREEN + "V.I.P. Football");
			//vip football will be the same just for donors only
		}
		else if (subject.equalsIgnoreCase("allowjoin")){
			sender.sendMessage(ChatColor.YELLOW + "Placeholder");
		}
	}
}
