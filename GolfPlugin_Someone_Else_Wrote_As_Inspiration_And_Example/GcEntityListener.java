/*     */ 
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.Sign;
/*     */ import org.bukkit.configuration.file.FileConfiguration;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.Snowball;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.ProjectileHitEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.PlayerInventory;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class GcEntityListener
/*     */   implements Listener
/*     */ {
/*     */   Golfcraft plugin;
/*  40 */   ArrayList<Snowball> bounce1 = new ArrayList();
/*     */ 
/*  42 */   public static HashMap<Player, Integer> bounce = new HashMap();
/*  43 */   public static HashMap<Snowball, Player> snowballShooters = new HashMap();
/*  44 */   public static HashMap<Player, Boolean> signExists = new HashMap();
/*  45 */   public static HashMap<Player, Location> signLocation = new HashMap();
/*  46 */   public static HashMap<Player, Boolean> finishedHole = new HashMap();
/*  47 */   public static HashMap<Player, Integer> score = new HashMap();
/*     */ 
/*     */   GcEntityListener(Golfcraft plugin)
/*     */   {
/*  37 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onProjectileHit(ProjectileHitEvent event)
/*     */   {
/*  57 */     onProjectileHit_Func(event);
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onEntityTarget(EntityTargetEvent event)
/*     */   {
/*  63 */     onEntityTarget_Func(event);
/*     */   }
/*     */ 
/*     */   @EventHandler(priority=EventPriority.HIGH)
/*     */   public void onEntityShootBow(EntityShootBowEvent event)
/*     */   {
/*  69 */     onEntityShootBow_Func(event);
/*     */   }
/*     */ 
/*     */   private void onEntityShootBow_Func(EntityShootBowEvent event)
/*     */   {
/*  77 */     if ((event.getEntity() instanceof Player)) {
/*  78 */       Player shooter = (Player)event.getEntity();
/*     */ 
/*  80 */       if (snowballShooters.containsValue(shooter))
/*     */       {
/*  82 */         return;
/*     */       }
/*  84 */       String shooterName = shooter.getName();
/*  85 */       boolean isGolfer = true;
/*     */ 
/*  87 */       if (!GcCommands.Golfing.containsKey(shooter)) {
/*  88 */         isGolfer = false;
/*     */       }
/*  90 */       else if (!((Boolean)GcCommands.Golfing.get(shooter)).booleanValue()) {
/*  91 */         isGolfer = false;
/*     */       }
/*     */ 
/*  94 */       if (!signExists.containsKey(shooter)) {
/*  95 */         signExists.put(shooter, Boolean.valueOf(false));
/*     */       }
/*  97 */       if ((shooter.hasPermission("golf.play")) || (shooter.isOp()))
/*     */       {
/*  99 */         if (isGolfer)
/* 100 */           if (!((Boolean)signExists.get(shooter)).booleanValue())
/*     */           {
/* 102 */             Player[] list = Bukkit.getOnlinePlayers();
/* 103 */             for (Player players : list)
/*     */             {
/* 105 */               if ((GcCommands.playingHole.containsKey(players)) && 
/* 106 */                 (((String)GcCommands.playingHole.get(players)).equals(GcCommands.playingHole.get(shooter))))
/*     */               {
/* 108 */                 boolean Continue = false;
/*     */ 
/* 110 */                 if (finishedHole.containsKey(shooter)) {
/* 111 */                   if (!((Boolean)finishedHole.get(shooter)).booleanValue())
/* 112 */                     Continue = true;
/*     */                 }
/*     */                 else
/*     */                 {
/* 116 */                   Continue = true;
/* 117 */                   finishedHole.put(shooter, Boolean.valueOf(false));
/*     */                 }
/*     */ 
/* 120 */                 if (Continue) {
/* 121 */                   if (!GcClubs.Club.containsKey(shooter)) {
/* 122 */                     GcClubs.Club.put(shooter, Integer.valueOf(0));
/*     */                   }
/* 124 */                   if (this.plugin.getConfig().getBoolean("enable-clubs")) {
/* 125 */                     players.sendMessage(ChatColor.DARK_GREEN + shooterName + ChatColor.GRAY + " swung their " + GcClubs.getClubMessage2(shooter));
/* 126 */                     if (!GcClubs.getEfficiencyMessage(shooter).isEmpty())
/* 127 */                       shooter.sendMessage(GcClubs.getEfficiencyMessage(shooter));
/*     */                   }
/*     */                   else {
/* 130 */                     players.sendMessage(ChatColor.DARK_GREEN + shooterName + ChatColor.GRAY + " has launched the ball!");
/*     */                   }
/*     */ 
/*     */                 }
/*     */ 
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 139 */             if (score.containsKey(shooter)) {
/* 140 */               score.put(shooter, Integer.valueOf(((Integer)score.get(shooter)).intValue() + 1));
/*     */             }
/*     */             else {
/* 143 */               score.put(shooter, Integer.valueOf(1));
/*     */             }
/*     */ 
/* 146 */             GcClubs.Force.put(shooter, Float.valueOf(event.getForce()));
/*     */ 
/* 148 */             Entity proj = event.getProjectile();
/* 149 */             World world = proj.getWorld();
/*     */ 
/* 151 */             Snowball newProj = (Snowball)world.spawn(proj.getLocation(), Snowball.class);
/*     */ 
/* 153 */             newProj.setShooter(shooter);
/* 154 */             newProj.setVelocity(proj.getVelocity());
/* 155 */             newProj.setBounce(true);
/*     */ 
/* 157 */             if ((!this.plugin.getConfig().getBoolean("enable-clubs")) && (this.plugin.getConfig().getBoolean("enable-speed-variances"))) {
/* 158 */               if (shooter.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 159 */                 int chance = new Random().nextInt(GcConfig.sandChance());
/* 160 */                 chance = GcConfig.sandBase() + chance;
/* 161 */                 double calcChance = chance / 100.0D;
/* 162 */                 chance = 100 - chance;
/* 163 */                 newProj.setVelocity(newProj.getVelocity().multiply(calcChance));
/* 164 */                 shooter.sendMessage(ChatColor.YELLOW + "The sand trap reduced your speed by " + chance + "%");
/*     */               }
/* 166 */               if (shooter.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
/* 167 */                 int chance = new Random().nextInt(GcConfig.roughChance());
/* 168 */                 chance = GcConfig.roughBase() - chance;
/* 169 */                 double calcChance = chance / 100.0D;
/* 170 */                 chance = 100 - chance;
/* 171 */                 newProj.setVelocity(newProj.getVelocity().multiply(calcChance));
/* 172 */                 shooter.sendMessage(ChatColor.GRAY + "The rough reduced your speed by " + ChatColor.YELLOW + chance + "%");
/*     */               }
/* 174 */               if (shooter.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
/* 175 */                 int chance = new Random().nextInt(GcConfig.teeingGroundChance());
/* 176 */                 chance = GcConfig.teeingGroundBase() + chance;
/* 177 */                 double calcChance = chance / 100.0D;
/* 178 */                 chance -= 100;
/* 179 */                 newProj.setVelocity(newProj.getVelocity().multiply(calcChance));
/* 180 */                 shooter.sendMessage(ChatColor.GRAY + "Your driver increased your speed by " + ChatColor.YELLOW + chance + "%");
/*     */               }
/*     */             }
/*     */             else {
/* 184 */               if (!GcClubs.Club.containsKey(shooter)) {
/* 185 */                 GcClubs.Club.put(shooter, Integer.valueOf(0));
/*     */               }
/* 187 */               Float force = Float.valueOf(event.getForce());
/* 188 */               Float dirX = Float.valueOf((float)((0.0D - Math.sin(shooter.getLocation().getYaw() / 180.0F * 3.141592653589793D) * 3.0D) * force.floatValue()));
/* 189 */               Float dirZ = Float.valueOf((float)(Math.cos(shooter.getLocation().getYaw() / 180.0F * 3.141592653589793D) * 3.0D * force.floatValue()));
/* 190 */               newProj.setVelocity(newProj.getVelocity().setX(dirX.floatValue()));
/* 191 */               newProj.setVelocity(newProj.getVelocity().setZ(dirZ.floatValue()));
/* 192 */               if (!((Integer)GcClubs.Club.get(shooter)).equals(Integer.valueOf(11))) {
/* 193 */                 newProj.setVelocity(newProj.getVelocity().setY(GcClubs.getClubPitch(shooter).floatValue()));
/*     */               }
/* 195 */               newProj.setVelocity(newProj.getVelocity().multiply(GcClubs.getClubSpeed(shooter).doubleValue() * 0.475D * (this.plugin.getConfig().getInt("ball-power-percentage") / 100.0D)));
/*     */             }
/*     */ 
/* 198 */             snowballShooters.put(newProj, shooter);
/* 199 */             this.bounce1.add(newProj);
/*     */ 
/* 201 */             event.setProjectile(newProj);
/*     */           }
/*     */           else
/*     */           {
/* 205 */             shooter.sendMessage(ChatColor.RED + "You must destroy your sign to register a hit.");
/*     */           }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private Vector getReflectedVector(Projectile oldProjectile, Vector oldVelocity)
/*     */   {
/* 216 */     Vector newVector = oldVelocity.clone();
/*     */ 
/* 218 */     newVector.setY(-newVector.getY());
/*     */ 
/* 220 */     Vector normalized = oldVelocity.normalize();
/*     */ 
/* 222 */     double x = normalized.getX(); double z = normalized.getZ();
/*     */ 
/* 224 */     Block loc = oldProjectile.getLocation().getBlock();
/*     */ 
/* 226 */     if ((x > 0.0D) && (loc.getRelative(BlockFace.SOUTH).getType() != Material.AIR))
/* 227 */       newVector.setX(-newVector.getX());
/* 228 */     else if ((x < 0.0D) && (loc.getRelative(BlockFace.NORTH).getType() != Material.AIR)) {
/* 229 */       newVector.setX(-newVector.getX());
/*     */     }
/* 231 */     if ((z > 0.0D) && (loc.getRelative(BlockFace.WEST).getType() != Material.AIR))
/* 232 */       newVector.setZ(-newVector.getZ());
/* 233 */     else if ((z < 0.0D) && (loc.getRelative(BlockFace.EAST).getType() != Material.AIR)) {
/* 234 */       newVector.setZ(-newVector.getZ());
/*     */     }
/* 236 */     return newVector;
/*     */   }
/*     */ 
/*     */   private Snowball makeNewBall(Projectile oldProjectile, Vector newVector)
/*     */   {
/* 243 */     World world = oldProjectile.getWorld();
/*     */ 
/* 245 */     Snowball ball = (Snowball)world.spawn(oldProjectile.getLocation(), Snowball.class);
/*     */ 
/* 247 */     ball.setVelocity(newVector);
/* 248 */     ball.setBounce(true);
/*     */ 
/* 250 */     return ball;
/*     */   }
/*     */ 
/*     */   private void onProjectileHit_Func(ProjectileHitEvent event)
/*     */   {
/* 260 */     Player shooter = null;
/*     */ 
/* 262 */     if ((event.getEntity() instanceof Snowball)) {
/* 263 */       Snowball proj = (Snowball)event.getEntity();
/* 264 */       Snowball newBall = null;
/* 265 */       Integer bounceInt = Integer.valueOf(this.plugin.getConfig().getInt("number-of-bounces"));
/*     */ 
/* 267 */       if ((this.bounce1.size() > 0) && (this.bounce1.contains(proj))) {
/* 268 */         this.bounce1.remove(proj);
/* 269 */         if (!bounce.containsKey(shooter)) {
/* 270 */           bounce.put(shooter, Integer.valueOf(0));
/*     */         }
/* 272 */         Integer bounceMap = (Integer)bounce.get(shooter);
/* 273 */         if (bounceMap.intValue() <= bounceInt.intValue()) {
/* 274 */           newBall = makeNewBall(proj, getReflectedVector(proj, proj.getVelocity().multiply(0.5D)));
/* 275 */           this.bounce1.add(newBall);
/* 276 */           bounce.put(shooter, Integer.valueOf(bounceMap.intValue() + 1));
/*     */         }
/*     */         else {
/* 279 */           bounce.remove(shooter);
/*     */         }
/*     */       }
/*     */ 
/* 283 */       if (snowballShooters.containsKey(proj)) {
/* 284 */         shooter = (Player)snowballShooters.get(proj);
/* 285 */         snowballShooters.remove(proj);
/*     */ 
/* 287 */         if (newBall != null) {
/* 288 */           if (newBall.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 289 */             newBall.setVelocity(newBall.getVelocity().multiply(0.05D));
/*     */           }
/* 291 */           if (newBall.getLocation().getBlock().getType() == Material.WATER) {
/* 292 */             newBall.setVelocity(newBall.getVelocity().multiply(0.2D));
/*     */           }
/* 294 */           if (newBall.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.LEAVES) {
/* 295 */             newBall.setVelocity(newBall.getVelocity().multiply(0.1D));
/*     */           }
/* 297 */           if (newBall.getLocation().getBlock().getType() == Material.LAVA) {
/* 298 */             newBall.setVelocity(newBall.getVelocity().multiply(0.01D));
/*     */           }
/* 300 */           if (newBall.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
/* 301 */             newBall.setVelocity(newBall.getVelocity().multiply(0.7D));
/*     */           }
/* 303 */           snowballShooters.put(newBall, shooter);
/*     */ 
/* 305 */           return;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 311 */     if (shooter == null) {
/* 312 */       return;
/*     */     }
/* 314 */     boolean hazard = false;
/* 315 */     boolean createSign = false;
/* 316 */     boolean inHole = false;
/* 317 */     Block block = event.getEntity().getLocation().getBlock().getRelative(BlockFace.DOWN);
/* 318 */     String playersMessage = null;
/* 319 */     String scoreString = null;
/* 320 */     String shooterName = shooter.getName();
/*     */ 
/* 327 */     while ((block.getType() == Material.AIR) || (block.getType() == Material.FENCE)) {
/* 328 */       block = block.getRelative(BlockFace.DOWN);
/*     */     }
/*     */ 
/* 332 */     if (block.getType() != Material.GLOWSTONE) {
/* 333 */       while ((block.getType() == Material.FENCE) || (block.getType() == Material.FENCE)) {
/* 334 */         block = block.getRelative(BlockFace.UP);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 340 */     if (!GcCommands.Golfing.containsKey(shooter))
/*     */     {
/* 342 */       return;
/*     */     }
/* 344 */     if (!((Boolean)GcCommands.Golfing.get(shooter)).booleanValue())
/*     */     {
/* 346 */       return;
/*     */     }
/*     */ 
/* 349 */     boolean Continue = false;
/*     */ 
/* 351 */     if (finishedHole.containsKey(shooter)) {
/* 352 */       if (!((Boolean)finishedHole.get(shooter)).booleanValue())
/* 353 */         Continue = true;
/*     */     }
/*     */     else
/*     */     {
/* 357 */       Continue = true;
/* 358 */       finishedHole.put(shooter, Boolean.valueOf(false));
/*     */     }
/*     */     int parA;
/*     */     int scoreFinal;
/* 361 */     if (Continue) {
/* 362 */       int dist = (int)event.getEntity().getLocation().distance(shooter.getLocation());
/*     */ 
/* 364 */       if ((block.getRelative(BlockFace.UP).getType() == Material.WATER) || (block.getRelative(BlockFace.UP).getType() == Material.STATIONARY_WATER) || (block.getType() == Material.WATER) || (block.getType() == Material.STATIONARY_WATER)) {
/* 365 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.RED + " just landed in a water hazard!";
/* 366 */         hazard = true;
/*     */       }
/* 368 */       if ((block.getRelative(BlockFace.UP).getType() == Material.LAVA) || (block.getRelative(BlockFace.UP).getType() == Material.STATIONARY_LAVA) || (block.getType() == Material.LAVA) || (block.getType() == Material.STATIONARY_LAVA)) {
/* 369 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.DARK_RED + " just landed in a lava hazard!";
/* 370 */         hazard = true;
/*     */       }
/* 372 */       if ((block.getType() == Material.SAND) && (!hazard)) {
/* 373 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.YELLOW + " just landed in a sand trap... " + ChatColor.GRAY + "(" + ChatColor.AQUA + dist + "m" + ChatColor.GRAY + ")";
/*     */       }
/* 375 */       if ((block.getType() == Material.GRASS) && (!hazard)) {
/* 376 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.GRAY + " just landed in the rough... " + ChatColor.GRAY + "(" + ChatColor.AQUA + dist + "m" + ChatColor.GRAY + ")";
/*     */       }
/* 378 */       if ((block.getType() == Material.WOOL) && (block.getData() == 13) && (!hazard)) {
/* 379 */         playersMessage = ChatColor.DARK_GREEN + shooterName + " just landed in the fairway! " + ChatColor.GRAY + "(" + ChatColor.AQUA + dist + "m" + ChatColor.GRAY + ")";
/*     */       }
/* 381 */       if ((block.getType() == Material.WOOL) && (block.getData() == 5) && (!hazard)) {
/* 382 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.GREEN + " just landed on the green. " + ChatColor.GRAY + "(" + ChatColor.AQUA + dist + "m" + ChatColor.GRAY + ")";
/*     */       }
/* 384 */       if ((block.getType() == Material.GLOWSTONE) && (!hazard))
/*     */       {
/* 386 */         if (GcCommands.playingHole.containsKey(shooter)) {
/* 387 */           GcHole existingHole = GcFiles.getHole(shooter.getWorld().getName(), (String)GcCommands.playingHole.get(shooter));
/* 388 */           int scoreA = ((Integer)score.get(shooter)).intValue();
/* 389 */           parA = existingHole.par;
/* 390 */           scoreFinal = scoreA - parA;
/*     */ 
/* 392 */           if (scoreA == 1) {
/* 393 */             scoreString = ChatColor.GOLD + "----" + shooter.getName() + " GOT A HOLE IN ONE----";
/*     */           }
/*     */           else {
/* 396 */             if (scoreFinal <= -3) {
/* 397 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.GOLD + "Double Eagle" + ChatColor.GRAY + ")";
/*     */             }
/* 399 */             if (scoreFinal == -2) {
/* 400 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.YELLOW + "Eagle" + ChatColor.GRAY + ")";
/*     */             }
/* 402 */             if (scoreFinal == -1) {
/* 403 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.GREEN + "Birdie" + ChatColor.GRAY + ")";
/*     */             }
/* 405 */             if (scoreFinal == 0) {
/* 406 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.AQUA + "Par" + ChatColor.GRAY + ")";
/*     */             }
/* 408 */             if (scoreFinal == 1) {
/* 409 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.YELLOW + "Bogey" + ChatColor.GRAY + ")";
/*     */             }
/* 411 */             if (scoreFinal == 2) {
/* 412 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.RED + "Double Bogey" + ChatColor.GRAY + ")";
/*     */             }
/* 414 */             if (scoreFinal >= 3) {
/* 415 */               scoreString = ChatColor.GRAY + "Score: " + scoreA + " (" + ChatColor.DARK_RED + "Triple Bogey" + ChatColor.GRAY + ")";
/*     */             }
/*     */           }
/*     */ 
/* 419 */           playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.GREEN + " just landed in the" + ChatColor.GOLD + " cup!";
/* 420 */           shooter.sendMessage(ChatColor.GREEN + "You have finished this hole! Start a new hole?");
/* 421 */           shooter.sendMessage(ChatColor.GRAY + "/golf play [hole]");
/* 422 */           finishedHole.put(shooter, Boolean.valueOf(true));
/* 423 */           inHole = true;
/*     */         }
/*     */       }
/*     */ 
/* 427 */       if ((block.getType() == Material.LEAVES) && (!hazard)) {
/* 428 */         playersMessage = ChatColor.DARK_GREEN + shooterName + ChatColor.RED + " just landed in a birds nest";
/* 429 */         hazard = true;
/*     */       }
/*     */ 
/* 432 */       Player[] list = Bukkit.getOnlinePlayers();
/* 433 */       for (Player players : list)
/*     */       {
/* 435 */         if ((GcCommands.playingHole.containsKey(players)) && 
/* 436 */           (((String)GcCommands.playingHole.get(players)).equals(GcCommands.playingHole.get(shooter)))) {
/* 437 */           if ((playersMessage == null) && (block.getType() != Material.GLOWSTONE)) {
/* 438 */             playersMessage = ChatColor.GRAY + "The ball has landed on " + block.getType().toString().toLowerCase().replace("_", " ") + ". " + ChatColor.GRAY + "(" + ChatColor.AQUA + dist + "m" + ChatColor.GRAY + ")";
/* 439 */             if (this.plugin.getConfig().getBoolean("unknow-blocks-are-hazards"))
/* 440 */               hazard = true;
/*     */           }
/* 442 */           players.sendMessage(playersMessage);
/* 443 */           if ((inHole) && (scoreString != null)) {
/* 444 */             players.sendMessage(scoreString);
/*     */           }
/* 446 */           createSign = true;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 453 */     if (inHole) {
/* 454 */       signExists.remove(shooter);
/* 455 */       signLocation.remove(shooter);
/* 456 */       GcBlockListener.signLocation.remove(shooter);
/*     */     }
/*     */ 
/* 460 */     if (hazard) {
/* 461 */       while (shooter.getInventory().contains(Material.ARROW)) {
/* 462 */         shooter.getInventory().remove(Material.ARROW);
/*     */       }
/* 464 */       ItemStack arrow = new ItemStack(Material.ARROW);
/* 465 */       arrow.setAmount(1);
/* 466 */       shooter.getInventory().addItem(new ItemStack[] { arrow });
/* 467 */       while (shooter.getInventory().contains(Material.BOW)) {
/* 468 */         shooter.getInventory().remove(Material.BOW);
/*     */       }
/* 470 */       ItemStack bow = new ItemStack(Material.BOW);
/* 471 */       arrow.setAmount(1);
/* 472 */       shooter.getInventory().addItem(new ItemStack[] { bow });
/* 473 */       score.put(shooter, Integer.valueOf(((Integer)score.get(shooter)).intValue() + 1));
/*     */     }
/*     */ 
/* 476 */     if ((createSign) && (!hazard) && (!inHole)) {
/* 477 */       Block up = block.getRelative(BlockFace.UP);
/* 478 */       up.setType(Material.SIGN_POST);
/* 479 */       up.getState().update(true);
/* 480 */       signExists.put(shooter, Boolean.valueOf(true));
/*     */ 
/* 482 */       Player[] list = Bukkit.getOnlinePlayers();
/*     */ 
/* 484 */       if ((up.getState() instanceof Sign)) {
/* 485 */         Sign sign = (Sign)up.getState();
/* 486 */         sign.setLine(0, ChatColor.WHITE + "   [" + ChatColor.DARK_GREEN + "Golf" + ChatColor.WHITE + "]");
/* 487 */         sign.setLine(1, ChatColor.DARK_GRAY + shooterName);
/* 488 */         sign.update(true);
/* 489 */         up.getState().update(true);
/* 490 */         signLocation.put(shooter, sign.getLocation());
/*     */       }
/*     */       else
/*     */       {
/* 495 */         for (Player players : list)
/* 496 */           players.sendMessage(ChatColor.RED + "Sign failed to generate!");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void onEntityTarget_Func(EntityTargetEvent event)
/*     */   {
/* 508 */     if (this.plugin.getConfig().getBoolean("kill-mobs-on-course")) {
/* 509 */       Entity mob = event.getEntity();
/* 510 */       int targetUUID = 0;
/*     */       try {
/* 512 */         targetUUID = event.getTarget().getEntityId();
/*     */       }
/*     */       catch (NullPointerException e) {
/* 515 */         return;
/*     */       }
/* 517 */       Player[] list = Bukkit.getOnlinePlayers();
/*     */ 
/* 519 */       for (Player players : list) {
/* 520 */         int player = players.getEntityId();
/* 521 */         if ((targetUUID == player) && 
/* 522 */           (GcCommands.Golfing.containsKey(players)) && 
/* 523 */           (((Boolean)GcCommands.Golfing.get(players)).booleanValue())) {
/* 524 */           Block block = players.getLocation().getBlock().getRelative(BlockFace.DOWN);
/* 525 */           Block block2 = block.getRelative(BlockFace.DOWN);
/* 526 */           Block block3 = block2.getRelative(BlockFace.DOWN);
/* 527 */           if (((block.getType() == Material.WOOL) && ((block.getData() == 13) || (block.getData() == 5)) && (!mob.isDead())) || ((block2.getType() == Material.WOOL) && ((block2.getData() == 13) || (block2.getData() == 5)) && (!mob.isDead())) || ((block3.getType() == Material.WOOL) && ((block3.getData() == 13) || (block3.getData() == 5)) && (!mob.isDead())))
/* 528 */             mob.remove();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcEntityListener
 * JD-Core Version:    0.6.2
 */