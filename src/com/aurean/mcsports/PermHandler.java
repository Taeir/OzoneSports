package com.aurean.mcsports;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PermHandler {
	public static boolean has(CommandSender sender, String shortPermission){
		return sender.hasPermission("mcsports."+shortPermission);
	}
	public static boolean has(Player player, String shortPermission){
		return player.hasPermission("mcsports."+shortPermission);
	}
}
