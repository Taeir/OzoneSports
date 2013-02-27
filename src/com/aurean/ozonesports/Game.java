package com.aurean.ozonesports;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Game {
	public static enum gameType{
		Football, Tennis, Croquet, Basketball, Golf, Nothing, NoGame
	};
	private gameType GameType;
	private boolean running;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Game> runningGames = new ArrayList<Game>();
	
	
	public gameType getType(){
		return GameType;
	}
	public ArrayList<Player> getPlayers(){
		return players;
	}
	public static ArrayList<Player> getPlayers(Game game){
		return game.getPlayers();
	}
	public Player[] getPlayerArray(){
		return (Player[]) players.toArray();
	}
	public static Player[] getPlayerArray(Game game){
		return game.getPlayerArray();
	}
	
	public boolean isPlayerInGame(Player player){
		if (players.contains(player))
			return true;
		return false;
	}
	public static boolean isPlayerInGame(Player player, Game game){
		if (game == null)
			return false;
		return game.isPlayerInGame(player);
	}
	
	
	public boolean isGameRunning(){
		return running;
	}
	public static boolean isGameRunning(Game game){
		return game.isGameRunning();
	}
	
	
	//isgamerunning
	public void removePlayer(Player player){
		players.remove(player);
	}
	public static void removePlayer(Player player, Game game){
		game.removePlayer(player);
	}
	public void addPlayer(Player player){
		players.add(player);
	}
	public static void addPlayer(Player player, Game game){
		game.addPlayer(player);
	}
	
	
	
	public static ArrayList<Game> getRunningGames(){
		return runningGames;
	}
	public static Game getGameByType(gameType type){
		for (Game current : runningGames){
			if (current.getType() == type)
				return current;
		}
		return null;
	}
	/*
	public static boolean removePlayer(Player player, gameType game){
		if (getGameByType(game) == null)
			return false;
		getGameByType(game).removePlayer(player);
		return true;
	}*/
}
