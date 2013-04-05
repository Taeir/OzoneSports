package com.aurean.mcsports.basketball;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.util.Vector;

import com.aurean.mcsports.Game;
import com.aurean.mcsports.MCSPlayer;
import com.aurean.mcsports.MCSports;

public class EntityListener implements Listener{
	private Vector getReflectedVector(Projectile oldProjectile, Vector oldVelocity)
	{
		Vector newVector = oldVelocity.clone();

		newVector.setY(-newVector.getY());

		Vector normalized = oldVelocity.normalize();

		double x = normalized.getX();
		double z = normalized.getZ();

		Block loc = oldProjectile.getLocation().getBlock();

		if ((x > 0.0D) && (loc.getRelative(BlockFace.SOUTH).getType() != Material.AIR))
			newVector.setX(-newVector.getX());
		else if ((x < 0.0D) && (loc.getRelative(BlockFace.NORTH).getType() != Material.AIR)) {
			newVector.setX(-newVector.getX());
		}
		if ((z > 0.0D) && (loc.getRelative(BlockFace.WEST).getType() != Material.AIR))
			newVector.setZ(-newVector.getZ());
		else if ((z < 0.0D) && (loc.getRelative(BlockFace.EAST).getType() != Material.AIR)) {
			newVector.setZ(-newVector.getZ());
		}
		return newVector;
	}
	
	private Snowball makeNewBall(Projectile oldProjectile, Vector newVector)
	{
		Game oldGame = (Game) oldProjectile.getMetadata("Game").get(0).value();
		if (!oldGame.getField().isInField(oldProjectile.getLocation())){
			oldGame.sendMessage("Out of bounds!");
			return null;
		}
		
		World world = oldProjectile.getWorld();
		
		Snowball ball = world.spawn(oldProjectile.getLocation(), Snowball.class);

		ball.setVelocity(newVector);
		ball.setBounce(true);
		ball.setShooter(oldProjectile.getShooter());
		
		ball.setMetadata("Game", new FixedMetadataValue(MCSports.getInstance(), oldGame));
		return ball;
	}
	
	@EventHandler
	private void onSnowballHit(ProjectileHitEvent event){
		if (!(event.getEntity() instanceof Snowball)) return;
		Snowball ball = (Snowball) event.getEntity();
		Snowball newBall = null;
		newBall = makeNewBall(ball, getReflectedVector(ball, ball.getVelocity().multiply(0.5D)));
		if (newBall == null) return;
		Material blockType = newBall.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
		
		//if (blockType == Material.SAND) {
		//	newBall.setVelocity(newBall.getVelocity().multiply(0.7D));
		//}
		//if (blockType == Material.LEAVES) {
		//	newBall.setVelocity(newBall.getVelocity().multiply(0.1D));
		//}
		if (blockType == Material.GRASS) {
			newBall.setVelocity(newBall.getVelocity().multiply(0.7D));
		}
	}
	
	@EventHandler
	private void onShootBow(EntityShootBowEvent event){
		if (!(event.getEntity() instanceof Player)) return;
		Player shooter = (Player) event.getEntity();
		MCSPlayer test = MCSPlayer.getMCSPlayer(shooter);
		Game game = test.getGame();
		if (game == null) return;
		if (!(game instanceof BasketBallGame)) return;
		BasketBallGame bbGame = (BasketBallGame) game;
		
		if (!test.hasBall()){
			event.setCancelled(true);
			shooter.sendMessage(ChatColor.RED + "You currently don't have the ball!");
			return;
		}
		
		Snowball ball = (Snowball) event.getProjectile();
		ball.setMetadata("Game", new FixedMetadataValue(MCSports.getInstance(), bbGame));
		
	}
	
}
