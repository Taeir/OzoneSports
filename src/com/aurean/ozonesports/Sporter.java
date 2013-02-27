package com.aurean.ozonesports;

import org.bukkit.entity.Player;

import com.aurean.ozonesports.Game.gameType;

public class Sporter {
	private Player player;
	private Game currentGame;
	private int score;
	
	public Sporter(Player player){
		this.player = player;
		this.currentGame = null;
		this.score = 0;
	}
	
	public static gameType getGameByString(String name){
		name = name.toLowerCase();
		if (name.equals("football"))
			return gameType.Football;
		else if (name.equals("tennis"))
			return gameType.Tennis;
		else if (name.equals("croquet"))
			return gameType.Croquet;
		else if (name.equals("basketball"))
			return gameType.Basketball;
		else if (name.equals("gold"))
			return gameType.Golf;
		else
			return gameType.Nothing;
	}
	
	public boolean isInGame(){
		if (currentGame == null)
			return false;
		return currentGame.isPlayerInGame(player);
	}
	/*
	public gameType getCurrentGameType(){
		if (currentGame == null)
			return gameType.NoGame;
		return currentGame.getType();
	}*/
	public Player getPlayer(){
		return player;
	}
	public int getScore(){
		return score;
	}
	public void leaveGame(){
		Game.removePlayer(player, currentGame);
		currentGame = null;
	}
	public void joinGame(Game game){
		currentGame = game;
	}
}
