package com.aurean.mcsports.commands;

import net.minecraft.server.v1_4_R1.EntityPlayer;
import net.minecraft.server.v1_4_R1.Packet20NamedEntitySpawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.aurean.mcsports.Game;
import com.aurean.mcsports.L;
import com.aurean.mcsports.MCSPlayer;
import com.aurean.mcsports.PermHandler;
import com.aurean.mcsports.L.Type;

public class CmdReferee implements CommandExecutor {
	private String allArgs = "";
	private CommandSender sender;
	@SuppressWarnings("unused")
	private Command cmd;
	@SuppressWarnings("unused")
	private String[] args;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("referee")) return false; //Only the referee command should get executed here so this should never trigger.
		this.allArgs = "";
		this.sender = sender;
		this.cmd = cmd;
		this.args = args;
		for (String arg : args) {
			if (!allArgs.equals("")) allArgs = allArgs + " " + arg;
			else allArgs = arg;
		}
		
		if(!(sender instanceof Player)){
			sender.sendMessage("You cannot use this command from the Console!");
			L.og.standard(sender, cmd, allArgs, Type.fail, false, false);
			return true;
		}
		
		if (!PermHandler.has(sender, "donor.referee")){
			L.og.standard(sender, cmd, allArgs, Type.noperm, true, false);
			sender.sendMessage(ChatColor.YELLOW + "Apply for Referee at www.aurean.com/something");
			return true;
		}
		
		if (args.length == 0) help("main");
		
		Player player = (Player) sender;
		MCSPlayer osPlayer = MCSPlayer.getMCSPlayer(player);

		if(osPlayer.isReferee()){
			L.og.standard(sender, cmd, allArgs, Type.alreadyReferee, true, false);
			return true;
		}
		
		if(Game.getRefereeCount(osPlayer.getRefereeFor()) == 2) {
			L.og.standard(sender, cmd, allArgs, Type.refereeLimit, true, false);
			return true;
		}
		
		L.og.standard(sender, cmd, allArgs, Type.success, false, false);
		
		String nameBefore = sender.getName();
		
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
		return true;
		
		/*	
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
			sender.sendMessage("You are no longer a referee.");
		}
		return true;*/
	}
	
	private void help(String subject){
		if (subject.equals("main")){
			sender.sendMessage("Referee help");
		}
	}

}
