package com.aurean.mcsports;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderSignal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class MCSListener implements Listener{
	
	//This event should only be enabled if a game is started, and should be disabled when the game stops.
	public void onPlayerMove(PlayerMoveEvent event){
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

	@EventHandler
	public void onShoot(EntityShootBowEvent event){
		World world = event.getEntity().getWorld();
		Entity proj = event.getProjectile();
		Snowball newProj = world.spawn(proj.getLocation(), Snowball.class);
		newProj.setShooter(event.getEntity());
		newProj.setVelocity(proj.getVelocity());
		newProj.setBounce(true);
		event.setProjectile(newProj);
	}
	
	@EventHandler
	public void onPlayerRightClick(PlayerInteractEvent event){
		Player bukkitPlayer = event.getPlayer();
		@SuppressWarnings("unused")
		MCSPlayer mcsPlayer = MCSPlayer.getMCSPlayer(bukkitPlayer);
		
		//if (!mcsPlayer.isInGame()) return;
		if (bukkitPlayer.getItemInHand().getType().equals(Material.SLIME_BALL) && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)){
			org.bukkit.World world = bukkitPlayer.getWorld();
			EnderSignal ball = (EnderSignal) world.spawnEntity(bukkitPlayer.getLocation(), EntityType.ENDER_SIGNAL);
			ball.setVelocity(new Vector(bukkitPlayer.getLocation().getX()+10, bukkitPlayer.getLocation().getY(), bukkitPlayer.getLocation().getZ()));
			
			

			/*
			//L.og.plugin("debug", "BukkitPlayer UUID: " + bukkitPlayer.getUniqueId());
			
			CraftServer craftServer = (CraftServer) Bukkit.getServer();
			org.bukkit.World bukkitWorld = bukkitPlayer.getWorld();
			WorldServer mcWorld = null;
			EntityHuman mcEntityHuman = null;
			@SuppressWarnings("unused")
			HumanEntity bukkitHumanEntity = null;

			//Get the mcWorld
			for (WorldServer world2 : craftServer.getServer().worlds) if (world2.getWorld().getUID() == bukkitWorld.getUID()) {mcWorld = world2; break;}
			
			//Get the mcEntityHuman
			mcEntityHuman = (EntityHuman) mcWorld.getEntity(bukkitPlayer.getEntityId());
			bukkitHumanEntity = mcEntityHuman.getBukkitEntity(); //CraftHumanEntity --> HumanEntity
			/*for (Object current : mcWorld.entityList){
				if (current instanceof EntityHuman && ((EntityHuman) current).getBukkitEntity().getUniqueId().equals(bukkitPlayer.getUniqueId())){
					L.og.plugin("debug", "net.minecraft.server.v1_4_R1.EntityHuman");
					mcEntityHuman = (EntityHuman) current;
					break;
				}
			}
			
			//bukkitHumanEntity
			for (Entity current : bukkitWorld.getEntities()){
				if (current instanceof HumanEntity && ((HumanEntity) current).getUniqueId().equals(bukkitPlayer.getUniqueId())){
					bukkitHumanEntity = (HumanEntity) current;
					break;
				}
			}
			
			//L.og.plugin("debug", "BukkitPlayer id: " +bukkitPlayer.getEntityId());
			//L.og.plugin("debug", "BukkitHumanEntityId: " +bukkitHumanEntity.getEntityId());
			//L.og.plugin("debug", "McEntityHumanId: " +mcEntityHuman.id);
			//L.og.plugin("debug", "McWorld.getEntity(McEntityHuman.id) UUID: " + mcWorld.getEntity(mcEntityHuman.id).uniqueId);
			
			//EntityEnderSignal enderEye = new EntityEnderSignal(null, bukkitPlayer.getLocation().getX(), bukkitPlayer.getLocation().getY(), bukkitPlayer.getLocation().getZ());
			//CraftEnderSignal bukkitEnderEye = (CraftEnderSignal) enderEye.getBukkitEntity();
			//bukkitEnderEye.setVelocity(new Vector(1, 1, 1));
			//bukkitEnderEye.setMomentum(new Vector(1, 1, 1));
			//bukkitEnderEye.setMomentum(value)
			*/
		}
		
	}
}
