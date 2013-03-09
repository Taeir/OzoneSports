package com.aurean.mcsports.objects;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.aurean.mcsports.Game;
import com.aurean.mcsports.GameField;
import com.aurean.mcsports.MCSports;
import com.aurean.mcsports.Settings;

public class EntityBall extends Ball {
	protected Entity entity;
	private int threadid = 0;
	private Game game;
	private static ArrayList<EntityBall> allEntityBalls = new ArrayList<EntityBall>();
	public EntityBall(String name, Entity e, Game game) {
		super(name, e.getLocation());
		this.entity = e;
		this.game = game;
		allEntityBalls.add(this);
	}

	@Override
	public void fix() {
		Entity newEntity = entity.getWorld().dropItem(entity.getLocation(), new ItemStack(Settings.EntityBall, 1));
	    entity.remove();
	    entity = newEntity;
	}
	
	public static void fix(EntityBall ball){
		ball.fix();
	}
	public static void fix(Entity e){
		getEntityBall(e).fix();
	}
	public static EntityBall getEntityBall(Entity e){
		for (EntityBall current : allEntityBalls){
			if (current.entity.equals(e)) return current;
		}
		return null;
	}

	@Override
	public Location getLocation() {
		return entity.getLocation();
	}

	@Override
	public void kick(Vector v) {
		entity.setVelocity(v);
		Bukkit.getServer().getScheduler().cancelTask(threadid);
		final EntityBall temp = this;
		threadid = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(MCSports.getInstance(), new Runnable() {
			@Override
			public void run() {
				temp.fix();
			} }, 0L, 2400L);
		GameField field = game.getField();
		Location newLoc = entity.getLocation();
		if (!field.isInField(newLoc)){
			//Out of bounds
			double blockX = newLoc.getX();
			double blockZ = newLoc.getZ();
			if (blockX>field.xLargest) newLoc.setX(field.xLargest);
			else if (blockX<field.xSmallest) newLoc.setX(field.xSmallest);
			if (blockZ>field.zLargest) newLoc.setZ(field.zLargest);
			else if (blockZ<field.zSmallest) newLoc.setZ(field.zSmallest);
			//Set the ball's location to the closest fieldborder.
			entity.teleport(newLoc);
		}
		for (Goal current : field.getGoals()){
			if (current.containsBlock(newLoc)){
				reset();
				game.addPoint(current);
			}
		}
		
	}

	@Override
	public void setBallObjectLocation(Location location) {
		entity.teleport(location);
	}

	@Override
	public void reset() {
		Vector v = new Vector();
	    v.setX(0); v.setZ(0); v.setY(0);
	    entity.setVelocity(v);
	    entity.teleport(getSource());
	}

}
