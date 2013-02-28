package com.aurean.mcsports.info;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.aurean.mcsports.L;
import com.aurean.mcsports.PermHandler;
import com.aurean.mcsports.L.Type;

@SuppressWarnings("unused")
public class Help {
	private static ChatColor a = ChatColor.AQUA;
	private static ChatColor da = ChatColor.DARK_AQUA;
	private static ChatColor w = ChatColor.WHITE;
	private static ChatColor r = ChatColor.RED;
	private static ChatColor o = ChatColor.GOLD;
	private static ChatColor y = ChatColor.YELLOW;
	private static ChatColor g = ChatColor.GREEN;
	private static ChatColor b = ChatColor.BLUE;
	private static ChatColor dp = ChatColor.DARK_PURPLE;
	private static ChatColor dr = ChatColor.DARK_RED;
	private static ChatColor dg = ChatColor.DARK_GREEN;
	private static ChatColor db = ChatColor.DARK_BLUE;
	private static ChatColor gray = ChatColor.GRAY;
	
	public static void game(CommandSender sender, String subcommand){
		if (!sendCaptionOrFail(sender, y + "             ------------- Game Help -------------             ", "game."+subcommand))
			return;
		sendIfPerm(sender, "", "game.");
	}
	
	private static void sendIfPerm(CommandSender s, String msg, String permission){
		if (PermHandler.has(s, permission))
			s.sendMessage(msg);
	}
	
	private static boolean sendCaptionOrFail(CommandSender s, String msg, String permission){
		if (PermHandler.has(s, permission)){
			s.sendMessage(msg);
			return true;
		}
		L.ogC(s, "/"+permission.replace(".", " ")+" (help)", Type.noperm, true);
		return false;
	}
}
