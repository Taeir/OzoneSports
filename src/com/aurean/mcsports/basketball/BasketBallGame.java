package com.aurean.mcsports.basketball;

import org.bukkit.entity.Player;

import com.aurean.mcsports.Game;
import com.aurean.mcsports.objects.Ball;
import com.aurean.mcsports.objects.GameField;
import com.aurean.mcsports.objects.Team;

public class BasketBallGame extends Game {
	private Ball ball;
	public BasketBallGame(GameField field, Ball ball) {
		super(gameType.Basketball, field);
		this.ball = ball;
	}
	
	public BasketBallGame(GameField field) {
		super(gameType.Basketball, field);
		//FIXME this.ball = new Ball();
	}

	@Override
	public boolean addPlayer(Player player, Team team) {
		if (team.addPlayer(player)){ //If there is room then add the player.
			addPlayer(player, this); //Add the player to the game
			return true;
		}
		return false;
	}
	
	public Ball getBall(){
		return ball;
	}

}
