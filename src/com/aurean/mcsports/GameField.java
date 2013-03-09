package com.aurean.mcsports;

import java.util.ArrayList;

import org.bukkit.Location;

public class GameField {
	public Location first, second;
	String name;
	int id = 0;
	static int count;
	public double xLargest, xSmallest, zLargest, zSmallest;
	private static ArrayList<GameField> allFields = new ArrayList<GameField>();
	
	public GameField(String name, Location first, Location second) {
		this.name = name;
		this.first = first;
		this.second = second;
		setSize();
		id = count;
		count++;
		allFields.add(this);
	}
	
	public static ArrayList<GameField> getAllFields(){
		return allFields;
	}
	
	private void setSize(){
		double x1 = first.getX();
		double x2 = second.getX();
		double z1 = first.getZ();
		double z2 = second.getZ();
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
	double getLargest(String coord){
		if (coord.equalsIgnoreCase("x")) return xLargest;
		else if (coord.equalsIgnoreCase("z")) return zLargest;
		return 0; //When someone calls this method with an unknown coord
	}
	double getSmallest(String coord){
		if (coord.equalsIgnoreCase("x")) return xSmallest;
		else if (coord.equalsIgnoreCase("z")) return zSmallest;
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
}
