

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class GcClubs
{
	public static HashMap<Player, Integer> Club = new HashMap<Player, Integer>();//Better to make maps with player names. Saves memory, faster .get and .put and often also easier.
	public static HashMap<Player, Float> Force = new HashMap<Player, Float>();

	public static String getClubMessage(Player player) {
		int club = Club.get(player).intValue();
		String message = "Unknown club";
		if (club == 0) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Driver (1 wood)" + ChatColor.GRAY + "]";
		}
		else if (club == 1) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "3 wood" + ChatColor.GRAY + "]";
		}
		else if (club == 2) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "5 wood" + ChatColor.GRAY + "]";
		}
		else if (club == 3) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "4 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 4) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "5 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 5) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "6 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 6) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "7 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 7) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "8 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 8) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "9 iron" + ChatColor.GRAY + "]";
		}
		else if (club == 9) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Pitching Wedge" + ChatColor.GRAY + "]";
		}
		else if (club == 10) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Sand Wedge" + ChatColor.GRAY + "]";
		}
		else if (club == 11) {
			message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Putter" + ChatColor.GRAY + "]";
		}
		return message;
	}

	public static String getClubMessage2(Player player) {
		int club = Club.get(player).intValue();
		String message = "Unknown club";
		if (club == 0) {
			message = ChatColor.GRAY + "Driver (1 wood)";
		}
		else if (club == 1) {
			message = ChatColor.GRAY + "3 wood";
		}
		else if (club == 2) {
			message = ChatColor.GRAY + "5 wood";
		}
		else if (club == 3) {
			message = ChatColor.GRAY + "4 iron";
		}
		else if (club == 4) {
			message = ChatColor.GRAY + "5 iron";
		}
		else if (club == 5) {
			message = ChatColor.GRAY + "6 iron";
		}
		else if (club == 6) {
			message = ChatColor.GRAY + "7 iron";
		}
		else if (club == 7) {
			message = ChatColor.GRAY + "8 iron";
		}
		else if (club == 8) {
			message = ChatColor.GRAY + "9 iron";
		}
		else if (club == 9) {
			message = ChatColor.GRAY + "Pitching Wedge";
		}
		else if (club == 10) {
			message = ChatColor.GRAY + "Sand Wedge";
		}
		else if (club == 11) {
			message = ChatColor.GRAY + "Putter";
		}
		return message;
	}

	public static Double getClubSpeed(Player player) {
		int club = Club.get(player).intValue();
		if (club == 0) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
				return Double.valueOf(0.9D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.35D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
				return Double.valueOf(1.63D);
			}
			return Double.valueOf(1.25D);
		}
		if (club == 1) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
				return Double.valueOf(0.86D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.35D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
				return Double.valueOf(1.49D);
			}
			return Double.valueOf(1.2D);
		}
		if (club == 2) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.35D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
				return Double.valueOf(1.35D);
			}
			return Double.valueOf(1.15D);
		}
		if (club == 3) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(1.24D);
		}
		if (club == 4) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(1.18D);
		}
		if (club == 5) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(1.12D);
		}
		if (club == 6) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(1.06D);
		}
		if (club == 7) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(1.0D);
		}
		if (club == 8) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(0.9300000000000001D);
		}
		if (club == 9) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.6D);
			}
			return Double.valueOf(0.85D);
		}
		if (club == 10) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.7D);
			}
			return Double.valueOf(0.6D);
		}
		if (club == 11) {
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
				return Double.valueOf(0.3D);
			}
			if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
				return Double.valueOf(0.2D);
			}
			return Double.valueOf(0.45D);
		}
		return Double.valueOf(1.0D);
	}

	public static Float getClubPitch(Player player) {
		int club = Club.get(player).intValue();
		float force = Force.get(player).floatValue();
		if (club == 0) {
			return Float.valueOf((float)(1.6D * force));
		}
		if (club == 1) {
			return Float.valueOf((float)(1.75D * force));
		}
		if (club == 2) {
			return Float.valueOf(2.0F * force);
		}
		if (club == 3) {
			return Float.valueOf((float)(2.1D * force));
		}
		if (club == 4) {
			return Float.valueOf((float)(2.13D * force));
		}
		if (club == 5) {
			return Float.valueOf((float)(2.16D * force));
		}
		if (club == 6) {
			return Float.valueOf((float)(2.2D * force));
		}
		if (club == 7) {
			return Float.valueOf((float)(2.23D * force));
		}
		if (club == 8) {
			return Float.valueOf((float)(2.26D * force));
		}
		if (club == 9) {
			return Float.valueOf((float)(2.44D * force));
		}
		if (club == 10) {
			return Float.valueOf((float)(2.44D * force));
		}
		if (club == 11) {
			return Float.valueOf((float)(2.5D * force));
		}
		return Float.valueOf((float)(1.57D * force));
	}

	public static String getEfficiencyMessage(Player player) {
		int club = Club.get(player).intValue();
		String message = "";
		Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
		if (club == 0) {
			if (block.getType() == Material.GRASS) {
				message = ChatColor.GRAY + "Your driver was less efficient in the rough.";
			}
			if (block.getType() == Material.SAND) {
				message = ChatColor.GRAY + "Your driver was almost useless in the sand.";
			}
			if ((block.getType() == Material.WOOL) && (block.getData() == 13)) {
				message = ChatColor.GRAY + "Your driver lost momentum on the fairway.";
			}
		}
		if ((club >= 1) && (club <= 9) && 
				(block.getType() == Material.SAND)) {
			message = ChatColor.GRAY + "Your " + getClubMessage2(player).toLowerCase() + " was less efficient in the sand.";
		}

		if ((club == 10) && 
				(block.getType() == Material.SAND)) {
			message = ChatColor.GRAY + "Your " + getClubMessage2(player).toLowerCase() + " was more efficient in the sand!";
		}

		if (club == 11) {
			if (block.getType() == Material.GRASS) {
				message = ChatColor.GRAY + "Your putter was almost useless in the grass.";
			}
			if (block.getType() == Material.SAND) {
				message = ChatColor.GRAY + "Your driver was useless in the sand.";
			}
		}
		return message;
	}
}

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcClubs
 * JD-Core Version:    0.6.2
 */