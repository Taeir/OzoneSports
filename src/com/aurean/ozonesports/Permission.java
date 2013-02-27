package com.aurean.ozonesports;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Permission {
	public static enum Perm{
		reload
	};
	
	public static boolean has(CommandSender sender, String shortPermission){
		return sender.hasPermission("ozonesports."+shortPermission);
	}
	public static boolean has(Player player, String shortPermission){
		return player.hasPermission("ozonesports."+shortPermission);
	}
	public static boolean has(Player player, Perm permission){
		if (permission == Perm.reload)
			return player.hasPermission("ozonesports.mod.reload");
		return false;
	}
}
