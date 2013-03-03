/*     */ 
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ 
/*     */ public class GcClubs
/*     */ {
/*  14 */   public static HashMap<Player, Integer> Club = new HashMap();
/*  15 */   public static HashMap<Player, Float> Force = new HashMap();
/*     */ 
/*     */   public static String getClubMessage(Player player) {
/*  18 */     int club = ((Integer)Club.get(player)).intValue();
/*  19 */     String message = "Unknown club";
/*  20 */     if (club == 0) {
/*  21 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Driver (1 wood)" + ChatColor.GRAY + "]";
/*     */     }
/*  23 */     if (club == 1) {
/*  24 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "3 wood" + ChatColor.GRAY + "]";
/*     */     }
/*  26 */     if (club == 2) {
/*  27 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "5 wood" + ChatColor.GRAY + "]";
/*     */     }
/*  29 */     if (club == 3) {
/*  30 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "4 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  32 */     if (club == 4) {
/*  33 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "5 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  35 */     if (club == 5) {
/*  36 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "6 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  38 */     if (club == 6) {
/*  39 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "7 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  41 */     if (club == 7) {
/*  42 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "8 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  44 */     if (club == 8) {
/*  45 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "9 iron" + ChatColor.GRAY + "]";
/*     */     }
/*  47 */     if (club == 9) {
/*  48 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Pitching Wedge" + ChatColor.GRAY + "]";
/*     */     }
/*  50 */     if (club == 10) {
/*  51 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Sand Wedge" + ChatColor.GRAY + "]";
/*     */     }
/*  53 */     if (club == 11) {
/*  54 */       message = ChatColor.GRAY + "[" + ChatColor.GOLD + "Putter" + ChatColor.GRAY + "]";
/*     */     }
/*  56 */     return message;
/*     */   }
/*     */ 
/*     */   public static String getClubMessage2(Player player) {
/*  60 */     int club = ((Integer)Club.get(player)).intValue();
/*  61 */     String message = "Unknown club";
/*  62 */     if (club == 0) {
/*  63 */       message = ChatColor.GRAY + "Driver (1 wood)";
/*     */     }
/*  65 */     if (club == 1) {
/*  66 */       message = ChatColor.GRAY + "3 wood";
/*     */     }
/*  68 */     if (club == 2) {
/*  69 */       message = ChatColor.GRAY + "5 wood";
/*     */     }
/*  71 */     if (club == 3) {
/*  72 */       message = ChatColor.GRAY + "4 iron";
/*     */     }
/*  74 */     if (club == 4) {
/*  75 */       message = ChatColor.GRAY + "5 iron";
/*     */     }
/*  77 */     if (club == 5) {
/*  78 */       message = ChatColor.GRAY + "6 iron";
/*     */     }
/*  80 */     if (club == 6) {
/*  81 */       message = ChatColor.GRAY + "7 iron";
/*     */     }
/*  83 */     if (club == 7) {
/*  84 */       message = ChatColor.GRAY + "8 iron";
/*     */     }
/*  86 */     if (club == 8) {
/*  87 */       message = ChatColor.GRAY + "9 iron";
/*     */     }
/*  89 */     if (club == 9) {
/*  90 */       message = ChatColor.GRAY + "Pitching Wedge";
/*     */     }
/*  92 */     if (club == 10) {
/*  93 */       message = ChatColor.GRAY + "Sand Wedge";
/*     */     }
/*  95 */     if (club == 11) {
/*  96 */       message = ChatColor.GRAY + "Putter";
/*     */     }
/*  98 */     return message;
/*     */   }
/*     */ 
/*     */   public static Double getClubSpeed(Player player) {
/* 102 */     int club = ((Integer)Club.get(player)).intValue();
/* 103 */     if (club == 0) {
/* 104 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
/* 105 */         return Double.valueOf(0.9D);
/*     */       }
/* 107 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 108 */         return Double.valueOf(0.35D);
/*     */       }
/* 110 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
/* 111 */         return Double.valueOf(1.63D);
/*     */       }
/* 113 */       return Double.valueOf(1.25D);
/*     */     }
/* 115 */     if (club == 1) {
/* 116 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
/* 117 */         return Double.valueOf(0.86D);
/*     */       }
/* 119 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 120 */         return Double.valueOf(0.35D);
/*     */       }
/* 122 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
/* 123 */         return Double.valueOf(1.49D);
/*     */       }
/* 125 */       return Double.valueOf(1.2D);
/*     */     }
/* 127 */     if (club == 2) {
/* 128 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 129 */         return Double.valueOf(0.35D);
/*     */       }
/* 131 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.WOOD) {
/* 132 */         return Double.valueOf(1.35D);
/*     */       }
/* 134 */       return Double.valueOf(1.15D);
/*     */     }
/* 136 */     if (club == 3) {
/* 137 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 138 */         return Double.valueOf(0.6D);
/*     */       }
/* 140 */       return Double.valueOf(1.24D);
/*     */     }
/* 142 */     if (club == 4) {
/* 143 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 144 */         return Double.valueOf(0.6D);
/*     */       }
/* 146 */       return Double.valueOf(1.18D);
/*     */     }
/* 148 */     if (club == 5) {
/* 149 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 150 */         return Double.valueOf(0.6D);
/*     */       }
/* 152 */       return Double.valueOf(1.12D);
/*     */     }
/* 154 */     if (club == 6) {
/* 155 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 156 */         return Double.valueOf(0.6D);
/*     */       }
/* 158 */       return Double.valueOf(1.06D);
/*     */     }
/* 160 */     if (club == 7) {
/* 161 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 162 */         return Double.valueOf(0.6D);
/*     */       }
/* 164 */       return Double.valueOf(1.0D);
/*     */     }
/* 166 */     if (club == 8) {
/* 167 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 168 */         return Double.valueOf(0.6D);
/*     */       }
/* 170 */       return Double.valueOf(0.9300000000000001D);
/*     */     }
/* 172 */     if (club == 9) {
/* 173 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 174 */         return Double.valueOf(0.6D);
/*     */       }
/* 176 */       return Double.valueOf(0.85D);
/*     */     }
/* 178 */     if (club == 10) {
/* 179 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 180 */         return Double.valueOf(0.7D);
/*     */       }
/* 182 */       return Double.valueOf(0.6D);
/*     */     }
/* 184 */     if (club == 11) {
/* 185 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.GRASS) {
/* 186 */         return Double.valueOf(0.3D);
/*     */       }
/* 188 */       if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SAND) {
/* 189 */         return Double.valueOf(0.2D);
/*     */       }
/* 191 */       return Double.valueOf(0.45D);
/*     */     }
/* 193 */     return Double.valueOf(1.0D);
/*     */   }
/*     */ 
/*     */   public static Float getClubPitch(Player player) {
/* 197 */     int club = ((Integer)Club.get(player)).intValue();
/* 198 */     float force = ((Float)Force.get(player)).floatValue();
/* 199 */     if (club == 0) {
/* 200 */       return Float.valueOf((float)(1.6D * force));
/*     */     }
/* 202 */     if (club == 1) {
/* 203 */       return Float.valueOf((float)(1.75D * force));
/*     */     }
/* 205 */     if (club == 2) {
/* 206 */       return Float.valueOf(2.0F * force);
/*     */     }
/* 208 */     if (club == 3) {
/* 209 */       return Float.valueOf((float)(2.1D * force));
/*     */     }
/* 211 */     if (club == 4) {
/* 212 */       return Float.valueOf((float)(2.13D * force));
/*     */     }
/* 214 */     if (club == 5) {
/* 215 */       return Float.valueOf((float)(2.16D * force));
/*     */     }
/* 217 */     if (club == 6) {
/* 218 */       return Float.valueOf((float)(2.2D * force));
/*     */     }
/* 220 */     if (club == 7) {
/* 221 */       return Float.valueOf((float)(2.23D * force));
/*     */     }
/* 223 */     if (club == 8) {
/* 224 */       return Float.valueOf((float)(2.26D * force));
/*     */     }
/* 226 */     if (club == 9) {
/* 227 */       return Float.valueOf((float)(2.44D * force));
/*     */     }
/* 229 */     if (club == 10) {
/* 230 */       return Float.valueOf((float)(2.44D * force));
/*     */     }
/* 232 */     if (club == 11) {
/* 233 */       return Float.valueOf((float)(2.5D * force));
/*     */     }
/* 235 */     return Float.valueOf((float)(1.57D * force));
/*     */   }
/*     */ 
/*     */   public static String getEfficiencyMessage(Player player) {
/* 239 */     int club = ((Integer)Club.get(player)).intValue();
/* 240 */     String message = "";
/* 241 */     Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
/* 242 */     if (club == 0) {
/* 243 */       if (block.getType() == Material.GRASS) {
/* 244 */         message = ChatColor.GRAY + "Your driver was less efficient in the rough.";
/*     */       }
/* 246 */       if (block.getType() == Material.SAND) {
/* 247 */         message = ChatColor.GRAY + "Your driver was almost useless in the sand.";
/*     */       }
/* 249 */       if ((block.getType() == Material.WOOL) && (block.getData() == 13)) {
/* 250 */         message = ChatColor.GRAY + "Your driver lost momentum on the fairway.";
/*     */       }
/*     */     }
/* 253 */     if ((club >= 1) && (club <= 9) && 
/* 254 */       (block.getType() == Material.SAND)) {
/* 255 */       message = ChatColor.GRAY + "Your " + getClubMessage2(player).toLowerCase() + " was less efficient in the sand.";
/*     */     }
/*     */ 
/* 258 */     if ((club == 10) && 
/* 259 */       (block.getType() == Material.SAND)) {
/* 260 */       message = ChatColor.GRAY + "Your " + getClubMessage2(player).toLowerCase() + " was more efficient in the sand!";
/*     */     }
/*     */ 
/* 263 */     if (club == 11) {
/* 264 */       if (block.getType() == Material.GRASS) {
/* 265 */         message = ChatColor.GRAY + "Your putter was almost useless in the grass.";
/*     */       }
/* 267 */       if (block.getType() == Material.SAND) {
/* 268 */         message = ChatColor.GRAY + "Your driver was useless in the sand.";
/*     */       }
/*     */     }
/* 271 */     return message;
/*     */   }
/*     */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcClubs
 * JD-Core Version:    0.6.2
 */