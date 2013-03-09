package com.aurean.mcsports;

import org.bukkit.Material;

public class Settings {
	public static Material EntityBall = Material.SLIME_BALL;
	public static int BlockBallId = 19;
	public static byte BlockBallData = 0;
	
	public static String GoalMessage = "Goal! For &team!";
	public static String LostMessage = "You lost with &scorelose-&scorewin!";
	public static String WinMessage = "You won with &scorewin-&scorelose!";
	//TODO make sure the settings get updated from the config files.
}
