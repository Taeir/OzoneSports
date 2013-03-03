/*     */ 
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ //import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ //import org.bukkit.inventory.PlayerInventory;
/*     */ //import org.bukkit.plugin.PluginDescriptionFile;
/*     */ 
/*     */ public class GcCommands
/*     */   implements CommandExecutor
/*     */ {
/*  26 */   @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<Player, Boolean> Golfing = new HashMap();
/*  27 */   @SuppressWarnings({ "unchecked", "rawtypes" })
public static HashMap<Player, String> playingHole = new HashMap();
/*     */ 
/*     */   @SuppressWarnings("unused")
public boolean onCommand(CommandSender sender, Command command, String label, String[] arg)
/*     */   {
/*  36 */     Player player = (Player)sender;
/*     */ 
/*  38 */     if (arg.length == 0) {
/*  39 */       boolean golfing = false;
/*     */ 
/*  41 */       if ((player.hasPermission("golf.spectate")) || (player.isOp()) || (player.hasPermission("golf.play"))) {
/*  42 */         if (Golfing.containsKey(player)) {
/*  43 */           if (((Boolean)Golfing.get(player)).booleanValue()) {
/*  44 */             Golfing.put(player, Boolean.valueOf(false));
/*  45 */             GcEntityListener.finishedHole.put(player, Boolean.valueOf(true));
/*  46 */             player.sendMessage(ChatColor.GRAY + "You have been removed from all golfing activity.");
/*  47 */             if (GcEntityListener.signLocation.containsKey(player)) {
/*  48 */               Block block = ((Location)GcEntityListener.signLocation.get(player)).getBlock();
/*  49 */               block.setType(Material.AIR);
/*  50 */               GcEntityListener.signLocation.remove(player);
/*     */             }
/*  52 */             if (GcEntityListener.signExists.containsKey(player)) {
/*  53 */               GcEntityListener.signExists.remove(player);
/*     */             }
/*  55 */             if (GcBlockListener.signLocation.containsKey(player)) {
/*  56 */               Block block = ((Location)GcBlockListener.signLocation.get(player)).getBlock();
/*  57 */               block.setType(Material.AIR);
/*  58 */               GcBlockListener.signLocation.remove(player);
/*     */             }
/*  60 */             if (GcEntityListener.score.containsKey(player))
/*  61 */               GcEntityListener.score.remove(player);
/*     */           }
/*     */           else
/*     */           {
/*  65 */             Golfing.put(player, Boolean.valueOf(true));
/*  66 */             player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*  67 */             player.sendMessage(ChatColor.GRAY + "You have not chosen a hole. You can do so with:");
/*  68 */             player.sendMessage(ChatColor.GRAY + "/golf play [hole]");
/*  69 */             golfing = true;
/*     */           }
/*     */         }
/*     */         else {
/*  73 */           Golfing.put(player, Boolean.valueOf(true));
/*  74 */           player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*  75 */           player.sendMessage(ChatColor.GRAY + "You have not chosen a hole. You can do so with:");
/*  76 */           player.sendMessage(ChatColor.GRAY + "/golf play [hole]");
/*  77 */           golfing = true;
/*     */         }
/*     */ 
/*  80 */         if (golfing) if (!player.hasPermission("golf.play")) { if (!player.isOp()); } else {
/*  81 */             while (player.getInventory().contains(Material.ARROW)) {
/*  82 */               player.getInventory().remove(Material.ARROW);
/*     */             }
/*  84 */             ItemStack arrow = new ItemStack(Material.ARROW);
/*  85 */             arrow.setAmount(1);
/*  86 */             player.getInventory().addItem(new ItemStack[] { arrow });
/*  87 */             while (player.getInventory().contains(Material.BOW)) {
/*  88 */               player.getInventory().remove(Material.BOW);
/*     */             }
/*  90 */             ItemStack bow = new ItemStack(Material.BOW);
/*  91 */             arrow.setAmount(1);
/*  92 */             player.getInventory().addItem(new ItemStack[] { bow });
					  //break label720;
					  return true;
/*     */           }
/*     */ 
/*  95 */         while (player.getInventory().contains(Material.ARROW))
/*     */         {
/*     */           ItemStack arrow;
/*     */           ItemStack bow;
/*  96 */           player.getInventory().remove(Material.ARROW);
/*     */         }
/*  98 */         while (player.getInventory().contains(Material.BOW)) {
/*  99 */           player.getInventory().remove(Material.BOW);
/*     */         }
/*     */ 
/* 102 */         label720: return true;
/*     */       }
/*     */ 
/* 105 */       player.sendMessage(ChatColor.DARK_RED + "You don't have permission to spectate golf!");
/* 106 */       return true;
/*     */     }
/*     */ 
/* 110 */     if (arg.length > 0) {
/* 111 */       String Command = arg[0];
/*     */ 
/* 113 */       if (Command.equalsIgnoreCase("help")) {
/* 114 */         if ((arg.length == 1) || ((arg.length == 2) && (arg[1].equals("1")))) {
/* 115 */           player.sendMessage(ChatColor.GRAY + "Golfcraft " + ChatColor.GREEN + "v" + Golfcraft.info.getVersion() + ChatColor.GRAY + " Page 1/4");
/* 116 */           player.sendMessage(ChatColor.GRAY + "____________________________________");
/* 117 */           player.sendMessage(ChatColor.GREEN + "/golf" + ChatColor.GRAY + " - enters/exits golf mode");
/* 118 */           player.sendMessage(ChatColor.GREEN + "/golf help (page)" + ChatColor.GRAY + " - what you just typed");
/* 119 */           player.sendMessage(ChatColor.GREEN + "/golf version" + ChatColor.GRAY + " - checks for updates");
/* 120 */           player.sendMessage(ChatColor.GREEN + "/golf tp (player)" + ChatColor.GRAY + " - TP's you or others to sign");
/* 121 */           player.sendMessage(ChatColor.GREEN + "/golf create [name] [par]" + ChatColor.GRAY + " - creates a new hole and sets par");
/* 122 */           player.sendMessage(ChatColor.GREEN + "/golf play [name]" + ChatColor.GRAY + " - TP's you to that hole to begin new round");
/* 123 */           player.sendMessage("");
/* 124 */           player.sendMessage("");
/* 125 */           return true;
/*     */         }
/* 127 */         if (arg[1].equals("2")) {
/* 128 */           player.sendMessage(ChatColor.GRAY + "Golfcraft " + ChatColor.GREEN + "v" + Golfcraft.info.getVersion() + ChatColor.GRAY + " Page 2/4");
/* 129 */           player.sendMessage(ChatColor.GRAY + "____________________________________");
/* 130 */           player.sendMessage(ChatColor.GRAY + "Green Wool - " + ChatColor.DARK_GREEN + "Fairway" + ChatColor.GRAY + " (main material)");
/* 131 */           player.sendMessage(ChatColor.GRAY + "Lime Wool - " + ChatColor.GREEN + "The Green" + ChatColor.GRAY + " (putting ground)");
/* 132 */           player.sendMessage(ChatColor.GRAY + "Glowstone - " + ChatColor.GREEN + "The " + ChatColor.GOLD + "Cup");
/* 133 */           player.sendMessage(ChatColor.GRAY + "Sand - " + ChatColor.YELLOW + "Sandtrap");
/* 134 */           player.sendMessage(ChatColor.GRAY + "Grass - Rough  (border of course)");
/* 135 */           player.sendMessage(ChatColor.GRAY + "Wood - Teeing Ground (Start of course)");
/* 136 */           player.sendMessage("");
/* 137 */           player.sendMessage("");
/* 138 */           return true;
/*     */         }
/* 140 */         if (arg[1].equals("3")) {
/* 141 */           player.sendMessage(ChatColor.GRAY + "Golfcraft " + ChatColor.GREEN + "v" + Golfcraft.info.getVersion() + ChatColor.GRAY + " Page 3/4");
/* 142 */           player.sendMessage(ChatColor.GRAY + "____________________________________");
/* 143 */           player.sendMessage(ChatColor.RED + "Hazards:");
/* 144 */           player.sendMessage(ChatColor.GRAY + " - " + ChatColor.RED + "Water");
/* 145 */           player.sendMessage(ChatColor.GRAY + " - " + ChatColor.RED + "Trees");
/* 146 */           player.sendMessage(ChatColor.GRAY + " - " + ChatColor.RED + "Lava");
/* 147 */           player.sendMessage("");
/* 148 */           player.sendMessage("");
/* 149 */           player.sendMessage("");
/* 150 */           player.sendMessage("");
/* 151 */           return true;
/*     */         }
/*     */ 
/* 154 */         if (arg[1].equals("4")) {
/* 155 */           player.sendMessage(ChatColor.GRAY + "Golfcraft " + ChatColor.GREEN + "v" + Golfcraft.info.getVersion() + ChatColor.GRAY + " Page 4/4");
/* 156 */           player.sendMessage(ChatColor.GRAY + "____________________________________");
/* 157 */           player.sendMessage(ChatColor.GRAY + "You can create new holes where you're standing.");
/* 158 */           player.sendMessage(ChatColor.GRAY + "Best to create holes while on your teeing ground (wood).");
/* 159 */           player.sendMessage(ChatColor.GRAY + "Entering golf mode will force your inventory to have 1 arrow.");
/* 160 */           player.sendMessage(ChatColor.GRAY + "Par for each hole is set on creation. You scores will be");
/* 161 */           player.sendMessage(ChatColor.GRAY + "based on that number. Scores dont carry over to new holes.");
/* 162 */           player.sendMessage("");
/* 163 */           player.sendMessage(ChatColor.GRAY + "You will get your arrow back once you destroy your sign, or");
/* 164 */           player.sendMessage(ChatColor.GRAY + "if you land in a hazard.");
/* 165 */           return true;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/*     */         String inputLine;
/* 169 */         if (Command.equalsIgnoreCase("version")) {
/* 170 */           sender.sendMessage(ChatColor.GRAY + "You are running " + ChatColor.GREEN + "Golfcraft " + ChatColor.GRAY + Golfcraft.info.getVersion());
/*     */           try
/*     */           {
/* 173 */             URL version = new URL("http://dl.dropbox.com/u/25125155/GolfCraft/Version.txt");
/* 174 */             URLConnection dc = version.openConnection();
/* 175 */             BufferedReader in = new BufferedReader(new InputStreamReader(dc.getInputStream()));
/*     */ 
/* 178 */             while ((inputLine = in.readLine()) != null)
/*     */             {
/*     */               //String inputLine;
/* 179 */               if (inputLine.equals(Golfcraft.info.getVersion())) {
/* 180 */                 sender.sendMessage(ChatColor.GRAY + "Golfcraft is up to date!");
/*     */               }
/*     */               else {
/* 183 */                 sender.sendMessage(ChatColor.GRAY + "Golfcraft is Out of date! The latest version is:");
/* 184 */                 sender.sendMessage(ChatColor.GREEN + inputLine);
/* 185 */                 sender.sendMessage(ChatColor.GRAY + "You can download the latest version here:");
/* 186 */                 sender.sendMessage("http://dev.bukkit.org/server-mods/golfcraft/files/");
/*     */               }
/*     */             }
/* 189 */             in.close();
/*     */           }
/*     */           catch (IOException e) {
/* 192 */             e.printStackTrace();
/*     */           }
/*     */ 
/* 195 */           return true;
/*     */         }
/*     */ 
/* 198 */         if (Command.equalsIgnoreCase("tp")) {
/* 199 */           if (arg.length == 1) {
/* 200 */             if ((player.hasPermission("golf.tp.sign")) || (player.isOp())) {
/* 201 */               if (GcEntityListener.signExists.containsKey(player)) {
/* 202 */                 if (((Boolean)GcEntityListener.signExists.get(player)).booleanValue()) {
/* 203 */                   if (GcEntityListener.signLocation.containsKey(player)) {
/* 204 */                     player.teleport((Location)GcEntityListener.signLocation.get(player));
/* 205 */                     player.sendMessage(ChatColor.GREEN + "You have been teleported to your sign.");
/* 206 */                     return true;
/*     */                   }
/*     */ 
/* 209 */                   player.sendMessage(ChatColor.GRAY + "You do not have a sign.");
/* 210 */                   return true;
/*     */                 }
/*     */ 
/* 214 */                 player.sendMessage(ChatColor.GRAY + "You do not have a sign.");
/* 215 */                 return true;
/*     */               }
/*     */ 
/* 219 */               player.sendMessage(ChatColor.GRAY + "You do not have a sign.");
/* 220 */               return true;
/*     */             }
/*     */ 
/* 224 */             player.sendMessage(ChatColor.DARK_RED + "You don't have permission to teleport!");
/* 225 */             return true;
/*     */           }
/*     */ 
/* 230 */           if ((player.hasPermission("golf.tp.sign.others")) || (player.isOp())) {
/* 231 */             String tpPlayer = arg[1];
/* 232 */             Player[] list = Bukkit.getOnlinePlayers();
/*     */             Player[] arrayOfPlayer1;
/* 233 */             int str1 = (arrayOfPlayer1 = list).length;
						for (int j = 0; j < str1; j++) {
						Player players = arrayOfPlayer1[j];
/* 234 */               if ((players.getName().equals(tpPlayer)) && 
/* 235 */                 (GcEntityListener.signExists.containsKey(players)) && 
/* 236 */                 (((Boolean)GcEntityListener.signExists.get(players)).booleanValue()) && 
/* 237 */                 (GcEntityListener.signLocation.containsKey(players))) {
/* 238 */                 players.teleport((Location)GcEntityListener.signLocation.get(players));
/* 239 */                 player.sendMessage(ChatColor.GRAY + "The player " + ChatColor.DARK_GREEN + arg[1] + ChatColor.GRAY + " has been teleported.");
/* 240 */                 players.sendMessage(ChatColor.GREEN + "You have been teleported to your sign.");
/* 241 */                 return true;
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 247 */             player.sendMessage(ChatColor.RED + "The player " + ChatColor.DARK_GREEN + arg[1] + ChatColor.RED + " is not found.");
/* 248 */             return true;
/*     */           }
/*     */ 
/* 251 */           player.sendMessage(ChatColor.DARK_RED + "You don't have permission to teleport others!");
/* 252 */           return true;
/*     */         }
/*     */ 
/* 257 */         if (Command.equalsIgnoreCase("create")) {
/* 258 */           String holeName = null;
/* 259 */           String testString = null;
/*     */           try
/*     */           {
/* 262 */             holeName = arg[1];
/* 263 */             testString = arg[2];
/*     */           }
/*     */           catch (IndexOutOfBoundsException e) {
/* 266 */             player.sendMessage(ChatColor.GRAY + "Need to specify hole and par(#).");
/* 267 */             player.sendMessage(ChatColor.GRAY + "/golf create [hole] [par]");
/* 268 */             return true;
/*     */           }
/*     */           try
/*     */           {
/* 272 */             Integer.parseInt(testString);
/*     */           }
/*     */           catch (NumberFormatException e) {
/* 275 */             player.sendMessage("Par needs to be an integer");
/* 276 */             return true;
/*     */           }
/*     */ 
/* 279 */           int parAmount = Integer.parseInt(testString);
/*     */ 
/* 281 */           if (!holeName.equals(null)) {
/* 282 */             Block block = player.getLocation().getBlock();
/* 283 */             if ((player.isOp()) || (player.hasPermission("golf.create"))) {
/* 284 */               if (!GcFiles.holeExists(player, holeName, parAmount))
/*     */               {
/* 286 */                 GcFiles.addHole(new GcHole(player.getWorld().getName(), holeName, parAmount, block));
/* 287 */                 GcFiles.save();
/* 288 */                 player.sendMessage(ChatColor.GREEN + "You have created " + ChatColor.AQUA + holeName + ": par " + parAmount);
/* 289 */                 return true;
/*     */               }
/*     */ 
/* 293 */               GcHole existingHole = GcFiles.getHole(player.getWorld().getName(), holeName);
/*     */ 
/* 295 */               player.sendMessage(ChatColor.RED + "You have already created " + ChatColor.AQUA + holeName + ": par " + existingHole.par);
/*     */ 
/* 297 */               return true;
/*     */             }
/*     */ 
/* 301 */             player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use that command.");
/* 302 */             return true;
/*     */           }
/*     */ 
/*     */         }
/* 307 */         else if (Command.equals("play")) {
/* 308 */           String holeName = null;
/*     */           try
/*     */           {
/* 311 */             holeName = arg[1];
/*     */           }
/*     */           catch (IndexOutOfBoundsException e) {
/* 314 */             player.sendMessage(ChatColor.GRAY + "Need to specify hole.");
/* 315 */             player.sendMessage(ChatColor.GRAY + "/golf play [hole]");
/* 316 */             return true;
/*     */           }
/* 318 */           if (!holeName.equals(null)) {
/* 319 */             if ((player.isOp()) || (player.hasPermission("golf.tp.play"))) {
/* 320 */               GcHole existingHole = GcFiles.getHole(player.getWorld().getName(), holeName);
/*     */               try {
/* 322 */                 GcFiles.holeExists(player, holeName);
/*     */               }
/*     */               catch (NullPointerException e) {
/* 325 */                 player.sendMessage(ChatColor.GRAY + "That hole does not exist!");
/* 326 */                 return true;
/*     */               }
/* 328 */               if (GcFiles.holeExists(player, holeName)) {
/* 329 */                 if (GcEntityListener.signExists.containsKey(player)) {
/* 330 */                   if ((((Boolean)GcEntityListener.signExists.get(player)).booleanValue()) && 
/* 331 */                     (GcEntityListener.signLocation.containsKey(player))) {
/* 332 */                     ((Location)GcEntityListener.signLocation.get(player)).getBlock().setType(Material.AIR);
/* 333 */                     GcEntityListener.signLocation.remove(player);
/*     */                   }
/*     */ 
/* 336 */                   GcEntityListener.signExists.remove(player);
/*     */                 }
/* 338 */                 Location tpHoleLoc = GcFiles.getHole(player.getWorld().getName(), holeName).getBlock().getLocation();
/* 339 */                 player.teleport(tpHoleLoc);
/*     */ 
/* 341 */                 if (Golfing.containsKey(player))
/*     */                 {
/* 343 */                   if (!((Boolean)Golfing.get(player)).booleanValue()) {
/* 344 */                     Golfing.put(player, Boolean.valueOf(true));
/* 345 */                     player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*     */                   }
/*     */                 }
/*     */                 else {
/* 349 */                   Golfing.put(player, Boolean.valueOf(true));
/* 350 */                   player.sendMessage(ChatColor.GOLD + "You are now golfing!");
/*     */                 }
/*     */ 
/* 353 */                 if (GcEntityListener.finishedHole.containsKey(player)) {
/* 354 */                   if (((Boolean)GcEntityListener.finishedHole.get(player)).booleanValue()) {
/* 355 */                     GcEntityListener.finishedHole.put(player, Boolean.valueOf(false));
/*     */                   }
/*     */                 }
/*     */                 else {
/* 359 */                   GcEntityListener.finishedHole.put(player, Boolean.valueOf(false));
/*     */                 }
/*     */ 
/* 364 */                 Player[] list = Bukkit.getOnlinePlayers();
/* 365 */                 for (Player players : list)
/*     */                 {
/* 367 */                   if ((Golfing.containsKey(players)) && 
/* 368 */                     (((Boolean)Golfing.get(players)).booleanValue()))
/*     */                   {
/* 370 */                     players.sendMessage(ChatColor.DARK_GREEN + player.getName() + ChatColor.GRAY + " has begun " + ChatColor.GOLD + holeName + " par: " + existingHole.par);
/*     */                   }
/*     */ 
/*     */                 }
/*     */ 
/* 376 */                 while (player.getInventory().contains(Material.ARROW)) {
/* 377 */                   player.getInventory().remove(Material.ARROW);
/*     */                 }
/* 379 */                 ItemStack arrow = new ItemStack(Material.ARROW);
/* 380 */                 arrow.setAmount(1);
/* 381 */                 player.getInventory().addItem(new ItemStack[] { arrow });
/* 382 */                 while (player.getInventory().contains(Material.BOW)) {
/* 383 */                   player.getInventory().remove(Material.BOW);
/*     */                 }
/* 385 */                 ItemStack bow = new ItemStack(Material.BOW);
/* 386 */                 arrow.setAmount(1);
/* 387 */                 player.getInventory().addItem(new ItemStack[] { bow });
/* 388 */                 GcEntityListener.score.put(player, Integer.valueOf(0));
/* 389 */                 playingHole.put(player, holeName);
/* 390 */                 return true;
/*     */               }
/*     */ 
/* 393 */               player.sendMessage(ChatColor.AQUA + holeName + ChatColor.RED + " doesn't exist in world: " + player.getWorld().getName());
/* 394 */               return true;
/*     */             }
/*     */ 
/* 398 */             player.sendMessage(ChatColor.DARK_RED + "You do not have permission to use that command.");
/* 399 */             return true;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/* 404 */     player.sendMessage(ChatColor.GRAY + "/golf help");
/* 405 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcCommands
 * JD-Core Version:    0.6.2
 */