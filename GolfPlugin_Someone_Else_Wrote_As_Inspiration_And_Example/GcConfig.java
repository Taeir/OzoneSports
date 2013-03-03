/*    */ 
/*    */ 
/*    */ import org.bukkit.configuration.file.FileConfiguration;
/*    */ 
/*    */ public class GcConfig
/*    */ {
/*    */   static Golfcraft plugin;
/*    */ 
/*    */   public static int sandChance()
/*    */   {
/* 10 */     int Min = plugin.getConfig().getInt("sandtrap-min-percent-decrease");
/* 11 */     int Max = plugin.getConfig().getInt("sandtrap-max-percent-decrease");
/* 12 */     if (Max < 1)
/* 13 */       Max = 1;
/* 14 */     if (Min < 0)
/* 15 */       Min = 0;
/* 16 */     if (Min >= Max)
/* 17 */       Min = Max - 1;
/* 18 */     int Variation = Max - Min;
/* 19 */     return Variation;
/*    */   }
/*    */ 
/*    */   public static int sandBase()
/*    */   {
/* 24 */     int Max = plugin.getConfig().getInt("sandtrap-max-percent-decrease");
/* 25 */     if (Max < 0)
/* 26 */       Max = 0;
/* 27 */     if (Max > 99)
/* 28 */       Max = 99;
/* 29 */     int Base = 100 - Max;
/* 30 */     return Base;
/*    */   }
/*    */ 
/*    */   public static int roughChance()
/*    */   {
/* 35 */     int Min = plugin.getConfig().getInt("rough-min-percent-decrease");
/* 36 */     int Max = plugin.getConfig().getInt("rough-max-percent-decrease");
/* 37 */     if (Max < 1)
/* 38 */       Max = 1;
/* 39 */     if (Min < 0)
/* 40 */       Min = 0;
/* 41 */     if (Min >= Max)
/* 42 */       Min = Max - 1;
/* 43 */     int Variation = Max - Min;
/* 44 */     return Variation;
/*    */   }
/*    */ 
/*    */   public static int roughBase()
/*    */   {
/* 49 */     int Min = plugin.getConfig().getInt("rough-min-percent-decrease");
/* 50 */     if (Min < 0)
/* 51 */       Min = 0;
/* 52 */     if (Min > 99)
/* 53 */       Min = 99;
/* 54 */     int Base = 100 - Min;
/* 55 */     return Base;
/*    */   }
/*    */ 
/*    */   public static int teeingGroundChance()
/*    */   {
/* 60 */     int Min = plugin.getConfig().getInt("teeing-ground-min-percent-increase");
/* 61 */     int Max = plugin.getConfig().getInt("teeing-ground-max-percent-increase");
/* 62 */     if (Max < 1)
/* 63 */       Max = 1;
/* 64 */     if (Min < 0)
/* 65 */       Min = 0;
/* 66 */     if (Min >= Max)
/* 67 */       Min = Max - 1;
/* 68 */     int Variation = Max - Min;
/* 69 */     return Variation;
/*    */   }
/*    */ 
/*    */   public static int teeingGroundBase()
/*    */   {
/* 74 */     int Min = plugin.getConfig().getInt("teeing-ground-min-percent-decrease");
/* 75 */     if (Min < 0)
/* 76 */       Min = 0;
/* 77 */     if (Min > 99)
/* 78 */       Min = 99;
/* 79 */     int Base = 100 - Min;
/* 80 */     return Base;
/*    */   }
/*    */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcConfig
 * JD-Core Version:    0.6.2
 */