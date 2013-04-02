import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class GcPlayerListener
implements Listener
{
	Golfcraft plugin;

	GcPlayerListener(Golfcraft plugin)
	{
		this.plugin = plugin;
	}

	public void onPlayerMove(PlayerMoveEvent event)
	{
		onPlayerMove_Func(event);
	}

	public void onPlayerInteract(PlayerInteractEvent event)
	{
		onPlayerInteract_Func(event);
	}

	@EventHandler(priority=EventPriority.HIGH)
	private void onPlayerInteract_Func(PlayerInteractEvent event)
	{
		Player player = event.getPlayer();
		if ((this.plugin.getConfig().getBoolean("enable-clubs")) && 
				(GcCommands.Golfing.containsKey(player)) && 
				(GcCommands.Golfing.get(player).booleanValue()) && 
				(event.getAction() == Action.LEFT_CLICK_AIR) && (player.getItemInHand().getType() == Material.BOW)) {
			if (GcClubs.Club.containsKey(player)) {
				if (GcClubs.Club.get(player).intValue() < 11) {
					GcClubs.Club.put(player, Integer.valueOf(GcClubs.Club.get(player).intValue() + 1));
				}
				else {
					GcClubs.Club.put(player, Integer.valueOf(0));
				}
			}
			else {
				GcClubs.Club.put(player, Integer.valueOf(0));
			}
			player.sendMessage(GcClubs.getClubMessage(player));
		}
	}

	@EventHandler(priority=EventPriority.HIGH)
	private void onPlayerMove_Func(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		Location playerLocation = null;
		Location signLocation = null;

		if ((GcEntityListener.signExists.containsKey(player)) && 
				(!GcEntityListener.signExists.get(player).booleanValue())) {
			playerLocation = player.getLocation();
		}

		if (playerLocation != null) {
			signLocation = GcBlockListener.signLocation.get(player);
			if ((signLocation != null) && 
					(playerLocation.distance(signLocation) > 2.0D)) {
				player.teleport(signLocation);
				player.sendMessage(ChatColor.RED + "You cannot move until your launched ball has landed.");
			}
		}
	}
}

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcPlayerListener
 * JD-Core Version:    0.6.2
 */