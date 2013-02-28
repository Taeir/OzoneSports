package com.aurean.ozonesports;

import java.util.ArrayList;

import net.minecraft.server.v1_4_R1.EntityPlayer;
import net.minecraft.server.v1_4_R1.Packet20NamedEntitySpawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

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
				// If no perm, then log the command and return so it stops executing more code
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			switch (args.length){
			case 0:
				L.og(sender, cmd, allArgs + " (help)", Type.success, false, false);
				//Add help for /game here
				sender.sendMessage("Right click a sign to Join that Game Type.");
				sender.sendMessage(ChatColor.AQUA + "Normal Game Types (Everyone)");
				sender.sendMessage(ChatColor.AQUA + "American Football, Soccer, Golf, Tennis");
				sender.sendMessage(ChatColor.GREEN + "Donator Game Types (Purchase @ donate.aurean.com)");
				sender.sendMessage(ChatColor.GREEN + "V.I.P. Football");
				//vip football will be the same just for donors only
				break;
			case 1:
				if (args[0].equalsIgnoreCase("help")){
					//Add the same help.
					sender.sendMessage("Right click a sign to Join that Game Type.");
					sender.sendMessage(ChatColor.AQUA + "Normal Game Types (Everyone)");
					sender.sendMessage(ChatColor.AQUA + "American Football, Soccer, Golf, Tennis");
					sender.sendMessage(ChatColor.GREEN + "Donator Game Types (Purchase @ donate.aurean.com)");
					sender.sendMessage(ChatColor.GREEN + "V.I.P. Football");
					//vip football will be the same just for donors only
				}
				else if (args[0].equalsIgnoreCase("allowjoin")){
					if (!Permission.has(sender, "game.allowjoin"))
						L.og(sender, cmd, allArgs, Type.noperm, true, false);
					else {
						L.og(sender, cmd, allArgs, Type.fail, true, false);
						sender.sendMessage(ChatColor.RED + "Correct usage: /game allowjoin <game> [yes/true/no/false]");
					}
				}else if(args[0].equalsIgnoreCase("info")){
					//Credits basicly
					sender.sendMessage(ChatColor.RED + "Server Plugin Coded By: Taeir, and Mayoz");
					sender.sendMessage(ChatColor.RED + "Server Maps Created By: Ozoneman, Bugsrus");
				}
					
				break;
			case 2:
				break;
			case 3:
				if (args[0].equalsIgnoreCase("allowjoin")){
					if (!Permission.has(sender, "game.allowjoin")){
						L.og(sender, cmd, allArgs, Type.noperm, true, false);
						return true;
					}
					
					L.og(sender, cmd, allArgs, Type.success, false, false);
					
					boolean setTo = false;
					if (args[2].equalsIgnoreCase("no") || args[2].equalsIgnoreCase("false"))
						setTo = false;
					else
						setTo = true;
					Game.getGameByType(Sporter.getGameTypeByString(args[1])).setJoinable(setTo);
				}
				break;
			}
			
			
			return true;
		} else if (cmd.getName().equalsIgnoreCase("osreload")){
			if (!Permission.has(sender, "mod.reload")){
				// If no perm, then log the command and return so it stops executing more code.
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			L.og(sender, cmd, allArgs, Type.success, false, false); // Log the command
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "OzoneSports " + plugin.getDescription().getVersion() + " reloaded!");
			return true;
			
		} else if (cmd.getName().equalsIgnoreCase("referee")){
			if (!Permission.has(sender, "donor.referee")){
				// If no perm, then log the command and return so it stops executing more code.
				sender.sendMessage(ChatColor.YELLOW + "Apply for Referee at www.aurean.com/something");
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			L.og(sender, cmd, allArgs, Type.success, false, false); // Log the command
			if(sender instanceof Player){
				String nameBefore = sender.getName();

					if(!Game.refs.contains(sender)){
						if(!(Game.refnum == 2)) {
						Game.refnum++;
						EntityPlayer ep = ((CraftPlayer) sender).getHandle(); //Setting the name
						((Player) sender).setDisplayName(ChatColor.BLACK + nameBefore);
						ep.name = ChatColor.BLACK + "" + nameBefore;
						for(Player p : Bukkit.getOnlinePlayers()){
							if(p != sender){
								((CraftPlayer) p).getHandle().playerConnection.sendPacket(new Packet20NamedEntitySpawn(ep));
							}
						}
					sender.sendMessage(ChatColor.GREEN + "You have become a Referee!");
					return true;
					}else{
						sender.sendMessage(ChatColor.RED + "Referee Limit reached!");
					L.og(sender, cmd, allArgs, Type.fail, false, false); // will make custom log message
					}
				} else {
					Game.refs.remove(sender);
						Game.refnum--;
						EntityPlayer ep = ((CraftPlayer) sender).getHandle();
						((Player) sender).setDisplayName(nameBefore);
						ep.name = nameBefore;
						for(Player p : Bukkit.getOnlinePlayers()){
							if(p != sender){
								((CraftPlayer) p).getHandle().playerConnection.sendPacket(new Packet20NamedEntitySpawn(ep));
							}
						}
						sender.sendMessage("You are no longer referee.");
				}
			}else{
				sender.sendMessage("You cannot use this command as Console!");
				L.og(sender, cmd, allArgs, Type.fail, false, false);
			}
		}
		return true;
	}
	
	public void help(CommandSender sender, String command){
		if (command.equalsIgnoreCase("game allowjoin")){
			sender.sendMessage(ChatColor.YELLOW + "Placeholder");
		}
	}
}
