package com.aurean.mcsports.basketball;

import org.bukkit.Color;
import org.bukkit.entity.Player;

import com.aurean.mcsports.MCSPlayer;
import com.aurean.mcsports.objects.Ball;
import com.aurean.mcsports.objects.Goal;
import com.aurean.mcsports.objects.Team;

public class BasketBallTeam extends Team {

	public BasketBallTeam(String name, Color color, Goal goal1, Goal goal2) {
		super(name, color, goal1, goal2);
	}
	
	public BasketBallTeam(String name, int red, int green, int blue, Goal goal1, Goal goal2) {
		super(name, red, green, blue, goal1, goal2);
	}

	@Override
	public boolean addPlayer(Player player){
		if (players.size()<11) return players.add(player); //Always true
		return false; //If there are already 11 players, return false.
	}

	/** 
	 * Give the ball to this team and to the given player.<br>
	 * Should be called when:<br>
	 * 1) This player steals the ball from the other team.<br>
	 * 2) This player gets a pass from someone else.
	 */
	@Override
	public void giveBall(Ball ball, Player player) {
		//TODO add further implementation.
		MCSPlayer.getMCSPlayer(player).giveBall(ball);
		hasBall = true;
	}
	
	@Override
	public void passBall(Ball ball, Player from, Player to){
		MCSPlayer.getMCSPlayer(from).removeBall();
		MCSPlayer.getMCSPlayer(to).giveBall(ball);
	}

	@Override
	public void removeBall() {
		//TODO add further implementation.
		hasBall = false;
	}
	
	/**
	 * Remove the ball from this team and the given player.<br>
	 * Should be called when the other team steals the ball.
	 */
	public void removeBall(Ball ball, Player player) {
		//TODO add further implementation.
		MCSPlayer.getMCSPlayer(player).removeBall();
		hasBall = false;
	}
}
