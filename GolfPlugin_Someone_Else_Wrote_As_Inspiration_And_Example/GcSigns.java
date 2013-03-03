/*     */ 
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.event.block.BlockBreakEvent;
/*     */ import org.bukkit.event.block.BlockPlaceEvent;
/*     */ import org.bukkit.event.block.SignChangeEvent;
/*     */ import org.bukkit.event.player.PlayerInteractEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.scheduler.BukkitScheduler;
/*     */ 
/*     */ public class GcSigns
/*     */   implements Listener
/*     */ {
/*     */   public Golfcraft plugin;
/*     */ 
/*     */   public GcSigns(Golfcraft plugin)
/*     */   {
/*  28 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onBlockBreak(BlockBreakEvent event)
/*     */   {
/*  34 */     if ((event.getBlock() != null) && 
/*  35 */       (event.getBlock().getState() != null) && 
/*  36 */       ((event.getBlock().getState() instanceof Sign))) {
/*  37 */       Sign sign = null;
/*     */       try {
/*  39 */         sign = (Sign)event.getBlock();
/*     */       }
/*     */       catch (ClassCastException|NullPointerException e) {
/*  42 */         return;
/*     */       }
/*  44 */       String[] lines = sign.getLines();
/*  45 */       Player player = event.getPlayer();
/*  46 */       if ((lines[0].equalsIgnoreCase(ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]")) && 
/*  47 */         (GcFiles.holeExists(player, lines[2])))
/*  48 */         if ((!player.isOp()) && (!player.hasPermission("golf.sign"))) {
/*  49 */           player.sendMessage(ChatColor.DARK_RED + "You do not have permission to remove golf signs.");
/*  50 */           event.setCancelled(true);
/*  51 */           final Location bl = event.getBlock().getLocation();
/*  52 */           this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable()
/*     */           {
/*     */             public void run() {
/*  55 */               bl.getBlock().getState().update();
/*     */             }
/*     */           }
/*     */           , 20L);
/*     */         }
/*     */         else {
/*  60 */           player.sendMessage(ChatColor.GRAY + "You have removed a golf play sign");
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onBlockPlaceEvent(BlockPlaceEvent event)
/*     */   {
/*  72 */     if (event.getBlockAgainst() == null) {
/*  73 */       return;
/*     */     }
/*     */ 
/*  76 */     if (event.getBlockAgainst().getState() == null) {
/*  77 */       return;
/*     */     }
/*     */ 
/*  80 */     if ((event.getBlockAgainst().getState() instanceof Sign)) {
/*  81 */       Sign sign = (Sign)event.getBlockAgainst().getState();
/*     */ 
/*  83 */       if (ChatColor.stripColor(sign.getLine(0)).equalsIgnoreCase("[golf]"))
/*  84 */         event.setCancelled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   @EventHandler
/*     */   public void onSignChange(SignChangeEvent event)
/*     */   {
/*  92 */     Player player = event.getPlayer();
/*     */ 
/*  94 */     String[] lines = event.getLines();
/*     */ 
/*  96 */     if ((lines[0].equalsIgnoreCase("golf")) || (lines[0].equalsIgnoreCase("[golf]")))
/*     */     {
/*  98 */       if ((player.isOp()) || (player.hasPermission("golf.sign")))
/*     */       {
/* 100 */         String hole = lines[2];
/*     */ 
/* 102 */         if (GcFiles.holeExists(player, hole)) {
/* 103 */           event.setLine(0, ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]");
/* 104 */           event.setLine(1, "Start:");
/* 105 */           event.setLine(2, hole);
/* 106 */           event.setLine(3, "");
/* 107 */           player.sendMessage(ChatColor.GREEN + "Successfully created a play sign!");
/*     */         }
/*     */         else {
/* 110 */           event.setLine(0, ChatColor.DARK_GRAY + "   [" + ChatColor.DARK_RED + "Golf" + ChatColor.DARK_GRAY + "]");
/* 111 */           event.setLine(1, ChatColor.DARK_GRAY + "This hole...");
/* 112 */           event.setLine(2, ChatColor.DARK_GRAY + "Is a lie!!!");
/* 113 */           event.setLine(3, "");
/*     */         }
/*     */       }
/*     */       else {
/* 117 */         event.setLine(0, ChatColor.DARK_GRAY + "   [" + ChatColor.DARK_RED + "Golf" + ChatColor.DARK_GRAY + "]");
/* 118 */         event.setLine(1, ChatColor.DARK_GRAY + "You can not");
/* 119 */         event.setLine(2, ChatColor.DARK_GRAY + "create golf");
/* 120 */         event.setLine(3, ChatColor.DARK_GRAY + "play signs!");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.NORMAL)
/*     */   public void onPlayerInteract(PlayerInteractEvent event)
/*     */   {
/* 129 */     if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
/* 130 */       return;
/*     */     }
/* 132 */     if (event.getClickedBlock() == null) {
/* 133 */       return;
/*     */     }
/*     */ 
/* 136 */     if (event.getClickedBlock().getState() == null) {
/* 137 */       return;
/*     */     }
/*     */ 
/* 140 */     if ((event.getClickedBlock().getState() instanceof Sign)) {
/* 141 */       Player player = event.getPlayer();
/*     */ 
/* 143 */       Sign sign = (Sign)event.getClickedBlock().getState();
/* 144 */       String[] lines = sign.getLines();
/*     */ 
/* 146 */       String signLine1 = lines[0];
/*     */ 
/* 148 */       if (signLine1.equalsIgnoreCase(ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]"))
/*     */       {
/* 150 */         if ((player.isOp()) || (player.hasPermission("golf.play")))
/*     */         {
/* 152 */           String hole = ChatColor.stripColor(lines[2]);
/*     */ 
/* 155 */           if (GcFiles.holeExists(player, hole)) {
/* 156 */             GcHole existingHole = GcFiles.getHole(player.getWorld().getName(), hole);
/* 157 */             if (GcEntityListener.signExists.containsKey(player)) {
/* 158 */               if ((((Boolean)GcEntityListener.signExists.get(player)).booleanValue()) && 
/* 159 */                 (GcEntityListener.signLocation.containsKey(player))) {
/* 160 */                 ((Location)GcEntityListener.signLocation.get(player)).getBlock().setType(Material.AIR);
/* 161 */                 GcEntityListener.signLocation.remove(player);
/*     */               }
/*     */ 
/* 164 */               GcEntityListener.signExists.remove(player);
/*     */             }
/* 166 */             Location tpHoleLoc = GcFiles.getHole(player.getWorld().getName(), hole).getBlock().getLocation();
/* 167 */             player.teleport(tpHoleLoc);
/*     */ 
/* 169 */             if (GcCommands.Golfing.containsKey(player)) {
/* 170 */               if (!((Boolean)GcCommands.Golfing.get(player)).booleanValue()) {
/* 171 */                 GcCommands.Golfing.put(player, Boolean.valueOf(true));
/* 172 */                 player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*     */               }
/*     */             }
/*     */             else {
/* 176 */               GcCommands.Golfing.put(player, Boolean.valueOf(true));
/* 177 */               player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*     */             }
/*     */ 
/* 180 */             if (GcEntityListener.finishedHole.containsKey(player)) {
/* 181 */               if (((Boolean)GcEntityListener.finishedHole.get(player)).booleanValue()) {
/* 182 */                 GcEntityListener.finishedHole.put(player, Boolean.valueOf(false));
/*     */               }
/*     */             }
/*     */             else {
/* 186 */               GcEntityListener.finishedHole.put(player, Boolean.valueOf(false));
/*     */             }
/*     */ 
/* 189 */             if (hole != GcCommands.playingHole.get(player)) {
/* 190 */               Player[] list = Bukkit.getOnlinePlayers();
/* 191 */               for (Player players : list)
/*     */               {
/* 193 */                 if ((GcCommands.Golfing.containsKey(players)) && 
/* 194 */                   (((Boolean)GcCommands.Golfing.get(players)).booleanValue()))
/*     */                 {
/* 196 */                   players.sendMessage(ChatColor.DARK_GREEN + player.getName() + ChatColor.GRAY + " has begun " + ChatColor.GOLD + hole + " par: " + existingHole.par);
/*     */                 }
/*     */               }
/*     */ 
/*     */             }
/*     */             else
/*     */             {
/* 203 */               player.sendMessage(ChatColor.GRAY + "You have restarted " + ChatColor.GOLD + hole + " par: " + existingHole.par);
/*     */             }
/*     */ 
/* 206 */             while (player.getInventory().contains(Material.ARROW)) {
/* 207 */               player.getInventory().remove(Material.ARROW);
/*     */             }
/* 209 */             ItemStack arrow = new ItemStack(Material.ARROW);
/* 210 */             arrow.setAmount(1);
/* 211 */             player.getInventory().addItem(new ItemStack[] { arrow });
/* 212 */             while (player.getInventory().contains(Material.BOW)) {
/* 213 */               player.getInventory().remove(Material.BOW);
/*     */             }
/* 215 */             ItemStack bow = new ItemStack(Material.BOW);
/* 216 */             arrow.setAmount(1);
/* 217 */             player.getInventory().addItem(new ItemStack[] { bow });
/* 218 */             GcEntityListener.score.put(player, Integer.valueOf(0));
/* 219 */             GcCommands.playingHole.put(player, hole);
/* 220 */             player.updateInventory();
/*     */           }
/*     */           else
/*     */           {
/* 224 */             player.sendMessage(ChatColor.GOLD + "[CK] " + ChatColor.RED + "That hole no longer exists!");
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcSigns
 * JD-Core Version:    0.6.2
 */