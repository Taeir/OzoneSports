package com.aurean.mcsports.objects;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;

import com.aurean.mcsports.Game;

public abstract class Ball {
	private Location loc;
	private String name;
	
	public Ball(String name, Location loc){
		this.name = name;
		this.loc = loc;
	}
	
	public String getName(){
		return this.name;
	}
	public Location getSource(){
		return this.loc;
	}
	
	public abstract void fix();
	public abstract Location getLocation();
	public abstract void kick(Vector paramVector);
	public abstract void setBallObjectLocation(Location paramLocation);
	public abstract void reset();
	//Abstract methods must be inherited by anything that extends Ball. This means that eclipse will notify if
	//you forget to add one of these methods.
	
	public boolean isInAField(Location l){
		for (GameField current : GameField.getAllFields()){
			if (current.isInField(l)) return true;
		}
		return false;
	}
	
	public boolean isInFieldOfGame(Location l, Game game){
		if (game.getField().isInField(l)) return true;
		else return false;
	}
	
	public Location getClosestAir(Location l){
		World world = l.getWorld();
		Location newLocation = l;
		int type = world.getBlockAt(l).getTypeId();
		while (type != 0){
			newLocation.add(1, 0, 0); //+1,0,0
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.subtract(2, 0, 0); //-1,0,0
			}
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.add(1, 0, 1); //0,0,+1
			}
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.subtract(0, 0, 2);//0,0,-1
			}//0,0,x done
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.subtract(0, 0, 1); //-1,0,-1
			}
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.add(0, 0, 2); //-1,0,+1
			}//-1,0,x done
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.add(2, 0, 0); //+1,0,+1
			}
			if (world.getBlockAt(newLocation).getTypeId() != 0){
				newLocation.subtract(0, 0, 2); //+1,0,-1
			}//+1,0,x done
			
			if (world.getBlockAt(newLocation).getTypeId() == 0){
				break;
			} else {
				while (true){
					double adderX = Math.round((Math.random()) - (Math.random()));//Max +1, min -1
					double adderZ = Math.round((Math.random()) - (Math.random()));//Max +1, min -1
					if (adderX==0 && adderZ == 0) continue;//do again if both return 0
					
					newLocation.add(adderX, 0, adderZ);
					break;
					// i cannot think of a better way to do this so meh...
				}
			}
		}
		//This whole mess checks in a square around a block for air. If it doesnt find any, it will
		//add a random block and try again.
		return newLocation;
	}
}
