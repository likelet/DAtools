/*    */ package DOGProjectGenerator;
/*    */ 
import java.awt.geom.Rectangle2D;
/*    */ import java.awt.geom.Rectangle2D.Double;
/*    */ 
/*    */ public class SitePosition
/*    */ {
/*    */   public Rectangle2D.Double rec;
/*    */   public int sitePos;
/*    */ 
/*    */   public int getSitePos()
/*    */   {
/* 21 */     return this.sitePos;
/*    */   }
/*    */ 
/*    */   public void setSitePos(int sitePos) {
/* 25 */     this.sitePos = sitePos;
/*    */   }
/*    */ 
/*    */   public SitePosition(Rectangle2D.Double rec, int site) {
/* 29 */     this.rec = rec;
/* 30 */     this.sitePos = site;
/*    */   }
/*    */ 
/*    */   public Rectangle2D.Double getRec()
/*    */   {
/* 41 */     return this.rec;
/*    */   }
/*    */ 
/*    */   public void setRec(Rectangle2D.Double rec) {
/* 45 */     this.rec = rec;
/*    */   }
/*    */ }

/* Location:           F:\resouces\projects\DOG\测试\DOG3TEST20140331\DOG3TEST\DOG3.jar
 * Qualified Name:     gui.dog3.SitePosition
 * JD-Core Version:    0.6.0
 */