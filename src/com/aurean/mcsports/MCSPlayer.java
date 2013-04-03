package com.aurean.mcsports;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;

import com.aurean.mcsports.objects.Ball;
import com.aurean.mcsports.objects.Team;

public class MCSPlayer {
	private Player player;
	private Game currentGame;
	private Game refereeFor;
	private int score = 0;
	private Team team;
	private Ball ball;
	
	private static ArrayList<MCSPlayer> allOSPlayers = new ArrayList<MCSPlayer>();
	public static void disablePlugin(){
		allOSPlayers = null;
		//In the future, add save feature
	}
	
	public MCSPlayer(Player player){
		this.player = player;
		allOSPlayers.add(this);
	}
	
	public static MCSPlayer getMCSPlayer(Player player){
		for (MCSPlayer current: allOSPlayers){
			if (current.getPlayer().equals(player))
				return current;
		}
		return new MCSPlayer(player);
	}
	
	/**
	 * @return The player
	 */
	public Player getPlayer(){
		return player;
	}
	/**
	 * <i>Convenience method</i>
	 * @return The players name
	 */
	public String getName(){
		return player.getName();
	}
	/**
	 * <i>Convenience method</i>
	 * @return The players inventory.
	 */
	public PlayerInventory getInventory(){
		return player.getInventory();
	}
	
	/**
	 * @return If the player is currently in a game.
	 */
	public boolean isInGame(){
		if (currentGame != null) return currentGame.isPlayerInGame(player);
		return false;
	}
	/**
	 * @return The players current game, or null if he has none.
	 */
	public Game getGame(){
		return currentGame;
	}
	/**
	 * Set the players current game.<br>
	 * <b>Should not be called manually.</b> If the player is added to a game with  Game.addPlayer, he will also be added here.
	 * {@link #com.aurean.mcsports.Game.addPlayer(Player player)}
	 * @param game
	 */
	public void setGame(Game game){
		currentGame = game;
	}

	/**
	 * @return If the player is in a team.
	 */
	public boolean isInTeam(){
		return team != null; // True if team != null, false otherwise.
	}
	/**
	 * @return The team the player is in currently, or null if the player has no team.
	 */
	public Team getTeam(){
		return team;
	}
	/**
	 * Set the players current team.<br><br>
	 * To remove the current team, use <code>setTeam(null);</code>
	 * @param team
	 */
	public void setTeam(Team team){
		this.team = team;
	}
	
	public boolean hasBall(){
		return ball != null;
	}
	/**
	 * @return The ball the player currently has, or null if he has none.
	 */
	public Ball getBall(){
		return ball;
	}
	/**
	 * Give the given ball to this player.
	 * @param ball
	 */
	public void giveBall(Ball ball){
		this.ball = ball;
	}
	/**
	 * Remove the ball from this player.
	 */
	public void removeBall(){
		this.ball = null;
	}
	
	public static boolean isInGame(Player player){
		return getMCSPlayer(player).isInGame();
	}
	
	
	public int getScore(){
		return score;
	}
	public static int getScore(Player player){
		return getMCSPlayer(player).getScore();
	}
	public void setScore(int newscore){
		score = newscore;
	}
	public static void setScore(int newscore, Player player){
		getMCSPlayer(player).setScore(newscore);
	}
	public void addToScore(int value){
		score = score+value;
	}
	public static void addToScore(int value, Player player){
		getMCSPlayer(player).addToScore(value);
	}
	
	public static Game getCurrentGame(Player player){
		return getMCSPlayer(player).getGame();
	}
	static void setCurrentGame(Game game, Player player){
		//Game.removePlayer(player, currentGame);
		getMCSPlayer(player).setGame(game);
	}
	
	public boolean isReferee(){
		if (refereeFor == null) return false;
		else return true;
	}
	public static boolean isReferee(Player player){
		return getMCSPlayer(player).isReferee();
	}
	public Game getRefereeFor(){
		return refereeFor;
	}
	public static Game getRefereeFor(Player player){
		return getMCSPlayer(player).getRefereeFor();
	}
	public void setAsRefereeFor(Game game){
		refereeFor = game;
	}
	public static void setAsRefereeFor(Game game, Player player){
		getMCSPlayer(player).setAsRefereeFor(game);
	}
}
