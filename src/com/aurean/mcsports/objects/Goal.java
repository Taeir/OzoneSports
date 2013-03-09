package com.aurean.mcsports.objects;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import com.aurean.mcsports.GameField;

public class Goal {
	GameField field;
	public String name;
	Location corner1;
	Location corner2;
	
	public Goal(String teamName, Location l1, Location l2, GameField field){
		this.name = teamName;
		this.corner1 = l1;
		this.corner2 = l2;
		this.field = field;
	}
	
	public Location[] getCorners(){
		return new Location[] { corner1, corner2 };
	}
	
	public GameField getField(){
		return field;
	}
	public boolean containsBlock(Location loc){
		Vector v1 = Vector.getMinimum(corner1.toVector(), corner2.toVector());
		Vector v2 = Vector.getMaximum(corner1.toVector(), corner2.toVector());
		return (loc.getX() >= v1.getX()) && (loc.getX() <= v2.getX()) &&
				(loc.getZ() >= v1.getZ()) && (loc.getZ() <= v2.getZ());
		//returns true if the coords are within the 2 corners.
	}
}
