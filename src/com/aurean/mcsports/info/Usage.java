package com.aurean.mcsports.info;

import org.bukkit.command.CommandSender;

public class Usage {
	public static String game(CommandSender sender, String subtype){
		subtype = subtype.toLowerCase();
		if (subtype.equals("game"))
			return "Use /game help for more information about the usage.";
		else if (subtype.equals("ozonesports"))
			return "/sports <reload/info>";
		return "";
	}
}
