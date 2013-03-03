/*    */ 
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.HashMap;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.command.PluginCommand;
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ import org.bukkit.configuration.file.FileConfigurationOptions;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ import org.bukkit.plugin.PluginManager;
/*    */ import org.bukkit.plugin.java.JavaPlugin;
/*    */ 
/*    */ public class Golfcraft extends JavaPlugin
/*    */ {
/*    */   public static PluginDescriptionFile info;
/* 20 */   static Logger logger = Logger.getLogger("Minecraft");
/*    */   public static final String mainDirectory = "plugins/Golfcraft";
/*    */ 
/*    */   public void onEnable()
/*    */   {
/* 26 */     info = getDescription();
/* 27 */     logger = Logger.getLogger("Minecraft");
/*    */ 
/* 29 */     Log(Level.INFO, "is enabled, version: " + info.getVersion());
/* 30 */     Log(Level.INFO, "written by [Musaddict]");
/*    */ 
/* 32 */     new File("plugins/Golfcraft").mkdir();
/*    */ 
/* 35 */     getConfig().options().copyDefaults(true);
/* 36 */     saveConfig();
/*    */ 
/* 38 */     GcFiles.load();
/*    */ 
/* 40 */     GcConfig.plugin = this;
/* 41 */     getCommand("golf").setExecutor(new GcCommands());
/* 42 */     getServer().getPluginManager().registerEvents(new GcSigns(this), this);
/* 43 */     getServer().getPluginManager().registerEvents(new GcPlayerListener(this), this);
/* 44 */     getServer().getPluginManager().registerEvents(new GcBlockListener(), this);
/* 45 */     getServer().getPluginManager().registerEvents(new GcEntityListener(this), this);
/*    */   }
/*    */ 
/*    */   public void onDisable()
/*    */   {
/* 51 */     GcConfig.plugin = null;
/* 52 */     GcFiles.save();
/* 53 */     Player[] list = Bukkit.getOnlinePlayers();
/* 54 */     for (Player players : list) {
/* 55 */       if (GcEntityListener.signLocation.containsKey(players)) {
/* 56 */         Block block = ((Location)GcEntityListener.signLocation.get(players)).getBlock();
/* 57 */         block.setType(Material.AIR);
/* 58 */         GcEntityListener.signLocation.remove(players);
/*    */       }
/* 60 */       if (GcEntityListener.signExists.containsKey(players)) {
/* 61 */         GcEntityListener.signExists.remove(players);
/*    */       }
/* 63 */       if (GcBlockListener.signLocation.containsKey(players)) {
/* 64 */         Block block = ((Location)GcBlockListener.signLocation.get(players)).getBlock();
/* 65 */         block.setType(Material.AIR);
/* 66 */         GcBlockListener.signLocation.remove(players);
/*    */       }
/* 68 */       if (GcEntityListener.score.containsKey(players)) {
/* 69 */         GcEntityListener.score.remove(players);
/*    */       }
/*    */     }
/* 72 */     Log(Level.INFO, "was successfully disabled.");
/*    */   }
/*    */ 
/*    */   public static void Log(String message)
/*    */   {
/* 77 */     Log(Level.INFO, message);
/*    */   }
/*    */ 
/*    */   public static void Log(Level logLevel, String message)
/*    */   {
/* 82 */     logger.log(logLevel, "[" + info.getName() + "] " + message);
/*    */   }
/*    */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.Golfcraft
 * JD-Core Version:    0.6.2
 */