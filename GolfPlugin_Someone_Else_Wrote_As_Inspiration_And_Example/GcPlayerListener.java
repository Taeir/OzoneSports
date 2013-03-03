/*    */ 
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.EventPriority;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.bukkit.event.block.Action;
/*    */ import org.bukkit.event.player.PlayerInteractEvent;
/*    */ import org.bukkit.event.player.PlayerMoveEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class GcPlayerListener
/*    */   implements Listener
/*    */ {
/*    */   Golfcraft plugin;
/*    */ 
/*    */   GcPlayerListener(Golfcraft plugin)
/*    */   {
/* 23 */     this.plugin = plugin;
/*    */   }
/*    */ 
/*    */   public void onPlayerMove(PlayerMoveEvent event)
/*    */   {
/* 28 */     onPlayerMove_Func(event);
/*    */   }
/*    */ 
/*    */   public void onPlayerInteract(PlayerInteractEvent event)
/*    */   {
/* 33 */     onPlayerInteract_Func(event);
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.HIGH)
/*    */   private void onPlayerInteract_Func(PlayerInteractEvent event)
/*    */   {
/* 40 */     Player player = event.getPlayer();
/* 41 */     if ((this.plugin.getConfig().getBoolean("enable-clubs")) && 
/* 42 */       (GcCommands.Golfing.containsKey(player)) && 
/* 43 */       (((Boolean)GcCommands.Golfing.get(player)).booleanValue()) && 
/* 44 */       (event.getAction() == Action.LEFT_CLICK_AIR) && (player.getItemInHand().getType() == Material.BOW)) {
/* 45 */       if (GcClubs.Club.containsKey(player)) {
/* 46 */         if (((Integer)GcClubs.Club.get(player)).intValue() < 11) {
/* 47 */           GcClubs.Club.put(player, Integer.valueOf(((Integer)GcClubs.Club.get(player)).intValue() + 1));
/*    */         }
/*    */         else {
/* 50 */           GcClubs.Club.put(player, Integer.valueOf(0));
/*    */         }
/*    */       }
/*    */       else {
/* 54 */         GcClubs.Club.put(player, Integer.valueOf(0));
/*    */       }
/* 56 */       player.sendMessage(GcClubs.getClubMessage(player));
/*    */     }
/*    */   }
/*    */ 
/*    */   @EventHandler(priority=EventPriority.HIGH)
/*    */   private void onPlayerMove_Func(PlayerMoveEvent event)
/*    */   {
/* 66 */     Player player = event.getPlayer();
/* 67 */     Location playerLocation = null;
/* 68 */     Location signLocation = null;
/*    */ 
/* 70 */     if ((GcEntityListener.signExists.containsKey(player)) && 
/* 71 */       (!((Boolean)GcEntityListener.signExists.get(player)).booleanValue())) {
/* 72 */       playerLocation = player.getLocation();
/*    */     }
/*    */ 
/* 76 */     if (playerLocation != null) {
/* 77 */       signLocation = (Location)GcBlockListener.signLocation.get(player);
/* 78 */       if ((signLocation != null) && 
/* 79 */         (playerLocation.distance(signLocation) > 2.0D)) {
/* 80 */         player.teleport(signLocation);
/* 81 */         player.sendMessage(ChatColor.RED + "You cannot move until your launched ball has landed.");
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcPlayerListener
 * JD-Core Version:    0.6.2
 */