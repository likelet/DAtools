package DOGProjectGenerator;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D.Double;
import java.util.LinkedList;

/**
 * <p>Component.java</P>
 * <p>Copyright (c) 2007-2009. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.0
 */
public class NComponent implements Cloneable {

    private ComponentEnum type;
    private double x;
    private int y;
    private double x2;
    private int y2;
    private int x3;
    private int y3;
    /*****siteλ�㣬�϶�ʱ��*****/
    public java.util.List<SitePosition> sp = new java.util.LinkedList<SitePosition>();
    public int ichoosesite = -1;//ѡ���siteλ��λ��֮sp������
    /***����****/
    public boolean bDash = false;
    public boolean bProteinLDash = false;//domain ��ߵ�protein����
    public boolean bProteinRDash = false;//domain �ұߵ�protein����
    /***�������Ƶ�****/
    //Straight-0��, PolylineOne-1��, PolylineTwo-2��, Curve-1��, Bezier-2��
    private int controlx1;
    private int controly1;
    private int controlx2;
    private int controly2;
    /***�������Ƶ�****/
    public String parameter;
    private Color color;
    //private NArea area;
    private boolean isActive;
    public ShapeEnum shapeType;
    public double iHigh;
    public boolean bScale;
    private int centerx;
    private int centery;
    public double wide;
    public double high;
    public double rotationAngle=0;
    private Color colorText;
    private Color colorline;
    /************�϶� **************/
//    public boolean bSelected = false;
//    public Point.Double dStartPrevDrag; //�϶�
//    public Point.Double dEndPrevDrag; //�϶�
//    public Point.Double dStartComDrag; //�϶�,��������
//    public Point.Double dEndComDrag; //�϶�����������
//    public Point.Double dStartRotateDrag; //�϶���ת��ʼ���
//    public Point.Double dEndRotateDrag; //�϶���ת��ֹ���
//    public java.awt.geom.Area GeomArea = null;// ��ϵͳjava.awt.geom.Area, ShapeArea���ܶ������״
//    public java.awt.geom.Area GeomAnchor1 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊleft-top 
//    public java.awt.geom.Area GeomAnchor2 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊright-top 
//    public java.awt.geom.Area GeomAnchor3 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊleft-bottom   
//    public java.awt.geom.Area GeomAnchor4 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊright-bottom   
//    public java.awt.geom.Area GeomAnchorRotate = null;// �ؼ���תê�㣬�϶�mouse��������ת 
//    public boolean bAnchor1Selected = false;
//    public boolean bAnchor2Selected = false;
//    public boolean bAnchor3Selected = false;
//    public boolean bAnchor4Selected = false;
//    public boolean bAnchorRotateSelected = false;
//    public java.awt.geom.Area GeomAnchorE1 = null;// �ؼ��ı��е��ê�㣬�϶�mouse���������ţ���Ϊleft-top 
//    public java.awt.geom.Area GeomAnchorE2 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊright-top 
//    public java.awt.geom.Area GeomAnchorE3 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊleft-bottom   
//    public java.awt.geom.Area GeomAnchorE4 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊright-bottom   
//    public boolean bAnchorE1Selected = false;
//    public boolean bAnchorE2Selected = false;
//    public boolean bAnchorE3Selected = false;
//    public boolean bAnchorE4Selected = false;
    ;
    //
//    public NArea ControlArea1 = null;//Curve,Bezier,PolylineOne,
//    public NArea ControlArea2 = null;// Bezier, PolylineTwo
//    public boolean bControlArea1Selected = false;
//    public boolean bControlArea2Selected = false;
////    public NArea startArea = null;// �������
////    public NArea endArea = null;//  �յ�����
//    public boolean bStartArea1Selected = false;
//    public boolean bEndArea2Selected = false;
//    public java.awt.geom.GeneralPath geomGeneralPath = null;
    //���������
    public PolygonXY pgxy = new PolygonXY();

    public int getIchoosesite() {
        return ichoosesite;
    }

