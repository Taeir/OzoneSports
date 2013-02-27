package com.aurean.ozonesports;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class Game {
	public static enum gameType{
		Football, Tennis, Croquet, Basketball, Golf, Nothing, NoGame
	};
	private gameType GameType;
	private boolean running;
	private Boolean joinable;
	private ArrayList<Player> players = new ArrayList<Player>();
	private static ArrayList<Game> runningGames = new ArrayList<Game>();
	
	//Test for commit
	
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
	
	public Boolean getSetJoinable(){ //Im not sure if this works though
		return joinable; // This is a reference: if you change this output directly, it will also change here.
	}
	public boolean getJoinable(){
		return joinable;
	}
	public static boolean getJoinable(Game game){
		return game.getJoinable();
	}
	public void setJoinable(boolean bool){
		joinable = bool;
	}
	public static void setJoinable(Game game, boolean bool){
		game.setJoinable(bool);
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
