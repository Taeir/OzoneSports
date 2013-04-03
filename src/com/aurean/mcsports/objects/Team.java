package com.aurean.mcsports.objects;

import org.bukkit.Color;
import java.util.ArrayList;

import org.bukkit.entity.Player;

public abstract class Team {
	protected String name;
	protected Color color;
	protected ArrayList<Player> players = new ArrayList<Player>();
	protected Goal goal1, goal2;
	protected boolean hasBall = false;
	public Team(String name, Color color, Goal goal1, Goal goal2){
		this.color = color;
		this.name = name;
		this.goal1 = goal1;
		this.goal2 = goal2;
	}
	
	public Team(String name, int red, int green, int blue, Goal goal1, Goal goal2){
		this.color = Color.fromRGB(red, green, blue);
		this.name = name;
		this.goal1 = goal1;
		this.goal2 = goal2;
	}
	
	/**
	 * @return The color this team has.
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * @return The name of this team.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return All the players in this team.
	 */
	public ArrayList<Player> getPlayers(){
		return players;
	}
	
	/**
	 * Add a player to this team.<br>
	 * <b>Remember</b>: also add this player to the game!
	 * @return True if the team is not full.
	 */
	public boolean addPlayer(Player player){
		return players.add(player);
	}
	
	/**
	 * Remove a player from this team.
	 * @param player The player to remove.
	 * @return If the removal succeeded:<br>- <b>True</b> if the player was on this team.<br>- <b>False</b> if the player was not.
	 */
	public boolean removePlayer(Player player){
		return players.remove(player);
	}
	
	public Goal[] getGoals(){
		return new Goal[] { goal1, goal2 };
	}
	
	/**
	 * @return If this team currently has the ball.
	 */
	public boolean hasBall(){
		return hasBall;
	}
	
	/**
	 * Give the ball to this team.
	 * @param ball
	 */
	public abstract void giveBall(Ball ball, Player player);
	/**
	 * Remove the ball from this team.<br>
	 * Should be called when the game ends.
	 */
	public abstract void removeBall();

	/**
	 * Should be called when a ball is passed <b>within</b> a team.
	 */
	public abstract void passBall(Ball ball, Player from, Player to);

}