    public void setIchoosesite(int ichoosesite) {
        this.ichoosesite = ichoosesite;
    }

    public boolean isbProteinLDash() {
        return bProteinLDash;
    }

    public void setbProteinLDash(boolean bProteinLDash) {
        this.bProteinLDash = bProteinLDash;
    }

    public boolean isbProteinRDash() {
        return bProteinRDash;
    }

    public void setbProteinRDash(boolean bProteinRDash) {
        this.bProteinRDash = bProteinRDash;
    }

    public PolygonXY getPgxy() {
        return pgxy;
    }

    public void setPgxy(PolygonXY pgxy) {
        this.pgxy = pgxy;
    }
//
//    public Double getdEndComDrag() {
//        return dEndComDrag;
//    }
//
//    public void setdEndComDrag(Double dEndComDrag) {
//        this.dEndComDrag = dEndComDrag;
//    }
//
//    public Double getdStartComDrag() {
//        return dStartComDrag;
//    }
//
//    public void setdStartComDrag(Double dStartComDrag) {
//        this.dStartComDrag = dStartComDrag;
//    }
//
//    public Area getGeomAnchor1() {
//        return GeomAnchor1;
//    }
//
//    public void setGeomAnchor1(Area GeomAnchor1) {
//        this.GeomAnchor1 = GeomAnchor1;
//    }
//
//    public Area getGeomAnchor2() {
//        return GeomAnchor2;
//    }
//
//    public void setGeomAnchor2(Area GeomAnchor2) {
//        this.GeomAnchor2 = GeomAnchor2;
//    }
//
//    public Area getGeomAnchor3() {
//        return GeomAnchor3;
//    }
//
//    public void setGeomAnchor3(Area GeomAnchor3) {
//        this.GeomAnchor3 = GeomAnchor3;
//    }
//
//    public Area getGeomAnchor4() {
//        return GeomAnchor4;
//    }
//
//    public void setGeomAnchor4(Area GeomAnchor4) {
//        this.GeomAnchor4 = GeomAnchor4;
//    }
//
//    public Area getGeomAnchorRotate() {
//        return GeomAnchorRotate;
//    }
//
//    public void setGeomAnchorRotate(Area GeomAnchorRotate) {
//        this.GeomAnchorRotate = GeomAnchorRotate;
//    }
//
//    public boolean isbAnchor1Selected() {
//        return bAnchor1Selected;
//    }
//
//    public void setbAnchor1Selected(boolean bAnchor1Selected) {
//        this.bAnchor1Selected = bAnchor1Selected;
//    }
//
//    public boolean isbAnchor2Selected() {
//        return bAnchor2Selected;
//    }
//
//    public void setbAnchor2Selected(boolean bAnchor2Selected) {
//        this.bAnchor2Selected = bAnchor2Selected;
//    }
//
//    public boolean isbAnchor3Selected() {
//        return bAnchor3Selected;
//    }
//
//    public void setbAnchor3Selected(boolean bAnchor3Selected) {
//        this.bAnchor3Selected = bAnchor3Selected;
//    }
//
//    public boolean isbAnchor4Selected() {
//        return bAnchor4Selected;
//    }
//
//    public void setbAnchor4Selected(boolean bAnchor4Selected) {
//        this.bAnchor4Selected = bAnchor4Selected;
//    }
//
//    public boolean isbAnchorRotateSelected() {
//        return bAnchorRotateSelected;
//    }
//
//    public void setbAnchorRotateSelected(boolean bAnchorRotateSelected) {
//        this.bAnchorRotateSelected = bAnchorRotateSelected;
//    }

    public boolean isbDash() {
        return bDash;
    }

    public void setbDash(boolean bDash) {
        this.bDash = bDash;
    }

    public Color getColorText() {
        return colorText;
    }

    public void setColorText(Color colorText) {
        this.colorText = colorText;
    }

    public Color getColorline() {
        return colorline;
    }

