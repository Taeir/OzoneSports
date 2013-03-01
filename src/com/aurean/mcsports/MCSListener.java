package com.aurean.mcsports;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MCSListener implements Listener{
	
	//This event should only be enabled if a game is started, and should be disabled when the game stops.
	public void onPlayerMore(PlayerMoveEvent event){
		Player player = event.getPlayer();
		MCSPlayer mcsPlayer = MCSPlayer.getMCSPlayer(player);
		
		if (!mcsPlayer.isInGame()) return;
		Game currentGame = mcsPlayer.getCurrentGame();
		if (!currentGame.isRunning()) return;
		
		//Check if out of bounds
		Location PlayerLocation = player.getLocation();
		double PlayerX = PlayerLocation.getX();
		double PlayerY = PlayerLocation.getY();
		double PlayerZ = PlayerLocation.getZ();
		float PlayerYaw = PlayerLocation.getYaw();
		float PlayerPitch = PlayerLocation.getPitch();
		double FieldLX = currentGame.getField().getLargest("x");
		double FieldLZ = currentGame.getField().getLargest("z");
		double FieldSX = currentGame.getField().getSmallest("x");
		double FieldSZ = currentGame.getField().getSmallest("z");
		
		//If player not within field, then teleport him to the last field coord maintaining his orientation.
		if (PlayerX>FieldLX){
			player.sendMessage(ChatColor.RED + "You went out of bounds!");
			player.teleport(new Location(player.getWorld(), FieldLX, PlayerY, PlayerZ, PlayerYaw, PlayerPitch));
		}
		else if (PlayerX<FieldSX){
			player.sendMessage(ChatColor.RED + "You went out of bounds!");
			player.teleport(new Location(player.getWorld(), FieldSX, PlayerY, PlayerZ, PlayerYaw, PlayerPitch));
		}
		if (PlayerZ>FieldLZ){
			player.sendMessage(ChatColor.RED + "You went out of bounds!");
			player.teleport(new Location(player.getWorld(), PlayerX, PlayerY, FieldLZ, PlayerYaw, PlayerPitch));
		}
		else if (PlayerZ<FieldSZ){
			player.sendMessage(ChatColor.RED + "You went out of bounds!");
			player.teleport(new Location(player.getWorld(), PlayerX, PlayerY, FieldSZ, PlayerYaw, PlayerPitch));
		}
	}
}
