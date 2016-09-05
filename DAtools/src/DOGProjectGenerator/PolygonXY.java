/*     */ package DOGProjectGenerator;
/*     */ 
/*     */ public class PolygonXY
/*     */ {
/*     */   public int[] x;
/*     */   public int[] y;
/*     */   public int pointcount;
/*  20 */   public int minx = 0; public int miny = 0; public int maxx = 0; public int maxy = 0;
/*  21 */   public int bnewminx = 0; public int bnewminy = 0; public int bnewmaxx = 0; public int bnewmaxy = 0;
/*  22 */   public double[] startax = new double[4];
/*  23 */   public double[] startay = new double[4];
/*  24 */   public double[] endax = new double[4];
/*  25 */   public double[] enday = new double[4];
/*  26 */   public double centerx = 0.0D;
/*  27 */   public double centery = 0.0D;
/*  28 */   public double iwide = 0.0D;
/*  29 */   public double ihigh = 0.0D;
/*  30 */   public double[] startcx = new double[4];
/*  31 */   public double[] startcy = new double[4];
/*  32 */   public double[] endcx = new double[4];
/*  33 */   public double[] endcy = new double[4];
/*     */   public double angle;
/*     */ 
/*     */   public double[] getEndax()
/*     */   {
/*  37 */     return this.endax;
/*     */   }
/*     */ 
/*     */   public void setEndax(double[] endax) {
/*  41 */     this.endax = endax;
/*     */   }
/*     */ 
/*     */   public double[] getEnday() {
/*  45 */     return this.enday;
/*     */   }
/*     */ 
/*     */   public void setEnday(double[] enday) {
/*  49 */     this.enday = enday;
/*     */   }
/*     */ 
/*     */   public double[] getStartax() {
/*  53 */     return this.startax;
/*     */   }
/*     */ 
/*     */   public void setStartax(double[] startax) {
/*  57 */     this.startax = startax;
/*     */   }
/*     */ 
/*     */   public double[] getStartay() {
/*  61 */     return this.startay;
/*     */   }
/*     */ 
/*     */   public void setStartay(double[] startay) {
/*  65 */     this.startay = startay;
/*     */   }
/*     */ 
/*     */   public double[] getEndcx() {
/*  69 */     return this.endcx;
/*     */   }
/*     */ 
/*     */   public void setEndcx(double[] endcx) {
/*  73 */     this.endcx = endcx;
/*     */   }
/*     */ 
/*     */   public double[] getEndcy() {
/*  77 */     return this.endcy;
/*     */   }
/*     */ 
/*     */   public void setEndcy(double[] endcy) {
/*  81 */     this.endcy = endcy;
/*     */   }
/*     */ 
/*     */   public double[] getStartcx() {
/*  85 */     return this.startcx;
/*     */   }
/*     */ 
/*     */   public void setStartcx(double[] startcx) {
/*  89 */     this.startcx = startcx;
/*     */   }
/*     */ 
/*     */   public double[] getStartcy() {
/*  93 */     return this.startcy;
/*     */   }
/*     */ 
/*     */   public void setStartcy(double[] startcy) {
/*  97 */     this.startcy = startcy;
/*     */   }
/*     */ 
/*     */   public double getCenterx() {
/* 101 */     return this.centerx;
/*     */   }
/*     */ 
/*     */   public void setCenterx(double centerx) {
/* 105 */     this.centerx = centerx;
/*     */   }
/*     */ 
/*     */   public double getCentery() {
/* 109 */     return this.centery;
/*     */   }
/*     */ 
/*     */   public void setCentery(double centery) {
/* 113 */     this.centery = centery;
/*     */   }
/*     */ 
/*     */   public double getIhigh() {
/* 117 */     return this.ihigh;
/*     */   }
/*     */ 
/*     */   public void setIhigh(double ihigh) {
/* 121 */     this.ihigh = ihigh;
/*     */   }
/*     */ 
/*     */   public double getIwide() {
/* 125 */     return this.iwide;
/*     */   }
/*     */ 
/*     */   public void setIwide(double iwide) {
/* 129 */     this.iwide = iwide;
/*     */   }
/*     */ 
/*     */   public void setIwide(int iwide) {
/* 133 */     this.iwide = iwide;
/*     */   }
/*     */ 
/*     */   public double getAngle() {
/* 137 */     return this.angle;
/*     */   }
/*     */ 
/*     */   public void setAngle(double angle) {
/* 141 */     this.angle = angle;
/*     */   }
/*     */ 
/*     */   public int getPointcount() {
/* 145 */     return this.pointcount;
/*     */   }
/*     */ 
/*     */   public void setPointcount(int pointcount) {
/* 149 */     this.pointcount = pointcount;
/*     */   }
/*     */ 
/*     */   public int[] getX() {
/* 153 */     return this.x;
/*     */   }
/*     */ 
/*     */   public void setX(int[] x) {
/* 157 */     this.x = x;
/*     */   }
/*     */ 
/*     */   public int getMaxx() {
/* 161 */     return this.maxx;
/*     */   }
/*     */ 
/*     */   public void setMaxx(int maxx) {
/* 165 */     this.maxx = maxx;
/*     */   }
/*     */ 
/*     */   public int getMaxy() {
/* 169 */     return this.maxy;
/*     */   }
/*     */ 
/*     */   public void setMaxy(int maxy) {
/* 173 */     this.maxy = maxy;
/*     */   }
/*     */ 
/*     */   public int getMinx() {
/* 177 */     return this.minx;
/*     */   }
/*     */ 
/*     */   public void setMinx(int minx) {
/* 181 */     this.minx = minx;
/*     */   }
/*     */ 
/*     */   public int getMiny() {
/* 185 */     return this.miny;
/*     */   }
/*     */ 
/*     */   public void setMiny(int miny) {
/* 189 */     this.miny = miny;
/*     */   }
/*     */ 
/*     */   public int[] getY() {
/* 193 */     return this.y;
/*     */   }
/*     */ 
/*     */   public void setY(int[] y) {
/* 197 */     this.y = y;
/*     */   }
/*     */ 
/*     */   public int getMaxX()
/*     */   {
/* 202 */     int iMaxx = this.x[0];
/* 203 */     for (int i = 0; i < this.x.length; i++) {
/* 204 */       if (this.x[i] > iMaxx) {
/* 205 */         iMaxx = this.x[i];
/*     */       }
/*     */     }
/* 208 */     return iMaxx;
/*     */   }
/*     */ 
/*     */   public int getMaxY()
/*     */   {
/* 213 */     int iMaxy = this.y[0];
/* 214 */     for (int i = 0; i < this.y.length; i++) {
/* 215 */       if (this.y[i] > iMaxy) {
/* 216 */         iMaxy = this.y[i];
/*     */       }
/*     */     }
/* 219 */     return iMaxy;
/*     */   }
/*     */ 
/*     */   public int getMinX()
/*     */   {
/* 224 */     int iMinx = this.x[0];
/* 225 */     for (int i = 0; i < this.x.length; i++) {
/* 226 */       if (this.x[i] < iMinx) {
/* 227 */         iMinx = this.x[i];
/*     */       }
/*     */     }
/* 230 */     return iMinx;
/*     */   }
/*     */ 
/*     */   public int getMinY()
/*     */   {
/* 235 */     int iMiny = this.y[0];
/* 236 */     for (int i = 0; i < this.y.length; i++) {
/* 237 */       if (this.y[i] < iMiny) {
/* 238 */         iMiny = this.y[i];
/*     */       }
/*     */     }
/* 241 */     return iMiny;
/*     */   }
/*     */ 
/*     */   public int getBnewMaxX()
/*     */   {
/* 246 */     double iMaxx = this.endax[0];
/* 247 */     for (int i = 0; i < this.endax.length; i++) {
/* 248 */       if (this.endax[i] > iMaxx) {
/* 249 */         iMaxx = this.endax[i];
/*     */       }
/*     */     }
/* 252 */     return (int)iMaxx;
/*     */   }
/*     */ 
/*     */   public int getBnewMaxY()
/*     */   {
/* 257 */     double iMaxy = this.enday[0];
/* 258 */     for (int i = 0; i < this.enday.length; i++) {
/* 259 */       if (this.enday[i] > iMaxy) {
/* 260 */         iMaxy = this.enday[i];
/*     */       }
/*     */     }
/* 263 */     return (int)iMaxy;
/*     */   }
/*     */ 
/*     */   public int getBnewMinX()
/*     */   {
/* 268 */     double iMinx = this.endax[0];
/* 269 */     for (int i = 0; i < this.endax.length; i++) {
/* 270 */       if (this.endax[i] < iMinx) {
/* 271 */         iMinx = this.endax[i];
/*     */       }
/*     */     }
/* 274 */     return (int)iMinx;
/*     */   }
/*     */ 
/*     */   public int getBnewMinY()
/*     */   {
/* 279 */     double iMiny = this.enday[0];
/* 280 */     for (int i = 0; i < this.enday.length; i++) {
/* 281 */       if (this.enday[i] < iMiny) {
/* 282 */         iMiny = this.enday[i];
/*     */       }
/*     */     }
/* 285 */     return (int)iMiny;
/*     */   }
/*     */ 
/*     */   public void CacuMinMaxXY()
/*     */   {
/* 290 */     this.minx = getMinX();
/* 291 */     this.miny = getMinY();
/* 292 */     this.maxx = getMaxX();
/* 293 */     this.maxy = getMaxY();
/* 294 */     this.bnewminx = getBnewMinX();
/* 295 */     this.bnewminy = getBnewMinY();
/* 296 */     this.bnewmaxx = getBnewMaxX();
/* 297 */     this.bnewmaxy = getBnewMaxY();
/*     */   }
/*     */ 
/*     */   public void transpointA(int[] startatranx, int[] startatrany)
/*     */   {
/* 312 */     this.endax[0] = startatranx[3];
/* 313 */     this.enday[0] = startatrany[3];
/* 314 */     this.endax[1] = startatranx[2];
/* 315 */     this.enday[1] = startatrany[2];
/* 316 */     this.endax[2] = startatranx[1];
/* 317 */     this.enday[2] = startatrany[1];
/* 318 */     this.endax[3] = startatranx[0];
/* 319 */     this.enday[3] = startatrany[0];
/*     */   }
/*     */ 
/*     */   public void transPointCenterE()
/*     */   {
/* 326 */     this.endcx[0] = (int)(this.endax[0] + (this.endax[1] - this.endax[0]) / 2.0D);
/* 327 */     this.endcy[0] = (int)(this.enday[0] + (this.enday[1] - this.enday[0]) / 2.0D);
/*     */ 
/* 329 */     this.endcx[1] = (int)(this.endax[1] + (this.endax[2] - this.endax[1]) / 2.0D);
/* 330 */     this.endcy[1] = (int)(this.enday[1] + (this.enday[2] - this.enday[1]) / 2.0D);
/*     */ 
/* 332 */     this.endcx[2] = (int)(this.endax[2] + (this.endax[3] - this.endax[2]) / 2.0D);
/* 333 */     this.endcy[2] = (int)(this.enday[2] + (this.enday[3] - this.enday[2]) / 2.0D);
/*     */ 
/* 335 */     this.endcx[3] = (int)(this.endax[3] + (this.endax[0] - this.endax[3]) / 2.0D);
/* 336 */     this.endcy[3] = (int)(this.enday[3] + (this.enday[0] - this.enday[3]) / 2.0D);
/*     */   }
/*     */ 
/*     */   public void getTransCenterPoint()
/*     */   {
/* 346 */     this.centery = (int)Math.round(this.enday[0] + (this.enday[2] - this.enday[0]) / 2.0D);
/* 347 */     this.centerx = (int)Math.round(this.endax[0] + (this.endax[2] - this.endax[0]) / 2.0D);
/*     */   }
/*     */ 
/*     */   public void dragOneEToGetOtherA(int k, int dx, int dy)
/*     */   {
/* 356 */     this.endcx[k] += dx;
/* 357 */     this.endcy[k] += dy;
/*     */   }
/*     */ 
/*     */   public void dragOneAToGetWH(int k, int dx, int dy)
/*     */   {
/* 367 */     int i = k;
/*     */ 
/* 370 */     this.endax[i] += dx;
/* 371 */     this.enday[i] += dy;
/*     */ 
/* 374 */     double arcx1 = 0.0D; double arcy1 = 0.0D;
/* 375 */     double inputangle = 3.141592653589793D * this.angle / 180.0D;
/*     */ 
/* 377 */     arcx1 = (this.endax[i] - this.centerx) * Math.cos(inputangle) + (this.enday[i] - this.centery) * Math.sin(inputangle) + this.centerx;
/* 378 */     arcy1 = -(this.endax[i] - this.centerx) * Math.sin(inputangle) + (this.enday[i] - this.centery) * Math.cos(inputangle) + this.centery;
/* 379 */     this.iwide = Math.abs(arcx1 - this.centerx);
/* 380 */     this.ihigh = Math.abs(arcy1 - this.centery);
/*     */   }
/*     */ 
/*     */   public void dragOneEToGetWH(int k, int dx, int dy)
/*     */   {
/* 396 */     double arcx1 = 0.0D; double arcy1 = 0.0D;
/* 397 */     double inputangle = 3.141592653589793D * this.angle / 180.0D;
/*     */ 
/* 401 */     this.endcx[k] += dx;
/* 402 */     this.endcy[k] += dy;
/* 403 */     arcx1 = (this.endcx[k] - this.centerx) * Math.cos(inputangle) + (this.endcy[k] - this.centery) * Math.sin(inputangle) + this.centerx;
/* 404 */     arcy1 = -(this.endcx[k] - this.centerx) * Math.sin(inputangle) + (this.endcy[k] - this.centery) * Math.cos(inputangle) + this.centery;
/*     */ 
/* 407 */     if (k == 0)
/*     */     {
/* 411 */       this.ihigh = Math.abs(arcy1 - this.centery);
/*     */ 
/* 413 */       this.iwide = (Math.sqrt(Math.pow(this.endax[0] - this.endax[1], 2.0D) + Math.pow(this.enday[0] - this.enday[1], 2.0D)) / 2.0D);
/*     */     }
/* 415 */     else if (k == 1)
/*     */     {
/* 417 */       this.iwide = Math.abs(arcx1 - this.centerx);
/*     */ 
/* 419 */       this.ihigh = (Math.sqrt(Math.pow(this.endax[2] - this.endax[1], 2.0D) + Math.pow(this.enday[2] - this.enday[1], 2.0D)) / 2.0D);
/*     */     }
/* 422 */     else if (k == 2)
/*     */     {
/* 424 */       this.ihigh = Math.abs(arcy1 - this.centery);
/*     */ 
/* 426 */       this.iwide = (Math.sqrt(Math.pow(this.endax[0] - this.endax[1], 2.0D) + Math.pow(this.enday[0] - this.enday[1], 2.0D)) / 2.0D);
/*     */     }
/* 428 */     else if (k == 3)
/*     */     {
/* 430 */       this.iwide = Math.abs(arcx1 - this.centerx);
/*     */ 
/* 432 */       this.ihigh = (Math.sqrt(Math.pow(this.endax[2] - this.endax[1], 2.0D) + Math.pow(this.enday[2] - this.enday[1], 2.0D)) / 2.0D);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void getPolygonXY(double startX, double startY, double endX, double endY, String shapeType, double fangle, double rate)
/*     */   {
/* 447 */     this.angle = fangle;
/* 448 */     double radius = 0.0D;
/* 449 */     double centerX = startX + (endX - startX) / 2.0D;
/* 450 */     double centerY = startY + (endY - startY) / 2.0D;
/*     */ 
/* 452 */     double scaleX = 1.0D;
/* 453 */     double scaleY = 1.0D;
/* 454 */     int itype = 1;
/* 455 */     double inputangle = 3.141592653589793D * this.angle / 180.0D;
/*     */ 
/* 458 */     this.startax[0] = (int)Math.round(endX);
/* 459 */     this.startay[0] = (int)Math.round(startY);
/* 460 */     this.startax[1] = (int)Math.round(startX);
/* 461 */     this.startay[1] = (int)Math.round(startY);
/* 462 */     this.startax[2] = (int)Math.round(startX);
/* 463 */     this.startay[2] = (int)Math.round(endY);
/* 464 */     this.startax[3] = (int)Math.round(endX);
/* 465 */     this.startay[3] = (int)Math.round(endY);
/*     */ 
/* 471 */     int[] startatranx = new int[4];
/* 472 */     int[] startatrany = new int[4];
/* 473 */     for (int k = 0; k <= 3; k++) {
/* 474 */       double arcx1 = 0.0D; double arcy1 = 0.0D;
/* 475 */       double initangle1 = 0.0D;
/* 476 */       double arcangle1 = 0.0D;
/* 477 */       double zeroAngleX1 = 0.0D;
/* 478 */       double zeroAngleY1 = 0.0D;
/*     */ 
/* 480 */       if (k == 0) {
/* 481 */         double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 482 */         initangle1 = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 483 */         arcangle1 = -(initangle1 + inputangle);
/* 484 */         zeroAngleX1 = centerX + r;
/* 485 */         zeroAngleY1 = centerY;
/* 486 */         arcx1 = (zeroAngleX1 - centerX) * Math.cos(arcangle1) - (zeroAngleY1 - centerY) * Math.sin(arcangle1) + centerX;
/* 487 */         arcy1 = -1.0D * (zeroAngleX1 - centerX) * Math.sin(arcangle1) + (zeroAngleY1 - centerY) * Math.cos(arcangle1) + centerY;
/* 488 */       } else if (k == 1) {
/* 489 */         double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 490 */         initangle1 = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 491 */         arcangle1 = -(initangle1 + inputangle);
/* 492 */         zeroAngleX1 = centerX + r;
/* 493 */         zeroAngleY1 = centerY;
/* 494 */         arcx1 = (zeroAngleX1 - centerX) * Math.cos(arcangle1) - (zeroAngleY1 - centerY) * Math.sin(arcangle1) + centerX;
/* 495 */         arcy1 = -1.0D * (zeroAngleX1 - centerX) * Math.sin(arcangle1) + (zeroAngleY1 - centerY) * Math.cos(arcangle1) + centerY;
/* 496 */       } else if (k == 2) {
/* 497 */         double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 498 */         initangle1 = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 499 */         arcangle1 = -(initangle1 + inputangle);
/* 500 */         zeroAngleX1 = centerX + r;
/* 501 */         zeroAngleY1 = centerY;
/* 502 */         arcx1 = (zeroAngleX1 - centerX) * Math.cos(arcangle1) - (zeroAngleY1 - centerY) * Math.sin(arcangle1) + centerX;
/* 503 */         arcy1 = -1.0D * (zeroAngleX1 - centerX) * Math.sin(arcangle1) + (zeroAngleY1 - centerY) * Math.cos(arcangle1) + centerY;
/* 504 */       } else if (k == 3) {
/* 505 */         double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 506 */         initangle1 = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 507 */         arcangle1 = -(initangle1 + inputangle);
/* 508 */         zeroAngleX1 = centerX + r;
/* 509 */         zeroAngleY1 = centerY;
/* 510 */         arcx1 = (zeroAngleX1 - centerX) * Math.cos(arcangle1) - (zeroAngleY1 - centerY) * Math.sin(arcangle1) + centerX;
/* 511 */         arcy1 = -1.0D * (zeroAngleX1 - centerX) * Math.sin(arcangle1) + (zeroAngleY1 - centerY) * Math.cos(arcangle1) + centerY;
/*     */       }
/*     */ 
/* 515 */       startatranx[k] = (int)Math.round(arcx1);
/* 516 */       startatrany[k] = (int)Math.round(arcy1);
/*     */     }
/*     */ 
/* 521 */     transpointA(startatranx, startatrany);
/* 522 */     transPointCenterE();
/*     */ 
/* 524 */     double zeroAngleX = 0.0D;
/* 525 */     double zeroAngleY = 0.0D;
/* 526 */     double arcx = 0.0D; double arcy = 0.0D;
/* 527 */     double initangle = 0.0D;
/* 528 */     double arcangle = 0.0D;
/*     */ 
/* 530 */     if (endX - startX == endY - startY) {
/* 531 */       radius = (endX - startX) / 2.0D;
/* 532 */       zeroAngleX = endX;
/* 533 */       zeroAngleY = centerY;
/* 534 */       itype = 1;
/* 535 */       scaleX = 1.0D;
/* 536 */       scaleY = 1.0D;
/* 537 */     } else if (endX - startX <= endY - startY) {
/* 538 */       radius = (endX - startX) / 2.0D;
/* 539 */       zeroAngleX = centerX + radius;
/* 540 */       zeroAngleY = centerY;
/* 541 */       itype = 2;
/* 542 */       scaleX = 1.0D;
/* 543 */       scaleY = (endY - startY) / (endX - startX);
/* 544 */     } else if (endX - startX >= endY - startY) {
/* 545 */       radius = (endY - startY) / 2.0D;
/* 546 */       zeroAngleX = centerX + radius;
/* 547 */       zeroAngleY = centerY;
/* 548 */       itype = 3;
/* 549 */       scaleX = (endX - startX) / (endY - startY);
/* 550 */       scaleY = 1.0D;
/*     */     }
/*     */ 
/* 566 */     double ld_offset = 5.0D * rate;
/* 567 */     if (shapeType.equals("Circle"))
/*     */     {
/* 578 */       initangle = 0.0D;
/* 579 */       this.x = new int[4];
/* 580 */       this.y = new int[4];
/* 581 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 583 */         if (i == 0) {
/* 584 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 585 */           initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 586 */           arcangle = -(initangle + inputangle);
/* 587 */           zeroAngleX = centerX + r;
/* 588 */           zeroAngleY = centerY;
/* 589 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 590 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 591 */         } else if (i == 1) {
/* 592 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 593 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 594 */           arcangle = -(initangle + inputangle);
/* 595 */           zeroAngleX = centerX + r;
/* 596 */           zeroAngleY = centerY;
/* 597 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 598 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 599 */         } else if (i == 2) {
/* 600 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 601 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 602 */           arcangle = -(initangle + inputangle);
/* 603 */           zeroAngleX = centerX + r;
/* 604 */           zeroAngleY = centerY;
/* 605 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 606 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 607 */         } else if (i == 3) {
/* 608 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 609 */           initangle = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 610 */           arcangle = -(initangle + inputangle);
/* 611 */           zeroAngleX = centerX + r;
/* 612 */           zeroAngleY = centerY;
/* 613 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 614 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 616 */         this.x[i] = (int)Math.round(arcx);
/* 617 */         this.y[i] = (int)Math.round(arcy);
/* 618 */         this.pointcount = 4;
/*     */       }
/* 620 */     } else if (shapeType.equals("L_ParallelCurve"))
/*     */     {
/* 623 */       this.x = new int[8];
/* 624 */       this.y = new int[8];
/* 625 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 628 */         if (i == 0) {
/* 629 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 630 */           double a1 = Math.atan(Math.abs(startY - centerY) / Math.abs(endX - centerX));
/* 631 */           initangle = a1;
/* 632 */           arcangle = -(initangle + inputangle);
/* 633 */           zeroAngleX = centerX + r;
/* 634 */           zeroAngleY = centerY;
/* 635 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 636 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 637 */         } else if (i == 1) {
/* 638 */           double r = Math.sqrt(Math.pow(endX - ld_offset - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 639 */           double a1 = Math.atan(Math.abs(startY - centerY) / Math.abs(endX - ld_offset - centerX));
/* 640 */           initangle = a1;
/* 641 */           arcangle = -(initangle + inputangle);
/* 642 */           zeroAngleX = centerX + r;
/* 643 */           zeroAngleY = centerY;
/* 644 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 645 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 646 */         } else if (i == 2) {
/* 647 */           double r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 648 */           double a1 = Math.atan(Math.abs(endY - centerY) / Math.abs(startX - centerX));
/* 649 */           initangle = a1;
/* 650 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 651 */           zeroAngleX = centerX + r;
/* 652 */           zeroAngleY = centerY;
/* 653 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 654 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 655 */         } else if (i == 3) {
/* 656 */           double r = Math.sqrt(Math.pow(startX + ld_offset - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 657 */           double a1 = Math.atan(Math.abs(endY - centerY) / Math.abs(startX + ld_offset - centerX));
/* 658 */           initangle = a1;
/* 659 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 660 */           zeroAngleX = centerX + r;
/* 661 */           zeroAngleY = centerY;
/* 662 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 663 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 670 */         this.x[i] = (int)Math.round(arcx);
/* 671 */         this.y[i] = (int)Math.round(arcy);
/*     */       }
/*     */ 
/* 679 */       double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 680 */       double a1 = 0.0D;
/* 681 */       initangle = a1;
/* 682 */       arcangle = -(initangle + inputangle);
/* 683 */       zeroAngleX = centerX + r;
/* 684 */       zeroAngleY = centerY;
/* 685 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 686 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 687 */       this.x[4] = (int)Math.round(arcx);
/* 688 */       this.y[4] = (int)Math.round(arcy);
/*     */ 
/* 690 */       r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 691 */       a1 = 0.0D;
/* 692 */       initangle = a1;
/* 693 */       arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 694 */       zeroAngleX = centerX + r;
/* 695 */       zeroAngleY = centerY;
/* 696 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 697 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 698 */       this.x[5] = (int)Math.round(arcx);
/* 699 */       this.y[5] = (int)Math.round(arcy);
/*     */ 
/* 702 */       r = Math.sqrt(Math.pow(endX - ld_offset - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 703 */       a1 = 0.0D;
/* 704 */       initangle = a1;
/* 705 */       arcangle = -(initangle + inputangle);
/* 706 */       zeroAngleX = centerX + r;
/* 707 */       zeroAngleY = centerY;
/* 708 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 709 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 710 */       this.x[6] = (int)Math.round(arcx);
/* 711 */       this.y[6] = (int)Math.round(arcy);
/*     */ 
/* 713 */       r = Math.sqrt(Math.pow(startX - ld_offset - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 714 */       a1 = 0.0D;
/* 715 */       initangle = a1;
/* 716 */       arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 717 */       zeroAngleX = centerX + r;
/* 718 */       zeroAngleY = centerY;
/* 719 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 720 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 721 */       this.x[7] = (int)Math.round(arcx);
/* 722 */       this.y[7] = (int)Math.round(arcy);
/*     */ 
/* 724 */       this.pointcount = 8;
/* 725 */     } else if (shapeType.equals("R_ParallelCurve"))
/*     */     {
/* 728 */       this.x = new int[8];
/* 729 */       this.y = new int[8];
/* 730 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 733 */         if (i == 0) {
/* 734 */           double r = Math.sqrt(Math.pow(startX + ld_offset - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 735 */           double a1 = Math.atan(Math.abs(startX + ld_offset - centerX) / Math.abs(startY - centerY));
/* 736 */           initangle = a1;
/* 737 */           arcangle = -(initangle + 1.570796326794897D + inputangle);
/* 738 */           zeroAngleX = centerX + r;
/* 739 */           zeroAngleY = centerY;
/* 740 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 741 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 742 */         } else if (i == 1) {
/* 743 */           double r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 744 */           double a1 = Math.atan(Math.abs(startX - centerX) / Math.abs(startY - centerY));
/* 745 */           initangle = a1;
/* 746 */           arcangle = -(initangle + 1.570796326794897D + inputangle);
/* 747 */           zeroAngleX = centerX + r;
/* 748 */           zeroAngleY = centerY;
/* 749 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 750 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 751 */         } else if (i == 2) {
/* 752 */           double r = Math.sqrt(Math.pow(endX - ld_offset - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 753 */           double a1 = Math.atan(Math.abs(endX - ld_offset - centerX) / Math.abs(endY - centerY));
/* 754 */           initangle = a1;
/* 755 */           arcangle = -(initangle + 4.71238898038469D + inputangle);
/* 756 */           zeroAngleX = centerX + r;
/* 757 */           zeroAngleY = centerY;
/* 758 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 759 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 760 */         } else if (i == 3) {
/* 761 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 762 */           double a1 = Math.atan(Math.abs(endX - centerX) / Math.abs(endY - centerY));
/* 763 */           initangle = a1;
/* 764 */           arcangle = -(initangle + 4.71238898038469D + inputangle);
/* 765 */           zeroAngleX = centerX + r;
/* 766 */           zeroAngleY = centerY;
/* 767 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 768 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 775 */         this.x[i] = (int)Math.round(arcx);
/* 776 */         this.y[i] = (int)Math.round(arcy);
/*     */       }
/*     */ 
/* 784 */       double r = Math.sqrt(Math.pow(startX + ld_offset - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 785 */       double a1 = 0.0D;
/* 786 */       initangle = a1;
/* 787 */       arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 788 */       zeroAngleX = centerX + r;
/* 789 */       zeroAngleY = centerY;
/* 790 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 791 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 792 */       this.x[4] = (int)Math.round(arcx);
/* 793 */       this.y[4] = (int)Math.round(arcy);
/*     */ 
/* 795 */       r = Math.sqrt(Math.pow(endX + ld_offset - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 796 */       a1 = 0.0D;
/* 797 */       initangle = a1;
/* 798 */       arcangle = -(initangle + inputangle);
/* 799 */       zeroAngleX = centerX + r;
/* 800 */       zeroAngleY = centerY;
/* 801 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 802 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 803 */       this.x[5] = (int)Math.round(arcx);
/* 804 */       this.y[5] = (int)Math.round(arcy);
/*     */ 
/* 807 */       r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 808 */       a1 = 0.0D;
/* 809 */       initangle = a1;
/* 810 */       arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 811 */       zeroAngleX = centerX + r;
/* 812 */       zeroAngleY = centerY;
/* 813 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 814 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 815 */       this.x[6] = (int)Math.round(arcx);
/* 816 */       this.y[6] = (int)Math.round(arcy);
/*     */ 
/* 818 */       r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 819 */       a1 = 0.0D;
/* 820 */       initangle = a1;
/* 821 */       arcangle = -(initangle + inputangle);
/* 822 */       zeroAngleX = centerX + r;
/* 823 */       zeroAngleY = centerY;
/* 824 */       arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 825 */       arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 826 */       this.x[7] = (int)Math.round(arcx);
/* 827 */       this.y[7] = (int)Math.round(arcy);
/*     */ 
/* 829 */       this.pointcount = 8;
/* 830 */     } else if (shapeType.equals("L_ParallelLine"))
/*     */     {
/* 833 */       this.x = new int[4];
/* 834 */       this.y = new int[4];
/* 835 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 838 */         if (i == 0) {
/* 839 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 840 */           double a1 = Math.atan(Math.abs(startY - centerY) / Math.abs(endX - centerX));
/* 841 */           initangle = a1;
/* 842 */           arcangle = -(initangle + inputangle);
/* 843 */           zeroAngleX = centerX + r;
/* 844 */           zeroAngleY = centerY;
/* 845 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 846 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 847 */         } else if (i == 1) {
/* 848 */           double r = Math.sqrt(Math.pow(endX - ld_offset - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 849 */           double a1 = Math.atan(Math.abs(startY - centerY) / Math.abs(endX - ld_offset - centerX));
/* 850 */           initangle = a1;
/* 851 */           arcangle = -(initangle + inputangle);
/* 852 */           zeroAngleX = centerX + r;
/* 853 */           zeroAngleY = centerY;
/* 854 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 855 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 856 */         } else if (i == 2) {
/* 857 */           double r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 858 */           double a1 = Math.atan(Math.abs(endY - centerY) / Math.abs(startX - centerX));
/* 859 */           initangle = a1;
/* 860 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 861 */           zeroAngleX = centerX + r;
/* 862 */           zeroAngleY = centerY;
/* 863 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 864 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 865 */         } else if (i == 3) {
/* 866 */           double r = Math.sqrt(Math.pow(startX + ld_offset - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 867 */           double a1 = Math.atan(Math.abs(endY - centerY) / Math.abs(startX + ld_offset - centerX));
/* 868 */           initangle = a1;
/* 869 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 870 */           zeroAngleX = centerX + r;
/* 871 */           zeroAngleY = centerY;
/* 872 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 873 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 880 */         this.x[i] = (int)Math.round(arcx);
/* 881 */         this.y[i] = (int)Math.round(arcy);
/*     */       }
/*     */ 
/* 885 */       this.pointcount = 4;
/*     */     }
/* 887 */     else if (shapeType.equals("R_ParallelLine"))
/*     */     {
/* 890 */       this.x = new int[4];
/* 891 */       this.y = new int[4];
/* 892 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 895 */         if (i == 0) {
/* 896 */           double r = Math.sqrt(Math.pow(startX + ld_offset - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 897 */           double a1 = Math.atan(Math.abs(startX + ld_offset - centerX) / Math.abs(startY - centerY));
/* 898 */           initangle = a1;
/* 899 */           arcangle = -(initangle + 1.570796326794897D + inputangle);
/* 900 */           zeroAngleX = centerX + r;
/* 901 */           zeroAngleY = centerY;
/* 902 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 903 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 904 */         } else if (i == 1) {
/* 905 */           double r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(startY - centerY, 2.0D));
/* 906 */           double a1 = Math.atan(Math.abs(startX - centerX) / Math.abs(startY - centerY));
/* 907 */           initangle = a1;
/* 908 */           arcangle = -(initangle + 1.570796326794897D + inputangle);
/* 909 */           zeroAngleX = centerX + r;
/* 910 */           zeroAngleY = centerY;
/* 911 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 912 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 913 */         } else if (i == 2) {
/* 914 */           double r = Math.sqrt(Math.pow(endX - ld_offset - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 915 */           double a1 = Math.atan(Math.abs(endX - ld_offset - centerX) / Math.abs(endY - centerY));
/* 916 */           initangle = a1;
/* 917 */           arcangle = -(initangle + 4.71238898038469D + inputangle);
/* 918 */           zeroAngleX = centerX + r;
/* 919 */           zeroAngleY = centerY;
/* 920 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 921 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 922 */         } else if (i == 3) {
/* 923 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 924 */           double a1 = Math.atan(Math.abs(endX - centerX) / Math.abs(endY - centerY));
/* 925 */           initangle = a1;
/* 926 */           arcangle = -(initangle + 4.71238898038469D + inputangle);
/* 927 */           zeroAngleX = centerX + r;
/* 928 */           zeroAngleY = centerY;
/* 929 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 930 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 937 */         this.x[i] = (int)Math.round(arcx);
/* 938 */         this.y[i] = (int)Math.round(arcy);
/* 939 */         this.pointcount = 4;
/*     */       }
/*     */ 
/*     */     }
/* 943 */     else if (shapeType.equals("Rhomboid"))
/*     */     {
/* 946 */       this.x = new int[4];
/* 947 */       this.y = new int[4];
/* 948 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 951 */         if (i == 0) {
/* 952 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 953 */           double a1 = Math.atan((endY - startY) / (endX - startX));
/* 954 */           initangle = a1;
/* 955 */           arcangle = -(initangle + inputangle);
/* 956 */           zeroAngleX = startX + (endX - startX) / 2.0D + r;
/* 957 */           zeroAngleY = startY + (endY - startY) / 2.0D;
/* 958 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 959 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 960 */         } else if (i == 1) {
/* 961 */           double r = Math.sqrt(Math.pow(startX + (endX - startX) * 3.0D / 4.0D - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 962 */           double a1 = Math.atan((endX - startX) / 4.0D / ((endY - startY) / 2.0D));
/* 963 */           initangle = a1 + 1.570796326794897D;
/* 964 */           arcangle = -(initangle + inputangle);
/* 965 */           zeroAngleX = startX + (endX - startX) / 2.0D + r;
/* 966 */           zeroAngleY = startY + (endY - startY) / 2.0D;
/* 967 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 968 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 969 */         } else if (i == 2) {
/* 970 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 971 */           double a1 = Math.atan((endY - startY) / (endX - startX));
/* 972 */           initangle = a1;
/* 973 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 974 */           zeroAngleX = startX + (endX - startX) / 2.0D + r;
/* 975 */           zeroAngleY = startY + (endY - startY) / 2.0D;
/* 976 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 977 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 978 */         } else if (i == 3) {
/* 979 */           double r = Math.sqrt(Math.pow(startX + (endX - startX) * 3.0D / 4.0D - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 980 */           double a1 = Math.atan((endX - startX) / 4.0D / ((endY - startY) / 2.0D));
/* 981 */           initangle = a1 + 1.570796326794897D;
/* 982 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 983 */           zeroAngleX = startX + (endX - startX) / 2.0D + r;
/* 984 */           zeroAngleY = startY + (endY - startY) / 2.0D;
/* 985 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 986 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 993 */         this.x[i] = (int)Math.round(arcx);
/* 994 */         this.y[i] = (int)Math.round(arcy);
/* 995 */         this.pointcount = 4;
/*     */       }
/*     */ 
/*     */     }
/* 999 */     else if (shapeType.equals("Triangle"))
/*     */     {
/* 1001 */       this.x = new int[3];
/* 1002 */       this.y = new int[3];
/* 1003 */       for (int i = 0; i <= 2; i++)
/*     */       {
/* 1035 */         if (i == 0)
/*     */         {
/* 1037 */           double r = Math.sqrt(Math.pow(endX - centerX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 1038 */           initangle = 0.0D;
/* 1039 */           arcangle = -(initangle + inputangle);
/* 1040 */           zeroAngleX = centerX + r;
/* 1041 */           zeroAngleY = centerY;
/* 1042 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1043 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1044 */         } else if (i == 1)
/*     */         {
/* 1046 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1047 */           initangle = Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1048 */           arcangle = -(initangle + 1.570796326794897D + inputangle);
/* 1049 */           zeroAngleX = centerX + r;
/* 1050 */           zeroAngleY = centerY;
/* 1051 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1052 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1053 */         } else if (i == 2)
/*     */         {
/* 1055 */           double r = Math.sqrt(Math.pow(startX - centerX, 2.0D) + Math.pow(endY - centerY, 2.0D));
/* 1056 */           initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1057 */           arcangle = -(initangle + 3.141592653589793D + inputangle);
/* 1058 */           zeroAngleX = centerX + r;
/* 1059 */           zeroAngleY = centerY;
/* 1060 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1061 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 1063 */         this.x[i] = (int)Math.round(arcx);
/* 1064 */         this.y[i] = (int)Math.round(arcy);
/* 1065 */         this.pointcount = 3;
/*     */       }
/*     */ 
/*     */     }
/* 1070 */     else if (shapeType.equals("Rhombus"))
/*     */     {
/* 1072 */       this.x = new int[4];
/* 1073 */       this.y = new int[4];
/* 1074 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 1076 */         if (i == 0)
/*     */         {
/* 1078 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 1079 */           initangle = 0.0D;
/* 1080 */           arcangle = -(initangle + inputangle);
/* 1081 */           zeroAngleX = centerX + r;
/* 1082 */           zeroAngleY = centerY;
/* 1083 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1084 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1085 */         } else if (i == 1) {
/* 1086 */           double r = Math.sqrt(Math.pow(centerX - centerX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1087 */           initangle = 1.570796326794897D;
/* 1088 */           arcangle = -(initangle + inputangle);
/* 1089 */           zeroAngleX = centerX + r;
/* 1090 */           zeroAngleY = centerY;
/* 1091 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1092 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1093 */         } else if (i == 2) {
/* 1094 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - centerY, 2.0D));
/* 1095 */           initangle = 3.141592653589793D;
/* 1096 */           arcangle = -(initangle + inputangle);
/* 1097 */           zeroAngleX = centerX + r;
/* 1098 */           zeroAngleY = centerY;
/* 1099 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1100 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1101 */         } else if (i == 3) {
/* 1102 */           double r = Math.sqrt(Math.pow(centerX - centerX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1103 */           initangle = 4.71238898038469D;
/* 1104 */           arcangle = -(initangle + inputangle);
/* 1105 */           zeroAngleX = centerX + r;
/* 1106 */           zeroAngleY = centerY;
/* 1107 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1108 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 1112 */         this.x[i] = (int)Math.round(arcx);
/* 1113 */         this.y[i] = (int)Math.round(arcy);
/* 1114 */         this.pointcount = 4;
/*     */       }
/*     */     }
/* 1117 */     else if (shapeType.equals("Rectangle")) {
/* 1118 */       initangle = 0.0D;
/* 1119 */       this.x = new int[4];
/* 1120 */       this.y = new int[4];
/* 1121 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 1123 */         if (i == 0) {
/* 1124 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1125 */           initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1126 */           arcangle = -(initangle + inputangle);
/* 1127 */           zeroAngleX = centerX + r;
/* 1128 */           zeroAngleY = centerY;
/* 1129 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1130 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1131 */         } else if (i == 1) {
/* 1132 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1133 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1134 */           arcangle = -(initangle + inputangle);
/* 1135 */           zeroAngleX = centerX + r;
/* 1136 */           zeroAngleY = centerY;
/* 1137 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1138 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1139 */         } else if (i == 2) {
/* 1140 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1141 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1142 */           arcangle = -(initangle + inputangle);
/* 1143 */           zeroAngleX = centerX + r;
/* 1144 */           zeroAngleY = centerY;
/* 1145 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1146 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1147 */         } else if (i == 3) {
/* 1148 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1149 */           initangle = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1150 */           arcangle = -(initangle + inputangle);
/* 1151 */           zeroAngleX = centerX + r;
/* 1152 */           zeroAngleY = centerY;
/* 1153 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1154 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 1156 */         this.x[i] = (int)Math.round(arcx);
/* 1157 */         this.y[i] = (int)Math.round(arcy);
/* 1158 */         this.pointcount = 4;
/*     */       }
/* 1160 */     } else if (shapeType.equals("Cylinder")) {
/* 1161 */       initangle = 0.0D;
/* 1162 */       this.x = new int[4];
/* 1163 */       this.y = new int[4];
/* 1164 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 1166 */         if (i == 0) {
/* 1167 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1168 */           initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1169 */           arcangle = -(initangle + inputangle);
/* 1170 */           zeroAngleX = centerX + r;
/* 1171 */           zeroAngleY = centerY;
/* 1172 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1173 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1174 */         } else if (i == 1) {
/* 1175 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1176 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1177 */           arcangle = -(initangle + inputangle);
/* 1178 */           zeroAngleX = centerX + r;
/* 1179 */           zeroAngleY = centerY;
/* 1180 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1181 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1182 */         } else if (i == 2) {
/* 1183 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1184 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1185 */           arcangle = -(initangle + inputangle);
/* 1186 */           zeroAngleX = centerX + r;
/* 1187 */           zeroAngleY = centerY;
/* 1188 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1189 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1190 */         } else if (i == 3) {
/* 1191 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1192 */           initangle = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1193 */           arcangle = -(initangle + inputangle);
/* 1194 */           zeroAngleX = centerX + r;
/* 1195 */           zeroAngleY = centerY;
/* 1196 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1197 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 1199 */         this.x[i] = (int)Math.round(arcx);
/* 1200 */         this.y[i] = (int)Math.round(arcy);
/* 1201 */         this.pointcount = 4;
/*     */       }
/* 1203 */     } else if (shapeType.equals("RoundRectangle")) {
/* 1204 */       initangle = 0.0D;
/* 1205 */       this.x = new int[4];
/* 1206 */       this.y = new int[4];
/* 1207 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 1209 */         if (i == 0) {
/* 1210 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1211 */           initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1212 */           arcangle = -(initangle + inputangle);
/* 1213 */           zeroAngleX = centerX + r;
/* 1214 */           zeroAngleY = centerY;
/* 1215 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1216 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1217 */         } else if (i == 1) {
/* 1218 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1219 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1220 */           arcangle = -(initangle + inputangle);
/* 1221 */           zeroAngleX = centerX + r;
/* 1222 */           zeroAngleY = centerY;
/* 1223 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1224 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1225 */         } else if (i == 2) {
/* 1226 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1227 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1228 */           arcangle = -(initangle + inputangle);
/* 1229 */           zeroAngleX = centerX + r;
/* 1230 */           zeroAngleY = centerY;
/* 1231 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1232 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1233 */         } else if (i == 3) {
/* 1234 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1235 */           initangle = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1236 */           arcangle = -(initangle + inputangle);
/* 1237 */           zeroAngleX = centerX + r;
/* 1238 */           zeroAngleY = centerY;
/* 1239 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1240 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 1242 */         this.x[i] = (int)Math.round(arcx);
/* 1243 */         this.y[i] = (int)Math.round(arcy);
/* 1244 */         this.pointcount = 4;
/*     */       }
/* 1246 */     } else if (shapeType.equals("Arrow")) {
/* 1247 */       initangle = 0.0D;
/* 1248 */       this.x = new int[7];
/* 1249 */       this.y = new int[7];
/* 1250 */       for (int i = 0; i <= 6; i++) {
/* 1251 */         double r = 0.0D;
/* 1252 */         if (i == 0) {
/* 1253 */           arcangle = -(initangle + inputangle);
/* 1254 */           zeroAngleX = endX;
/* 1255 */           zeroAngleY = centerY;
/* 1256 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1257 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1258 */         } else if (i == 1) {
/* 1259 */           if (endX - startX >= endY - startY) {
/* 1260 */             r = Math.sqrt(Math.pow(centerX - (endX - (endY - startY) / 3.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1261 */             initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D - (endY - startY) / 3.0D));
/*     */           } else {
/* 1263 */             r = Math.sqrt(Math.pow(centerX - (endX - (endX - startX) / 3.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1264 */             initangle = Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D - (endX - startX) / 3.0D));
/*     */           }
/*     */ 
/* 1268 */           arcangle = -(initangle + inputangle);
/* 1269 */           zeroAngleX = centerX + r;
/* 1270 */           zeroAngleY = centerY;
/* 1271 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1272 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1273 */         } else if (i == 6) {
/* 1274 */           if (endX - startX >= endY - startY) {
/* 1275 */             r = Math.sqrt(Math.pow(centerX - (endX - (endY - startY) / 3.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1276 */             initangle = 6.283185307179586D - Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D - (endY - startY) / 3.0D));
/*     */           } else {
/* 1278 */             r = Math.sqrt(Math.pow(centerX - (endX - (endX - startX) / 3.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1279 */             initangle = 6.283185307179586D - Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D - (endX - startX) / 3.0D));
/*     */           }
/*     */ 
/* 1283 */           arcangle = -(initangle + inputangle);
/* 1284 */           zeroAngleX = centerX + r;
/* 1285 */           zeroAngleY = centerY;
/* 1286 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1287 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1288 */         } else if (i == 2) {
/* 1289 */           if (endX - startX >= endY - startY) {
/* 1290 */             r = Math.sqrt(Math.pow(centerX - (endX - (endY - startY) / 3.0D), 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1291 */             initangle = Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D - (endY - startY) / 3.0D));
/*     */           } else {
/* 1293 */             r = Math.sqrt(Math.pow(centerX - (endX - (endX - startX) / 3.0D), 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1294 */             initangle = Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D - (endX - startX) / 3.0D));
/*     */           }
/* 1296 */           arcangle = -(initangle + inputangle);
/* 1297 */           zeroAngleX = centerX + r;
/* 1298 */           zeroAngleY = centerY;
/* 1299 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1300 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1301 */         } else if (i == 5) {
/* 1302 */           if (endX - startX >= endY - startY) {
/* 1303 */             r = Math.sqrt(Math.pow(centerX - (endX - (endY - startY) / 3.0D), 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1304 */             initangle = 6.283185307179586D - Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D - (endY - startY) / 3.0D));
/*     */           } else {
/* 1306 */             r = Math.sqrt(Math.pow(centerX - (endX - (endX - startX) / 3.0D), 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1307 */             initangle = 6.283185307179586D - Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D - (endX - startX) / 3.0D));
/*     */           }
/* 1309 */           arcangle = -(initangle + inputangle);
/* 1310 */           zeroAngleX = centerX + r;
/* 1311 */           zeroAngleY = centerY;
/* 1312 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1313 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1314 */         } else if (i == 3)
/*     */         {
/* 1316 */           r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1317 */           initangle = 3.141592653589793D - Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D));
/*     */ 
/* 1319 */           arcangle = -(initangle + inputangle);
/* 1320 */           zeroAngleX = centerX + r;
/* 1321 */           zeroAngleY = centerY;
/* 1322 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1323 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1324 */         } else if (i == 4)
/*     */         {
/* 1326 */           r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - (startY + (endY - startY) / 4.0D), 2.0D));
/* 1327 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 4.0D / ((endX - startX) / 2.0D));
/*     */ 
/* 1329 */           arcangle = -(initangle + inputangle);
/* 1330 */           zeroAngleX = centerX + r;
/* 1331 */           zeroAngleY = centerY;
/* 1332 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1333 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/* 1335 */         this.x[i] = (int)Math.round(arcx);
/* 1336 */         this.y[i] = (int)Math.round(arcy);
/* 1337 */         this.pointcount = 7;
/*     */       }
/* 1339 */     } else if (shapeType.equals("DovetailArrow")) {
/* 1340 */       initangle = 0.0D;
/* 1341 */       this.x = new int[6];
/* 1342 */       this.y = new int[6];
/* 1343 */       for (int i = 0; i <= 5; i++) {
/* 1344 */         double r = 0.0D;
/* 1345 */         if (i == 0) {
/* 1346 */           arcangle = -(initangle + inputangle);
/* 1347 */           zeroAngleX = endX;
/* 1348 */           zeroAngleY = centerY;
/* 1349 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1350 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1351 */         } else if (i == 3) {
/* 1352 */           arcangle = -(initangle + inputangle);
/* 1353 */           zeroAngleX = centerX;
/* 1354 */           zeroAngleY = centerY;
/* 1355 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1356 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1357 */         } else if (i == 1)
/*     */         {
/* 1359 */           r = Math.sqrt(Math.pow(centerX - centerX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1360 */           initangle = 1.570796326794897D;
/* 1361 */           arcangle = -(initangle + inputangle);
/* 1362 */           zeroAngleX = centerX + r;
/* 1363 */           zeroAngleY = centerY;
/* 1364 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1365 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1366 */         } else if (i == 5)
/*     */         {
/* 1368 */           r = Math.sqrt(Math.pow(centerX - centerX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1369 */           initangle = 4.71238898038469D;
/* 1370 */           arcangle = -(initangle + inputangle);
/* 1371 */           zeroAngleX = centerX + r;
/* 1372 */           zeroAngleY = centerY;
/* 1373 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1374 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1375 */         } else if (i == 2)
/*     */         {
/* 1377 */           r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1378 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/*     */ 
/* 1380 */           arcangle = -(initangle + inputangle);
/* 1381 */           zeroAngleX = centerX + r;
/* 1382 */           zeroAngleY = centerY;
/* 1383 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1384 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1385 */         } else if (i == 4)
/*     */         {
/* 1387 */           r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1388 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/*     */ 
/* 1390 */           arcangle = -(initangle + inputangle);
/* 1391 */           zeroAngleX = centerX + r;
/* 1392 */           zeroAngleY = centerY;
/* 1393 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1394 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 1397 */         this.x[i] = (int)Math.round(arcx);
/* 1398 */         this.y[i] = (int)Math.round(arcy);
/* 1399 */         this.pointcount = 6;
/*     */       }
/* 1401 */     } else if (shapeType.equals("Trapezoid")) {
/* 1402 */       initangle = 0.0D;
/* 1403 */       this.x = new int[4];
/* 1404 */       this.y = new int[4];
/* 1405 */       for (int i = 0; i <= 3; i++)
/*     */       {
/* 1407 */         if (i == 0) {
/* 1408 */           double r = Math.sqrt(Math.pow(centerX - (endX - (endX - startX) / 4.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1409 */           initangle = 1.570796326794897D - Math.atan((endX - startX) / 4.0D / ((endY - startY) / 2.0D));
/* 1410 */           arcangle = -(initangle + inputangle);
/* 1411 */           zeroAngleX = centerX + r;
/* 1412 */           zeroAngleY = centerY;
/* 1413 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1414 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1415 */         } else if (i == 1) {
/* 1416 */           double r = Math.sqrt(Math.pow(centerX - (startX + (endX - startX) / 4.0D), 2.0D) + Math.pow(centerY - startY, 2.0D));
/* 1417 */           initangle = 1.570796326794897D + Math.atan((endX - startX) / 4.0D / ((endY - startY) / 2.0D));
/* 1418 */           arcangle = -(initangle + inputangle);
/* 1419 */           zeroAngleX = centerX + r;
/* 1420 */           zeroAngleY = centerY;
/* 1421 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1422 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1423 */         } else if (i == 2) {
/* 1424 */           double r = Math.sqrt(Math.pow(centerX - startX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1425 */           initangle = 3.141592653589793D + Math.atan((endY - startY) / 2.0D / ((endX - startX) / 2.0D));
/* 1426 */           arcangle = -(initangle + inputangle);
/* 1427 */           zeroAngleX = centerX + r;
/* 1428 */           zeroAngleY = centerY;
/* 1429 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1430 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1431 */         } else if (i == 3) {
/* 1432 */           double r = Math.sqrt(Math.pow(centerX - endX, 2.0D) + Math.pow(centerY - endY, 2.0D));
/* 1433 */           initangle = 4.71238898038469D + Math.atan((endX - startX) / 2.0D / ((endY - startY) / 2.0D));
/* 1434 */           arcangle = -(initangle + inputangle);
/* 1435 */           zeroAngleX = centerX + r;
/* 1436 */           zeroAngleY = centerY;
/* 1437 */           arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1438 */           arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/*     */         }
/*     */ 
/* 1441 */         this.x[i] = (int)Math.round(arcx);
/* 1442 */         this.y[i] = (int)Math.round(arcy);
/* 1443 */         this.pointcount = 4;
/*     */       }
/*     */ 
/*     */     }
/* 1447 */     else if (shapeType.equals("Pentagon")) {
/* 1448 */       initangle = 0.3141592653589793D;
/* 1449 */       this.x = new int[5];
/* 1450 */       this.y = new int[5];
/* 1451 */       for (int i = 0; i <= 4; i++) {
/* 1452 */         arcangle = -(initangle + i * 3.141592653589793D * 72.0D / 180.0D);
/* 1453 */         arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1454 */         arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1455 */         double ly0 = centerY * scaleY - centerY;
/* 1456 */         double lx0 = centerX * scaleX - centerX;
/* 1457 */         double arcx0 = arcx * scaleX - lx0;
/* 1458 */         double arcy0 = arcy * scaleY - ly0;
/* 1459 */         double lx = Math.abs(arcx0 - centerX);
/* 1460 */         double ly = Math.abs(arcy0 - centerY);
/* 1461 */         double r = Math.sqrt(Math.pow(arcx0 - centerX, 2.0D) + Math.pow(arcy0 - centerY, 2.0D));
/* 1462 */         double a1 = 0.0D;
/*     */ 
/* 1464 */         if ((arcx0 >= centerX) && (arcy0 < centerY))
/* 1465 */           a1 = Math.atan(ly / lx);
/* 1466 */         else if ((arcx0 <= centerX) && (arcy0 < centerY))
/* 1467 */           a1 = 1.570796326794897D + Math.atan(lx / ly);
/* 1468 */         else if ((arcx0 <= centerX) && (arcy0 >= centerY))
/* 1469 */           a1 = 3.141592653589793D + Math.atan(ly / lx);
/* 1470 */         else if ((arcx0 > centerX) && (arcy0 >= centerY)) {
/* 1471 */           a1 = 4.71238898038469D + Math.atan(lx / ly);
/*     */         }
/*     */ 
/* 1474 */         arcx0 = centerX + r;
/* 1475 */         arcy0 = centerY;
/*     */ 
/* 1477 */         arcangle = -(a1 + inputangle);
/* 1478 */         arcx = (arcx0 - centerX) * Math.cos(arcangle) - (arcy0 - centerY) * Math.sin(arcangle) + centerX;
/* 1479 */         arcy = -1.0D * (arcx0 - centerX) * Math.sin(arcangle) + (arcy0 - centerY) * Math.cos(arcangle) + centerY;
/* 1480 */         this.x[i] = (int)Math.round(arcx);
/* 1481 */         this.y[i] = (int)Math.round(arcy);
/* 1482 */         this.pointcount = 5;
/*     */       }
/*     */     }
/* 1485 */     else if (shapeType.equals("Hexagon")) {
/* 1486 */       initangle = 0.0D;
/* 1487 */       this.x = new int[6];
/* 1488 */       this.y = new int[6];
/* 1489 */       for (int i = 0; i <= 5; i++) {
/* 1490 */         arcangle = -(initangle + i * 3.141592653589793D * 60.0D / 180.0D);
/* 1491 */         arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1492 */         arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1493 */         double ly0 = centerY * scaleY - centerY;
/* 1494 */         double lx0 = centerX * scaleX - centerX;
/* 1495 */         double arcx0 = arcx * scaleX - lx0;
/* 1496 */         double arcy0 = arcy * scaleY - ly0;
/* 1497 */         double lx = Math.abs(arcx0 - centerX);
/* 1498 */         double ly = Math.abs(arcy0 - centerY);
/* 1499 */         double r = Math.sqrt(Math.pow(arcx0 - centerX, 2.0D) + Math.pow(arcy0 - centerY, 2.0D));
/* 1500 */         double a1 = 0.0D;
/*     */ 
/* 1502 */         if ((arcx0 >= centerX) && (arcy0 < centerY))
/* 1503 */           a1 = Math.atan(ly / lx);
/* 1504 */         else if ((arcx0 <= centerX) && (arcy0 < centerY))
/* 1505 */           a1 = 1.570796326794897D + Math.atan(lx / ly);
/* 1506 */         else if ((arcx0 <= centerX) && (arcy0 >= centerY))
/* 1507 */           a1 = 3.141592653589793D + Math.atan(ly / lx);
/* 1508 */         else if ((arcx0 > centerX) && (arcy0 >= centerY)) {
/* 1509 */           a1 = 4.71238898038469D + Math.atan(lx / ly);
/*     */         }
/*     */ 
/* 1512 */         arcx0 = centerX + r;
/* 1513 */         arcy0 = centerY;
/*     */ 
/* 1515 */         arcangle = -(a1 + inputangle);
/* 1516 */         arcx = (arcx0 - centerX) * Math.cos(arcangle) - (arcy0 - centerY) * Math.sin(arcangle) + centerX;
/* 1517 */         arcy = -1.0D * (arcx0 - centerX) * Math.sin(arcangle) + (arcy0 - centerY) * Math.cos(arcangle) + centerY;
/*     */ 
/* 1519 */         this.x[i] = (int)Math.round(arcx);
/* 1520 */         this.y[i] = (int)Math.round(arcy);
/* 1521 */         this.pointcount = 6;
/*     */       }
/*     */     }
/* 1524 */     else if (shapeType.equals("Octagon")) {
/* 1525 */       initangle = 0.0D;
/* 1526 */       this.x = new int[8];
/* 1527 */       this.y = new int[8];
/* 1528 */       for (int i = 0; i <= 7; i++) {
/* 1529 */         arcangle = -(initangle + i * 3.141592653589793D * 45.0D / 180.0D);
/* 1530 */         arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1531 */         arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1532 */         double ly0 = centerY * scaleY - centerY;
/* 1533 */         double lx0 = centerX * scaleX - centerX;
/* 1534 */         double arcx0 = arcx * scaleX - lx0;
/* 1535 */         double arcy0 = arcy * scaleY - ly0;
/* 1536 */         double lx = Math.abs(arcx0 - centerX);
/* 1537 */         double ly = Math.abs(arcy0 - centerY);
/* 1538 */         double r = Math.sqrt(Math.pow(arcx0 - centerX, 2.0D) + Math.pow(arcy0 - centerY, 2.0D));
/* 1539 */         double a1 = 0.0D;
/*     */ 
/* 1541 */         if ((arcx0 >= centerX) && (arcy0 < centerY))
/* 1542 */           a1 = Math.atan(ly / lx);
/* 1543 */         else if ((arcx0 <= centerX) && (arcy0 < centerY))
/* 1544 */           a1 = 1.570796326794897D + Math.atan(lx / ly);
/* 1545 */         else if ((arcx0 <= centerX) && (arcy0 >= centerY))
/* 1546 */           a1 = 3.141592653589793D + Math.atan(ly / lx);
/* 1547 */         else if ((arcx0 > centerX) && (arcy0 >= centerY)) {
/* 1548 */           a1 = 4.71238898038469D + Math.atan(lx / ly);
/*     */         }
/*     */ 
/* 1551 */         arcx0 = centerX + r;
/* 1552 */         arcy0 = centerY;
/*     */ 
/* 1554 */         arcangle = -(a1 + inputangle);
/* 1555 */         arcx = (arcx0 - centerX) * Math.cos(arcangle) - (arcy0 - centerY) * Math.sin(arcangle) + centerX;
/* 1556 */         arcy = -1.0D * (arcx0 - centerX) * Math.sin(arcangle) + (arcy0 - centerY) * Math.cos(arcangle) + centerY;
/* 1557 */         this.x[i] = (int)Math.round(arcx);
/* 1558 */         this.y[i] = (int)Math.round(arcy);
/* 1559 */         this.pointcount = 8;
/*     */       }
/*     */     }
/* 1562 */     else if (shapeType.equals("Heptagon")) {
/* 1563 */       initangle = 0.0D;
/* 1564 */       this.x = new int[7];
/* 1565 */       this.y = new int[7];
/* 1566 */       for (int i = 0; i <= 6; i++) {
/* 1567 */         arcangle = -(initangle + i * 3.141592653589793D * 51.399999999999999D / 180.0D);
/* 1568 */         arcx = (zeroAngleX - centerX) * Math.cos(arcangle) - (zeroAngleY - centerY) * Math.sin(arcangle) + centerX;
/* 1569 */         arcy = -1.0D * (zeroAngleX - centerX) * Math.sin(arcangle) + (zeroAngleY - centerY) * Math.cos(arcangle) + centerY;
/* 1570 */         double ly0 = centerY * scaleY - centerY;
/* 1571 */         double lx0 = centerX * scaleX - centerX;
/* 1572 */         double arcx0 = arcx * scaleX - lx0;
/* 1573 */         double arcy0 = arcy * scaleY - ly0;
/* 1574 */         double lx = Math.abs(arcx0 - centerX);
/* 1575 */         double ly = Math.abs(arcy0 - centerY);
/* 1576 */         double r = Math.sqrt(Math.pow(arcx0 - centerX, 2.0D) + Math.pow(arcy0 - centerY, 2.0D));
/* 1577 */         double a1 = 0.0D;
/*     */ 
/* 1579 */         if ((arcx0 >= centerX) && (arcy0 < centerY))
/* 1580 */           a1 = Math.atan(ly / lx);
/* 1581 */         else if ((arcx0 <= centerX) && (arcy0 < centerY))
/* 1582 */           a1 = 1.570796326794897D + Math.atan(lx / ly);
/* 1583 */         else if ((arcx0 <= centerX) && (arcy0 >= centerY))
/* 1584 */           a1 = 3.141592653589793D + Math.atan(ly / lx);
/* 1585 */         else if ((arcx0 > centerX) && (arcy0 >= centerY)) {
/* 1586 */           a1 = 4.71238898038469D + Math.atan(lx / ly);
/*     */         }
/*     */ 
/* 1589 */         arcx0 = centerX + r;
/* 1590 */         arcy0 = centerY;
/*     */ 
/* 1592 */         arcangle = -(a1 + inputangle);
/* 1593 */         arcx = (arcx0 - centerX) * Math.cos(arcangle) - (arcy0 - centerY) * Math.sin(arcangle) + centerX;
/* 1594 */         arcy = -1.0D * (arcx0 - centerX) * Math.sin(arcangle) + (arcy0 - centerY) * Math.cos(arcangle) + centerY;
/* 1595 */         this.x[i] = (int)Math.round(arcx);
/* 1596 */         this.y[i] = (int)Math.round(arcy);
/* 1597 */         this.pointcount = 7;
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\resouces\projects\DOG\测试\DOG3TEST20140331\DOG3TEST\DOG3.jar
 * Qualified Name:     gui.dog3.PolygonXY
 * JD-Core Version:    0.6.0
 */