    public void setColorline(Color colorline) {
        this.colorline = colorline;
    }

//    public GeneralPath getGeomGeneralPath() {
//        return geomGeneralPath;
//    }
//
//    public void setGeomGeneralPath(GeneralPath geomGeneralPath) {
//        this.geomGeneralPath = geomGeneralPath;
//    }
//
//    public Double getdEndRotateDrag() {
//        return dEndRotateDrag;
//    }
//
//    public void setdEndRotateDrag(Double dEndRotateDrag) {
//        this.dEndRotateDrag = dEndRotateDrag;
//    }
//
//    public Double getdStartRotateDrag() {
//        return dStartRotateDrag;
//    }
//
//    public void setdStartRotateDrag(Double dStartRotateDrag) {
//        this.dStartRotateDrag = dStartRotateDrag;
//    }

//    public boolean isbStartArea1Selected() {
//        return bStartArea1Selected;
//    }
//
//    public void setbStartArea1Selected(boolean bStartArea1Selected) {
//        this.bStartArea1Selected = bStartArea1Selected;
//    }
//
//    public NArea getControlArea1() {
//        return ControlArea1;
//    }
//
//    public void setControlArea1(NArea ControlArea1) {
//        this.ControlArea1 = ControlArea1;
//    }
//
//    public NArea getControlArea2() {
//        return ControlArea2;
//    }
//
//    public void setControlArea2(NArea ControlArea2) {
//        this.ControlArea2 = ControlArea2;
//    }

//    public Area getGeomArea() {
//        return GeomArea;
//    }
//
//    public void setGeomArea(Area GeomArea) {
//        this.GeomArea = GeomArea;
//    }
////
//    public boolean isbControlArea1Selected() {
//        return bControlArea1Selected;
//    }
//
//    public void setbControlArea1Selected(boolean bControlArea1Selected) {
//        this.bControlArea1Selected = bControlArea1Selected;
//    }
//
//    public boolean isbControlArea2Selected() {
//        return bControlArea2Selected;
//    }
//
//    public void setbControlArea2Selected(boolean bControlArea2Selected) {
//        this.bControlArea2Selected = bControlArea2Selected;
//    }
//
//    public boolean isbEndArea2Selected() {
//        return bEndArea2Selected;
//    }
//
//    public void setbEndArea2Selected(boolean bEndArea2Selected) {
//        this.bEndArea2Selected = bEndArea2Selected;
//    }

//    public boolean isbSelected() {
//        return bSelected;
//    }
//
//    public void setbSelected(boolean bSelected) {
//        this.bSelected = bSelected;
//    }
//
//    public Double getdEndPrevDrag() {
//        return dEndPrevDrag;
//    }
//
//    public void setdEndPrevDrag(Double dEndPrevDrag) {
//        this.dEndPrevDrag = dEndPrevDrag;
//    }
//
//    public Double getdStartPrevDrag() {
//        return dStartPrevDrag;
//    }
//
//    public void setdStartPrevDrag(Double dStartPrevDrag) {
//        this.dStartPrevDrag = dStartPrevDrag;
//    }

//    public NArea getEndArea() {
//        return endArea;
//    }
//
//    public void setEndArea(NArea endArea) {
//        this.endArea = endArea;
//    }
//
//    public NArea getStartArea() {
//        return startArea;
//    }
//
//    public void setStartArea(NArea startArea) {
//        this.startArea = startArea;
//    }

    /********************************/
    public int getControlx1() {
        return controlx1;
    }

    public void setControlx1(int controlx1) {
        this.controlx1 = controlx1;
    }

    public int getControlx2() {
        return controlx2;
    }

    public void setControlx2(int controlx2) {
        this.controlx2 = controlx2;
    }

    public int getControly1() {
        return controly1;
    }

    public void setControly1(int controly1) {
        this.controly1 = controly1;
    }

    public int getControly2() {
        return controly2;
    }

    public void setControly2(int controly2) {
        this.controly2 = controly2;
    }

