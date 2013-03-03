/*     */ 
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.logging.Level;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
/*     */ import org.xml.sax.SAXException;
/*     */ 
/*     */ public class GcFiles
/*     */ {
/*  33 */   private static File HoleFile = new File("plugins/Golfcraft" + File.separator + "Holes.xml");
/*     */   private static ArrayList<GcHole> HoleList;
/*     */ 
/*     */   @SuppressWarnings({ "unchecked", "rawtypes", "unused" })
public static void load()
/*     */   {
/*  42 */     Golfcraft.Log(Level.INFO, "Loading Holes...");
/*     */ 
/*  44 */     if (HoleList == null) {
/*  45 */       HoleList = new ArrayList();
/*     */     }
/*  47 */     if (!HoleFile.exists()) {
/*  48 */       Golfcraft.Log("No Holes to load.");
/*     */     } else {
/*  50 */       HoleList = new ArrayList();
/*  51 */       DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
/*  52 */       DocumentBuilder docBuilder = null;
/*     */       try {
/*  54 */         docBuilder = dbfac.newDocumentBuilder();
/*     */       } catch (ParserConfigurationException localParserConfigurationException) {
/*     */       }
/*  57 */       Document doc = null;
/*     */       try
/*     */       {
/*  60 */         doc = docBuilder.parse(HoleFile);
/*  61 */         doc.getDocumentElement().normalize();
/*     */       }
/*     */       catch (IOException|SAXException e) {
/*  64 */         Golfcraft.Log(Level.SEVERE, "Holes file is malformed.");
/*     */ 
/*  66 */         e.printStackTrace();
/*     */ 
/*  68 */         return;
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/*  73 */         Double fileVersion = Double.valueOf(-1.0D);
/*     */ 
/*  75 */         NodeList fileVersionNodeList = doc.getElementsByTagName("file");
/*     */ 
/*  77 */         if (fileVersionNodeList.getLength() > 0) {
/*  78 */           for (int i = 0; i < fileVersionNodeList.getLength(); i++) {
/*  79 */             Node fileVersionNode = fileVersionNodeList.item(i);
/*     */ 
/*  81 */             if (fileVersionNode.getNodeType() == 1) {
/*  82 */               Element fileVersionElement = (Element)fileVersionNode;
/*     */ 
/*  84 */               fileVersion = Double.valueOf(Double.parseDouble(fileVersionElement.getAttribute("version")));
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/*  89 */           fileVersion = Double.valueOf(1.0D);
/*     */         }
/*     */ 
/*  92 */         NodeList courseNodeList = doc.getElementsByTagName("hole");
/*     */ 
/*  94 */         for (int i = 0; i < courseNodeList.getLength(); i++) {
/*  95 */           Node doorNode = courseNodeList.item(i);
/*     */ 
/*  97 */           if (doorNode.getNodeType() == 1) {
/*  98 */             Element doorElement = (Element)doorNode;
/*     */ 
/* 100 */             World world = Bukkit.getWorld(doorElement.getAttribute("world"));
/* 101 */             String hole = doorElement.getAttribute("hole");
/* 102 */             int par = Integer.parseInt(doorElement.getAttribute("par"));
/* 103 */             int x = Integer.parseInt(doorElement.getAttribute("x")); int y = Integer.parseInt(doorElement.getAttribute("y")); int z = Integer.parseInt(doorElement.getAttribute("z"));
/*     */ 
/* 105 */             HoleList.add(new GcHole(world, hole, par, x, y, z));
/*     */           }
/*     */         }
/*     */       }
/*     */       catch (Exception e) {
/* 110 */         Golfcraft.Log(Level.SEVERE, "Holes file is not in the expected format.");
/*     */ 
/* 112 */         e.printStackTrace();
/*     */ 
/* 114 */         return;
/*     */       }
/*     */ 
/* 117 */       Golfcraft.Log(Level.INFO, "Holes loaded successfully.");
/* 118 */       Golfcraft.Log(Level.INFO, "Loaded " + HoleList.size() + " holes.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public static boolean save()
/*     */   {
/* 124 */     if (HoleList.size() == 0) {
/* 125 */       HoleFile.delete();
/*     */ 
/* 127 */       Golfcraft.Log(Level.INFO, "No holes to save.");
/*     */ 
/* 129 */       return true;
/*     */     }
/*     */ 
/* 132 */     Golfcraft.Log(Level.INFO, "Saving " + HoleList.size() + " holes...");
/*     */     try
/*     */     {
/* 135 */       if ((!HoleFile.exists()) && 
/* 136 */         (!HoleFile.createNewFile())) {
/* 137 */         Golfcraft.Log(Level.SEVERE, "Error creating Holes file.");
/*     */ 
/* 139 */         return false;
/*     */       }
/*     */ 
/* 143 */       DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
/* 144 */       DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
/* 145 */       Document doc = docBuilder.newDocument();
/*     */ 
/* 147 */       Element fileVersionElement = doc.createElement("file");
/* 148 */       fileVersionElement.setAttribute("version", "1.0");
/* 149 */       doc.appendChild(fileVersionElement);
/*     */ 
/* 151 */       for (GcHole hole : HoleList) {
/* 152 */         Element doorElement = doc.createElement("hole");
/* 153 */         doorElement.setAttribute("world", hole.world.getName());
/* 154 */         doorElement.setAttribute("hole", hole.hole);
/* 155 */         doorElement.setAttribute("par", hole.par + "");
/* 156 */         doorElement.setAttribute("x", hole.x + "");
/* 157 */         doorElement.setAttribute("y", hole.y + "");
/* 158 */         doorElement.setAttribute("z", hole.z + "");
/* 159 */         fileVersionElement.appendChild(doorElement);
/*     */       }
/*     */ 
/* 162 */       TransformerFactory transfac = TransformerFactory.newInstance();
/* 163 */       Transformer trans = transfac.newTransformer();
/*     */ 
/* 165 */       trans.setOutputProperty("encoding", "UTF-8");
/* 166 */       trans.setOutputProperty("indent", "yes");
/*     */ 
/* 168 */       StreamResult result = new StreamResult(new StringWriter());
/* 169 */       DOMSource source = new DOMSource(doc);
/* 170 */       trans.transform(source, result);
/*     */ 
/* 172 */       FileOutputStream OUT = new FileOutputStream(HoleFile);
/* 173 */       OUT.write(result.getWriter().toString().getBytes());
/* 174 */       OUT.flush();
/* 175 */       OUT.close();
/*     */ 
/* 177 */       Golfcraft.Log(Level.INFO, "Holes Saved Successfully.");
/*     */ 
/* 179 */       return true;
/*     */     }
/*     */     catch (Exception e) {
/* 182 */       Golfcraft.Log(Level.SEVERE, "Unknown error saving Holes.");
/*     */ 
/* 184 */       e.printStackTrace();
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */ 
/*     */   public static GcHole getHole(String worldName, String holeName)
/*     */   {
/* 197 */     return getHole(new GcHole(worldName, holeName, 0));
/*     */   }
/*     */ 
/*     */   public static GcHole getHole(GcHole partial)
/*     */   {
/* 203 */     for (GcHole hole : HoleList)
/* 204 */       if (hole.equals(partial))
/* 205 */         return hole;
/* 206 */     return null;
/*     */   }
/*     */ 
/*     */   public static boolean addHole(GcHole Hole)
/*     */   {
/* 211 */     if (!HoleList.contains(Hole))
/* 212 */       HoleList.add(Hole);
/*     */     else {
/* 214 */       return false;
/*     */     }
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */   public static boolean holeExists(Player player, String hole)
/*     */   {
/* 221 */     return getHole(player.getWorld().getName(), hole) != null;
/*     */   }
/*     */ 
/*     */   public static boolean holeExists(Player player, String hole, int par)
/*     */   {
/* 226 */     GcHole Hole = new GcHole(player.getWorld().getName(), hole, par);
/*     */ 
/* 228 */     if (HoleList.contains(Hole)) {
/* 229 */       return true;
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */ }