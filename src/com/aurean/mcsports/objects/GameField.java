package com.aurean.mcsports.objects;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Location;


public class GameField {
	public Location first, second;
	String name;
	int id = 0;
	private static int count;
	public int xLargest, xSmallest, zLargest, zSmallest;
	private static ArrayList<GameField> allFields = new ArrayList<GameField>();
	private ArrayList<Goal> goals = new ArrayList<Goal>();
	
	public GameField(String name, Location first, Location second, Goal goal1, Goal goal2){
		goals.add(goal1);
		goals.add(goal2);
		this.name = name;
		this.first = first;
		this.second = second;
		setSize();
		id = count;
		count++;
		allFields.add(this);
	}
	
	/**
	 * @param name
	 * @param first
	 * @param second
	 * @param goals A collection of goals to add (can be List<\Goal>, ArrayList<\Goal> etc.)
	 */
	public GameField(String name, Location first, Location second, Collection<? extends Goal> goals){
		this.goals.addAll(goals);
		this.name = name;
		this.first = first;
		this.second = second;
		setSize();
		id = count;
		count++;
		allFields.add(this);
	}
	
	public GameField(String name, Location first, Location second) {
		this.name = name;
		this.first = first.clone();
		this.second = second.clone();
		
		setSize();
		id = count;
		count++;
		allFields.add(this);
	}
	
	public static ArrayList<GameField> getAllFields(){
		return allFields;
	}
	
	/**
	 * Adds an unassigned goal to this field.
	 * @param goal
	 */
	public void addGoal(Goal goal){
		goals.add(goal);
	}
	public void removeGoal(Goal goal){
		goals.remove(goal);
	}
	
	private void setSize(){
		int x1 = first.getBlockX();
		int x2 = second.getBlockX();
		int z1 = first.getBlockZ();
		int z2 = second.getBlockZ();
		if (x1 > x2) {
			xLargest = x1;
			xSmallest = x2;
		} else if (x2 > x1) {
			xSmallest = x1;
			xLargest = x2;
		} else {
			//X coords are the same
			xSmallest = x1;
			xLargest = x2;
			//We should perhaps make sure that this does not count as a valid field.
		}
		if (z1 > z2) {
			zLargest = z1;
			zSmallest = z2;
		} else if (z2 > z1) {
			zSmallest = z1;
			zLargest = z2;
		} else {
			//Z coords are the same
			zSmallest = z1;
			zLargest = z2;
			//We should perhaps make sure that this does not count as a valid field.
		}
	}
	public int getLargest(char coord){
		if (coord == 'x') return xLargest;
		else if (coord == 'z') return zLargest;
		return 0; //When someone calls this method with an unknown coord
	}
	public int getSmallest(char coord){
		if (coord == 'x') return xSmallest;
		else if (coord == 'z') return zSmallest;
		return 0;
	}
	public boolean isInField(Location loc){
		double x = loc.getX();
		double z = loc.getZ();
		if (x < xLargest && x > xSmallest && z < zLargest && z > zSmallest){
			return true;
		}
		else return false;
	}
	/**
	 * @return All the goals in this field.
	 */
	public ArrayList<Goal> getGoals(){
		return goals;
	}
}