    public ShapeEnum getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeEnum shapeType) {
        this.shapeType = shapeType;
    }

    public NComponent(ComponentEnum type, int x, int y, String parameter, Color color, Color colorline, boolean blinedash,int iRotationAngle) {
//note
        this.type = type;
        this.x = x;
        this.y = y;
        this.parameter = parameter;
        this.color = color;
        this.colorline = colorline;
        this.bDash = blinedash;
        this.rotationAngle =  iRotationAngle;

    }

    public NComponent(ComponentEnum type, int x, int x2, int y1, String parameter, Color color) {

        this.type = type;
        this.x = x;
        this.x2 = x2;
        this.y = y1;
        this.parameter = parameter;
        this.color = color;

    }

    public NComponent(ComponentEnum type, int x, int y, String parameter, Color color, ShapeEnum shapeType) {
        this.shapeType = shapeType;
        this.type = type;
        this.x = x;
        this.x2 = y;
        this.parameter = parameter;
        this.color = color;

    }

    public NComponent(ComponentEnum type, int x, int y, String parameter, Color colortext, ShapeEnum shapeType, String stype,
            int wide, int high, int iRotationAngle, Color color, Color colorline, boolean isdash) {
        // site
        this.bDash = isdash;
        this.shapeType = shapeType;
        this.type = type;
        this.x = x;
        this.y = y;
        this.parameter = parameter;
        this.color = color;
        this.shapeType = shapeType;
        this.type = type;
        this.centerx = x;
        this.centery = y;
        this.wide = wide;
        this.high = high;

        this.rotationAngle = iRotationAngle;
        this.iHigh = high;
        this.colorText = colortext;
        this.colorline = colorline;
    }

    public NComponent(ComponentEnum type, int x, int y, String parameter, Color color, ShapeEnum shapeType, int iHigh, int iRotationAngle) {
        //domain
        this.shapeType = shapeType;
        this.type = type;
        this.x = x;
        this.x2 = y;
        this.parameter = parameter;
        this.color = color;
        this.iHigh = iHigh;
        this.rotationAngle = iRotationAngle;
        //  this.bScale = ibScale;

    }

    public NComponent(ComponentEnum type, double x, double y, String parameter, Color color, Color colortext, Color colorline,
            ShapeEnum shapeType, double iHigh, double iRotationAngle,
            boolean bleftd, boolean brightd, boolean borderdash) {
        //domain
        this.bDash = borderdash;
        this.bProteinLDash = bleftd;
        this.bProteinRDash = brightd;
        this.shapeType = shapeType;
        this.type = type;
        this.x = x;
        this.x2 = y;
        this.parameter = parameter;
        this.color = color;
        this.iHigh = iHigh;
        this.high = iHigh;
        this.rotationAngle = iRotationAngle;
        //  this.bScale = ibScale;
        this.colorText = colortext;
        this.colorline = colorline;

    }
//     public ShapeComponent(ComponentEnum type, int x, int y, String parameter, Color color, ShapeEnum shapeType, int iHigh, int rotationAngle  ) {
//        this.shapeType = shapeType;
//        this.type = type;
//        this.x = x;
//        this.x2 = y;
//        this.parameter = parameter;
//        this.color = color;
//        this.iHigh = iHigh;
//            this.rotationAngle = rotationAngle;
//     //   this.bScale = ibScale;
//      
//    }

    public NComponent(ComponentEnum type, int x1, int x2, int y1, int y2, String parameter, Color color, ShapeEnum shapeType, int iHigh, int iRotationAngle) {
        this.shapeType = shapeType;
        this.type = type;
        this.x = x1;
        this.x2 = x2;
        this.y = y1;
        this.y2 = y2;
        this.parameter = parameter;
        this.color = color;
        this.iHigh = iHigh;
        this.rotationAngle = iRotationAngle;


    }
