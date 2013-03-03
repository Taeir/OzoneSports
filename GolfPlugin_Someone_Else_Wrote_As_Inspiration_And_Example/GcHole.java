/*    */ 
/*    */ 
/*    */ import org.apache.commons.lang.builder.EqualsBuilder;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class GcHole
/*    */ {
/*    */   public World world;
/*    */   public String hole;
/*    */   public int par;
/*    */   public int x;
/*    */   public int y;
/*    */   public int z;
/*    */ 
/*    */   public GcHole(String worldName, String hole, int par)
/*    */   {
/* 20 */     this(Bukkit.getWorld(worldName), hole, par, 0, 0, 0);
/*    */   }
/*    */ 
/*    */   public GcHole(String worldName, String hole, int par, int x, int y, int z)
/*    */   {
/* 25 */     this(Bukkit.getWorld(worldName), hole, par, x, y, z);
/*    */   }
/*    */ 
/*    */   public GcHole(String worldName, String hole, int par, Block block)
/*    */   {
/* 30 */     this(Bukkit.getWorld(worldName), hole, par, block.getX(), block.getY(), block.getZ());
/*    */   }
/*    */ 
/*    */   public GcHole(World world, String hole, int par, int x, int y, int z)
/*    */   {
/* 35 */     this.world = world;
/* 36 */     this.hole = hole;
/* 37 */     this.par = par;
/* 38 */     this.x = x;
/* 39 */     this.y = y;
/* 40 */     this.z = z;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 45 */     return this.world.getName() + ";" + this.hole + ";" + this.par;
/*    */   }
/*    */ 
/*    */   public Block getBlock()
/*    */   {
/* 50 */     return this.world.getBlockAt(this.x, this.y, this.z);
/*    */   }
/*    */ 
/*    */   public boolean equals(Object obj)
/*    */   {
/* 56 */     if (obj == null) {
/* 57 */       return false;
/*    */     }
/* 59 */     if (obj == this) {
/* 60 */       return true;
/*    */     }
/* 62 */     if (obj.getClass() != getClass()) {
/* 63 */       return false;
/*    */     }
/* 65 */     GcHole otherHole = (GcHole)obj;
/*    */ 
/* 67 */     return new EqualsBuilder().append(this.world, otherHole.world).append(this.hole, otherHole.hole).isEquals();
/*    */   }
/*    */ }

/* Location:           C:\Users\Taico\Desktop\Golfcraft.jar
 * Qualified Name:     musaddict.golfcraft.GcHole
 * JD-Core Version:    0.6.2
 */