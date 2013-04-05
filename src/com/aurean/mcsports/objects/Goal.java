package com.aurean.mcsports.objects;

import org.bukkit.Location;
import org.bukkit.util.Vector;


public class Goal {
	@Override
	public String toString() {
		return "Goal [field=" + field.name + ", team=" + team.getName() + ", corner1="
				+ corner1 + ", corner2=" + corner2 + "]";
	}
	private GameField field;
	private Team team;
	Location corner1;
	Location corner2;

	/**
	 * An unassigned goal.
	 * @param l1
	 * @param l2
	 * @param field
	 */
	public Goal(Location l1, Location l2, GameField field){
		this.setTeam(null);
		this.corner1 = l1;
		this.corner2 = l2;
		this.field = field;
	}
	
	public Goal(Team team, Location l1, Location l2, GameField field){
		this.setTeam(team);
		this.corner1 = l1.clone();
		this.corner2 = l2.clone();
		this.field = field;
	}
	
	public Location[] getCorners(){
		return new Location[] { corner1, corner2 };
	}
	
	public GameField getField(){
		return field;
	}
	public void setField(GameField field) {
		this.field = field;
	}
	public boolean containsBlock(Location loc){
		Vector v1 = Vector.getMinimum(corner1.toVector(), corner2.toVector());
		Vector v2 = Vector.getMaximum(corner1.toVector(), corner2.toVector());
		return (loc.getX() >= v1.getX()) && (loc.getX() <= v2.getX()) &&
				(loc.getZ() >= v1.getZ()) && (loc.getZ() <= v2.getZ());
		//returns true if the coords are within the 2 corners.
	}

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
}
