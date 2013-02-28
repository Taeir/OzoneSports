/*package com.aurean.ozonesports;

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
			if (!allArgs.equals("")) allArgs = allArgs + " " + arg;
			else allArgs = arg;
		}
		if (cmd.getName().equalsIgnoreCase("game")){
			if (!PermHandler.has(sender, "game")){
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			switch (args.length){
			case 0:
				L.og(sender, cmd, allArgs + " (help)", Type.success, false, false);
				help(sender, "game");
				break;
			case 1:
				if (args[0].equalsIgnoreCase("help")){
					L.og(sender, cmd, allArgs, Type.success, false, false);
					help(sender, "game");
				}
				else if (args[0].equalsIgnoreCase("allowjoin")) gameAllowJoin(sender, cmd, allArgs, args);
				else if (args[0].equalsIgnoreCase("start")) gameStart(sender, cmd, allArgs, args);
				else L.og(sender, cmd, allArgs, Type.fail, true, true);
				break;
			case 2:
				if (args[0].equalsIgnoreCase("start")) gameStart(sender, cmd, allArgs, args);
				break;
			case 3:
				if (args[0].equalsIgnoreCase("allowjoin")) gameAllowJoin(sender, cmd, allArgs, args);
				else if (args[0].equalsIgnoreCase("start")) gameStart(sender, cmd, allArgs, args);
				else L.og(sender, cmd, allArgs, Type.fail, true, true);
				break;
			}
			
			
			return true;
		} else if (cmd.getName().equalsIgnoreCase("ozonesports")){
			if (!PermHandler.has(sender, "ozonesports")){
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			if (args.length==0){
				L.og(sender, cmd, allArgs + " (help)", Type.success, false, false);
				sender.sendMessage(ChatColor.GREEN + "Server Plugin Coded By: Taeir and Mayoz");
				sender.sendMessage(ChatColor.GREEN + "Server Maps Created By: Ozoneman, Bugsrus");
				return true;
			}
			else if (args.length == 1){
				if (args[0].equalsIgnoreCase("help")){
					L.og(sender, cmd, allArgs, Type.success, false, false);
					help(sender, "ozonelayer");
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
					plugin.reloadConfig();
					sender.sendMessage(ChatColor.GREEN + "OzoneSports " + plugin.getDescription().getVersion() + " reloaded!");
					return true;
				}
			}
			//If still not returned, the subcommand was invalid or the amount of arguments too big.
			L.og(sender, cmd, allArgs, Type.fail, true, true);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("referee")){
			if(!(sender instanceof Player)){
				sender.sendMessage("You cannot use this command from the Console!");
				L.og(sender, cmd, allArgs, Type.fail, false, false);
				return true;
			}
			
			if (!PermHandler.has(sender, "donor.referee")){
				sender.sendMessage(ChatColor.YELLOW + "Apply for Referee at www.aurean.com/something");
				L.og(sender, cmd, allArgs, Type.noperm, true, false);
				return true;
			}
			
			L.og(sender, cmd, allArgs, Type.success, false, false);
			Player player = (Player) sender;
			
			String nameBefore = sender.getName();

			if(!Game.refs.contains(player)){
				if(!(Game.refnum == 2)) {
				Game.refnum++;
				EntityPlayer ep = ((CraftPlayer) sender).getHandle(); //Setting the name
				player.setDisplayName(ChatColor.BLACK + nameBefore);
				ep.name = ChatColor.BLACK + "" + nameBefore;
				for (Player p : Bukkit.getOnlinePlayers()){
					//if(p != sender){
					//can never be true.
					//Only for int, boolean, short, long, float, double and other primitive times you can use ==
					//For all other types you need a .equals
					if (!p.equals(player)){
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(new Packet20NamedEntitySpawn(ep));
					}
				}
				sender.sendMessage(ChatColor.GREEN + "You have become a Referee!");
				// I must say i do not understand what you are doing here...
				return true;
				} else
					L.og(sender, cmd, allArgs, Type.refereeLimit, true, false);
			} else {
				Game.refs.remove(sender);
				Game.refnum--;
				EntityPlayer ep = ((CraftPlayer) sender).getHandle();
				player.setDisplayName(nameBefore);
				ep.name = nameBefore;
				for(Player p : Bukkit.getOnlinePlayers()){
					if(p != sender){
						((CraftPlayer) p).getHandle().playerConnection.sendPacket(new Packet20NamedEntitySpawn(ep));
					}
				}
				sender.sendMessage("You are no longer referee.");
			}
			// For what i can understand, you want to keep track of the amount of referees per player.
			// I suggest either moving this to sporter,
			// Or I can modify the player object to add referee as a method. So we could use player.getRefereeCount() and add values to that.
			// Downside of this is that you will need a modified craftbukkit.jar for your server :(
		}
		return true;
	}
	
	public void help(CommandSender sender, String command){
		if (command.equalsIgnoreCase("game")){
			sender.sendMessage("Right click a sign to Join that Game Type.");
			sender.sendMessage(ChatColor.AQUA + "Normal Game Types (Everyone)");
			sender.sendMessage(ChatColor.AQUA + "American Football, Soccer, Golf, Tennis");
			sender.sendMessage(ChatColor.GREEN + "Donator Game Types (Purchase @ donate.aurean.com)");
			sender.sendMessage(ChatColor.GREEN + "V.I.P. Football");
			//vip football will be the same just for donors only
		}
		if (command.equalsIgnoreCase("game allowjoin")){
			sender.sendMessage(ChatColor.YELLOW + "Placeholder");
		}
	}
	
	private void gameAllowJoin(CommandSender sender, Command cmd, String allArgs, String[] args){
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
	
	private void gameStart(CommandSender sender, Command cmd, String allArgs, String[] args){
		
	}
}
*/