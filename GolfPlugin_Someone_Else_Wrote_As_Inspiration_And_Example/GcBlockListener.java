/*     */ 
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ //import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockDamageEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ //import org.bukkit.inventory.PlayerInventory;
/*     */ 
/*     */ public class GcBlockListener
/*     */   implements Listener
/*     */ {
/*  24 */   @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<Player, Location> signLocation = new HashMap();
/*     */ 
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onBlockDamage(BlockDamageEvent event)
/*     */   {
/*  29 */     onBlockDamage_Func(event);
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onBlockBreak(BlockBreakEvent event)
/*     */   {
/*  35 */     onBlockBreak_Func(event);
/*     */   }
/*     */ 
/*     */   private void onBlockBreak_Func(BlockBreakEvent event)
/*     */   {
/*  45 */     Block block = event.getBlock();
/*  46 */     Player player = event.getPlayer();
/*     */ 
/*  48 */     if ((block.getState() instanceof Sign)) {
/*  49 */       Sign sign = (Sign)block.getState();
/*  50 */       String[] lines = sign.getLines();
/*     */ 
/*  52 */       if (lines[0].equals(ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]"))
/*  53 */         if (lines[1].equals(ChatColor.DARK_GRAY + player.getName())) {
/*  54 */           if ((GcCommands.Golfing.containsKey(player)) && 
/*  55 */             (((Boolean)GcCommands.Golfing.get(player)).booleanValue())) {
/*  56 */             while (player.getInventory().contains(Material.ARROW)) {
/*  57 */               player.getInventory().remove(Material.ARROW);
/*     */             }
/*  59 */             ItemStack arrow = new ItemStack(Material.ARROW);
/*  60 */             arrow.setAmount(1);
/*  61 */             player.getInventory().addItem(new ItemStack[] { arrow });
/*  62 */             while (player.getInventory().contains(Material.BOW)) {
/*  63 */               player.getInventory().remove(Material.BOW);
/*     */             }
/*  65 */             ItemStack bow = new ItemStack(Material.BOW);
/*  66 */             arrow.setAmount(1);
/*  67 */             player.getInventory().addItem(new ItemStack[] { bow });
/*  68 */             GcEntityListener.signExists.put(player, Boolean.valueOf(false));
/*  69 */             signLocation.put(player, block.getLocation());
/*  70 */             block.setType(Material.AIR);
/*  71 */             player.sendMessage(ChatColor.GRAY + "You may now continue golfing.");
/*     */           }
/*     */ 
/*     */         }
/*  75 */         else if (lines[1] != "Start:")
/*  76 */           if ((player.hasPermission("golf.ref")) || (player.isOp())) {
/*  77 */             String signPlayer = ChatColor.stripColor(lines[1]);
/*  78 */             player.sendMessage(ChatColor.GRAY + "You have removed " + ChatColor.GREEN + signPlayer + ChatColor.GRAY + "'" + ChatColor.GREEN + "s " + ChatColor.GRAY + "sign.");
/*     */ 
/*  80 */             Player[] list = Bukkit.getOnlinePlayers();
/*  81 */             for (Player players : list)
/*  82 */               if (players.getName().equals(signPlayer)) {
/*  83 */                 GcEntityListener.signExists.put(players, Boolean.valueOf(false));
/*  84 */                 block.setType(Material.AIR);
/*     */               }
/*     */           }
/*     */           else
/*     */           {
/*  89 */             event.setCancelled(true);
/*  90 */             block.getState().update(true);
/*     */           }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onBlockDamage_Func(BlockDamageEvent event)
/*     */   {
/* 104 */     Block block = event.getBlock();
/* 105 */     Player player = event.getPlayer();
/*     */ 
/* 107 */     if ((block.getState() instanceof Sign)) {
/* 108 */       Sign sign = (Sign)block.getState();
/* 109 */       String[] lines = sign.getLines();
/*     */ 
/* 111 */       if (lines[0].equals(ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]"))
/* 112 */         if (lines[1].equals(ChatColor.DARK_GRAY + player.getName())) {
/* 113 */           if ((GcCommands.Golfing.containsKey(player)) && 
/* 114 */             (((Boolean)GcCommands.Golfing.get(player)).booleanValue())) {
/* 115 */             while (player.getInventory().contains(Material.ARROW)) {
/* 116 */               player.getInventory().remove(Material.ARROW);
/*     */             }
/* 118 */             ItemStack arrow = new ItemStack(Material.ARROW);
/* 119 */             arrow.setAmount(1);
/* 120 */             player.getInventory().addItem(new ItemStack[] { arrow });
/* 121 */             while (player.getInventory().contains(Material.BOW)) {
/* 122 */               player.getInventory().remove(Material.BOW);
/*     */             }
/* 124 */             ItemStack bow = new ItemStack(Material.BOW);
/* 125 */             arrow.setAmount(1);
/* 126 */             player.getInventory().addItem(new ItemStack[] { bow });
/* 127 */             GcEntityListener.signExists.put(player, Boolean.valueOf(false));
/* 128 */             signLocation.put(player, block.getLocation());
/* 129 */             block.setType(Material.AIR);
/* 130 */             player.sendMessage(ChatColor.GRAY + "You may now continue golfing.");
/*     */           }
/*     */ 
/*     */         }
/* 134 */         else if (lines[1] != "Start:")
/* 135 */           if ((player.hasPermission("golf.ref")) || (player.isOp())) {
/* 136 */             String signPlayer = ChatColor.stripColor(lines[1]);
/* 137 */             player.sendMessage(ChatColor.GRAY + "You have removed " + ChatColor.GREEN + signPlayer + ChatColor.GRAY + "'" + ChatColor.GREEN + "s " + ChatColor.GRAY + "sign.");
/*     */ 
/* 139 */             Player[] list = Bukkit.getOnlinePlayers();
/* 140 */             for (Player players : list)
/* 141 */               if (players.getName().equals(signPlayer)) {
/* 142 */                 GcEntityListener.signExists.put(players, Boolean.valueOf(false));
/* 143 */                 block.setType(Material.AIR);
/*     */               }
/*     */           }
/*     */           else
/*     */           {
/* 148 */             event.setCancelled(true);
/* 149 */             block.getState().update(true);
/*     */           }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcBlockListener
 * JD-Core Version:    0.6.2
 */