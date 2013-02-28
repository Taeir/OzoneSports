package com.aurean.ozonesports;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class OSPlayer {
	private Player player;
	private Game currentGame;
	private Game refereeFor;
	private int score = 0;
	private static ArrayList<OSPlayer> allOSPlayers = new ArrayList<OSPlayer>();
	
	public OSPlayer(Player player){
		this.player = player;
		allOSPlayers.add(this);
	}
	
	public static void disablePlugin(){
		allOSPlayers = null;
		//In the future, add save feature
	}
	public static OSPlayer getOSPlayer(Player player){
		for (OSPlayer current: allOSPlayers){
			if (current.getPlayer().equals(player))
				return current;
		}
		return new OSPlayer(player);
	}
	
	
	public boolean isInGame(){
		if (currentGame == null) return false;
		else return currentGame.isPlayerInGame(player);
	}
	public static boolean isInGame(Player player){
		return getOSPlayer(player).isInGame();
	}

	public Player getPlayer(){
		return player;
	}
	
	public int getScore(){
		return score;
	}
	public static int getScore(Player player){
		return getOSPlayer(player).getScore();
	}
	public void setScore(int newscore){
		score = newscore;
	}
	public static void setScore(int newscore, Player player){
		getOSPlayer(player).setScore(newscore);
	}
	public void addToScore(int value){
		score = score+value;
	}
	public static void addToScore(int value, Player player){
		getOSPlayer(player).addToScore(value);
	}
	
	public Game getCurrentGame(){
		return currentGame;
	}
	public static Game getCurrentGame(Player player){
		return getOSPlayer(player).getCurrentGame();
	}
	void setCurrentGame(Game game){
		currentGame = game;
	}
	static void setCurrentGame(Game game, Player player){
		//Game.removePlayer(player, currentGame);
		getOSPlayer(player).setCurrentGame(game);
	}
	
	public boolean isReferee(){
		if (refereeFor == null) return false;
		else return true;
	}
	public static boolean isReferee(Player player){
		return getOSPlayer(player).isReferee();
	}
	public Game getRefereeFor(){
		return refereeFor;
	}
	public static Game getRefereeFor(Player player){
		return getOSPlayer(player).getRefereeFor();
	}
	public void setAsRefereeFor(Game game){
		refereeFor = game;
	}
	public static void setAsRefereeFor(Game game, Player player){
		getOSPlayer(player).setAsRefereeFor(game);
	}
}