// public NComponent(ComponentEnum type, int x1, int x2, int y1, int y2, String parameter, Color color, Color colortext, Color colorline, ShapeEnum shapeType, int iHigh, int iRotationAngle) {
//        this.shapeType = shapeType;
//        this.type = type;
//        this.x = x1;
//        this.x2 = x2;
//        this.y = y1;
//        this.y2 = y2;
//        this.parameter = parameter;
//        this.color = color;
//        this.iHigh = iHigh;
//        this.rotationAngle = iRotationAngle;
//            this.colorText=colortext;
//        this.colorline=colorline;
//
//    }
// 

    public NComponent(ComponentEnum type, int centerx, int centery, int wide, int high, int iRotationAngle, String parameter, Color color, ShapeEnum shapeType) {
        this.shapeType = shapeType;
        this.type = type;
        this.centerx = centerx;
        this.centery = centery;
        this.wide = wide;
        this.high = high;
        this.rotationAngle = iRotationAngle;
        this.parameter = parameter;
        this.color = color;
        this.iHigh = high;

    }

    public NComponent(ComponentEnum type, int centerx, int centery, int wide, int high, int iRotationAngle, String parameter, Color color,
            Color colortext, Color colorline, ShapeEnum shapeType, boolean isdash) {

        //  Polygon
        this.bDash = isdash;
        this.shapeType = shapeType;
        this.type = type;
        this.centerx = centerx;
        this.centery = centery;
        this.wide = wide;
        this.high = high;
        this.rotationAngle = iRotationAngle;
        this.parameter = parameter;
        this.color = color;
        this.iHigh = high;
        this.colorText = colortext;
        this.colorline = colorline;
    }

    public NComponent(ComponentEnum type, Color color, int centerx, int centery, int wide, int iRotationAngle, String parameter, ShapeEnum shapeType) {
        //������  
        this.type = type;
        this.centerx = centerx;
        this.centery = centery;
        this.wide = wide;
        this.rotationAngle = iRotationAngle;
        this.parameter = parameter;
        this.color = color;
        this.shapeType = shapeType;
    }

    public NComponent(ComponentEnum type, int x, int y, int x2, int y2, Integer cx1, Integer cy1, Integer cx2, Integer cy2, String parameter, Color color, Color colortext, ShapeEnum shapeType, boolean bDash) {
//��չ���ע������
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.parameter = parameter;
        this.color = color;
        this.colorText = colortext;
        this.shapeType = shapeType;
        this.bDash = bDash;
        if (shapeType == ShapeEnum.PolylineOne || shapeType == ShapeEnum.Curve) {//һ�������ߺ�����
            if (cx1 == null) {
                this.controlx1 = x + (x2 - x) / 2;
                this.controly1 = y + (y2 - y) / 2;
            } else {
                this.controlx1 = (int) cx1;
                this.controly1 = (int) cy1;
            }
        } else if (shapeType == ShapeEnum.PolylineTwo || shapeType == ShapeEnum.Bezier) {//���������ߺͱ��������
            if (cx1 == null && cx2 == null) {
                this.controlx1 = x + (x2 - x) / 3;
                this.controly1 = y + (y2 - y) / 3;
                this.controlx2 = x2 - (x2 - x) / 3;
                this.controly2 = y2 - (y2 - y) / 3;
            } else {
                this.controlx1 = (int) cx1;
                this.controly1 = (int) cy1;
                this.controlx2 = (int) cx2;
                this.controly2 = (int) cy2;
            }
        }

    }

    public NComponent(ComponentEnum type, int x, int y, int x2, int y2, Integer cx1, Integer cy1, Integer cx2, Integer cy2, String parameter, Color color, ShapeEnum shapeType, boolean bDash) {
//��չ����
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.parameter = parameter;
        this.color = color;
        this.shapeType = shapeType;
        this.bDash = bDash;
        if (shapeType == ShapeEnum.PolylineOne || shapeType == ShapeEnum.Curve) {//һ�������ߺ�����
            if (cx1 == null) {
                this.controlx1 = x + (x2 - x) / 2;
                this.controly1 = y + (y2 - y) / 2;
            } else {
                this.controlx1 = (int) cx1;
                this.controly1 = (int) cy1;
            }
        } else if (shapeType == ShapeEnum.PolylineTwo || shapeType == ShapeEnum.Bezier) {//���������ߺͱ��������
            if (cx1 == null && cx2 == null) {
                this.controlx1 = x + (x2 - x) / 3;
                this.controly1 = y + (y2 - y) / 3;
                this.controlx2 = x2 - (x2 - x) / 3;
                this.controly2 = y2 - (y2 - y) / 3;
            } else {
                this.controlx1 = (int) cx1;
                this.controly1 = (int) cy1;
                this.controlx2 = (int) cx2;
                this.controly2 = (int) cy2;
            }
        }
    }

    public NComponent(ComponentEnum type, int x, int y, int x2, int y2, String parameter, Color color) {
        //  this.shapeType = shapeType;

        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.parameter = parameter;
        this.color = color;

    }

    public NComponent(ComponentEnum type, int x, int y, int x2, int y2, int x3, int y3, String parameter, Color color) {
        //  this.shapeType = shapeType;
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.parameter = parameter;
        this.color = color;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getiHigh() {
        return iHigh;
    }

    public void setiHigh(double iHigh) {
        this.iHigh = iHigh;
    }

    public double getWide() {
        return wide;
    }

    public void setWide(double wide) {
        this.wide = wide;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public Object clone() throws CloneNotSupportedException {
        NComponent component = (NComponent) super.clone();
        component.sp = new java.util.LinkedList<SitePosition>();
        return component;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(int x3) {
        this.x3 = x3;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(int y3) {
        this.y3 = y3;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public ComponentEnum getType() {
        return type;
    }

    public String getParameter() {
        return parameter;
    }

    public Color getColor() {
        return color;
    }

//    public NArea getArea() {
//        return area;
//    }
//
//    public void setArea(NArea area) {
//        this.area = area;
//    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

//    public String toString() {
//        return type + "\t" + parameter + "\t" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\t"
//                + area;
//    }

    public boolean isbScale() {
        return bScale;
    }

    public void setbScale(boolean bScale) {
        this.bScale = bScale;
    }

    public int getCenterx() {
        return centerx;
    }

    public void setCenterx(int centerx) {
        this.centerx = centerx;
    }

    public int getCentery() {
        return centery;
    }

    public void setCentery(int centery) {
        this.centery = centery;
    }
}
//enum ComponentEnum {
//
//    Protein, Domain, Site, Note, Line, Polygon, Mark, Bracket
//}
//enum ShapeEnum {
//
//    RoundRectangle, Rectangle, Circle, Triangle, Rhomboid, Rhombus, Pentagon, Hexagon, Heptagon, Octagon, Trapezoid, Arrow, DovetailArrow,
//    Brace, SquareBracket, Straight, PolylineOne, PolylineTwo, Curve, Bezier,Null
//    //Բ�Ƿ���,����,Բ,�����,ƽ���ı���,����,�����,�����,�߱���Heptagon,�˱���,����,��ͷ,������,��(��)����,ֱ��,һ������,��������,����,���������
//}
//enum GradientEnum {
//
//    Null, West_Center, North_Center, North_West, West, South_West, South, South_East, East, North_East, North, Center, Border, Default
//}
//enum FillLineEnum {
//
//    Null, Grid, Horizontal, Vertical, Left_Slash, Right_Slash, Grid_Slash,
//    Grid_dashed, Horizontal_dashed, Vertical_dashed,
//    Left_Slash_dashed, Right_Slash_dashed, Grid_Slash_dashed
//}
//null, 00001.jpg,00002.jpg,00003.jpg,00005.jpg,00004.jpg,00006.jpg,
//00001_d.jpg,00002_d.jpg,00003_d.jpg,00005_d.jpg,00004_d.jpg,
//00005_d.jpg,00004_d.jpg,00006_d.jpg