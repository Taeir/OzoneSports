package com.aurean.mcsports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bukkit.entity.Player;

import com.aurean.mcsports.objects.GameField;
import com.aurean.mcsports.objects.Goal;
import com.aurean.mcsports.objects.Team;

public abstract class Game {
	@Override
	public String toString() {
		return "Game [GameType=" + GameType + ", running=" + running
				+ ", joinable=" + joinable + ", field=" + field + ", players="
				+ players.toString() + ", teams=" + teams.toString() + ", referees=" + referees.toString()
				+ "]";
	}
	public static enum gameType{
		Football, Tennis, Croquet, Basketball, Golf, Nothing, NoGame
	};
	private gameType GameType;
	private boolean running = false;
	private boolean joinable = false;
	private GameField field;
	private ArrayList<Player> players = new ArrayList<Player>();
	protected ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<Player> referees = new ArrayList<Player>();
	private static ArrayList<Game> Games = new ArrayList<Game>();
	//private Map<Goal, Integer> points = new HashMap<Goal, Integer>();
	
	/*public Game(gameType type){
		this.GameType = type;
		this.field = null;
		Games.add(this);
	}*/
	public Game(gameType type, GameField field){
		this.GameType = type;
		this.field = field;
		//Goal g;
		//for (Iterator<Goal> localIterator = field.getGoals().iterator(); localIterator.hasNext(); points.put(g, Integer.valueOf(0))){
		//	g = localIterator.next();
		//}
		Games.add(this);
	}
	
	//public void addPoint(Goal g){
	//	points.put(g, Integer.valueOf(points.get(g).intValue() + 1));
	//	String msg = Settings.GoalMessage.replace("&team", g.getTeam().getName());
	//	sendMessage(msg);
		//for (Player current : players){
		//	current.sendMessage(msg);
		//}
	//}
	
	public static gameType getGameTypeByString(String name){
		name = name.toLowerCase();
		if (name.equals("football"))
			return gameType.Football;
		else if (name.equals("tennis"))
			return gameType.Tennis;
		else if (name.equals("croquet"))
			return gameType.Croquet;
		else if (name.equals("basketball"))
			return gameType.Basketball;
		else if (name.equals("golf"))
			return gameType.Golf;
		else
			return gameType.Nothing;
	}
	public gameType getType(){
		return GameType;
	}
	public String getName(){
		return GameType.toString();
	}
	public GameField getField(){
		return field;
	}
	public GameField getField(Game game){
		return game.getField();
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
	public static boolean isPlayerInAnyGame(Player player){
		for (Game current : getGames()){
			if (current.isPlayerInGame(player)) return true;
		}
		return false;
	}
	
	public boolean isRunning(){
		return running;
	}
	public static boolean isRunning(Game game){
		return game.isRunning();
	}
	
	public void removePlayer(Player player){
		players.remove(player);
		MCSPlayer.setCurrentGame(null, player);
	}
	public static void removePlayer(Player player, Game game){
		game.removePlayer(player);
	}
	/**
	 * Add a player to this game, and set his currentgame to this.
	 * @param player
	 */
	public void addPlayer(Player player){
		players.add(player);
		MCSPlayer.setCurrentGame(this, player);
	}
	public static void addPlayer(Player player, Game game){
		game.addPlayer(player);
	}
	
	public abstract boolean addPlayer(Player player, Team team);
	
	/**
	 * Sends a message to the players of all teams in this game.
	 * @param message
	 * @see Team#sendMessage(String) Team.sendMessage
	 */
	public void sendMessage(String message){
		for (Team current : teams){
			current.sendMessage(message);
		}
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
	
	public ArrayList<Player> getReferees(){
		return referees;
	}
	public static ArrayList<Player> getReferees(Game game){
		return game.getReferees();
	}
	public int getRefereeCount(){
		return referees.size();
	}
	public static int getRefereeCount(Game game){
		return game.getRefereeCount();
	}
	
	public void addReferee(Player player){
		referees.add(player);
		MCSPlayer.setAsRefereeFor(this, player);
	}
	public static void addReferee(Player player, Game game){
		game.addReferee(player);
	}
	public void removeReferee(Player player){
		referees.remove(player);
		MCSPlayer.setAsRefereeFor(null, player);
	}
	public static void removeReferee(Player player, Game game){
		game.removeReferee(player);
	}
	
	public static ArrayList<Game> getGames(){
		return Games;
	}
	public static ArrayList<Game> getRunningGames(){
		ArrayList<Game> runningGames = new ArrayList<Game>();
		for (Game current : Games){
			if (current.isRunning()) runningGames.add(current);
		}
		return runningGames;
	}
	public static Game getGame(gameType type){
		for (Game current : Games){
			if (current.getType() == type)
				return current;
		}
		return null;
	}
}
