package com.aurean.ozonesports.commands;

import net.minecraft.server.v1_4_R1.EntityPlayer;
import net.minecraft.server.v1_4_R1.Packet20NamedEntitySpawn;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_4_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import com.aurean.ozonesports.Game;
import com.aurean.ozonesports.L;
import com.aurean.ozonesports.PermHandler;
import com.aurean.ozonesports.L.Type;

public class CmdReferee implements CommandExecutor {
	private String allArgs = "";
	@SuppressWarnings("unused")
	private CommandSender sender;
	@SuppressWarnings("unused")
	private Command cmd;
	@SuppressWarnings("unused")
	private String[] args;
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!cmd.getName().equalsIgnoreCase("referee")) return false; //Only the referee command should get executed here so this should never trigger.
		this.sender = sender;
		this.cmd = cmd;
		this.args = args;
		for (String arg : args) {
			if (!allArgs.equals("")) allArgs = allArgs + " " + arg;
			else allArgs = arg;
		}
		
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
			sender.sendMessage("You are no longer a referee.");
		}
		// For what i can understand, you want to keep track of the amount of referees per player.
		// I suggest either moving this to sporter,
		// Or I can modify the player object to add referee as a method. So we could use player.getRefereeCount() and add values to that.
		// Downside of this is that you will need a modified craftbukkit.jar for your server :(
		return true;
	}

}
