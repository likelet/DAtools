package DOGProjectGenerator;


import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D.Double;


/**
 * <p>Component.java</P>
 * <p>Copyright (c) 2007-2009. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.0
 */
public class Component implements Cloneable {

    private ComponentEnum type;
    public double x;
    public int y;
    public double x2;
    public int y2;
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
    private Area area;
    private boolean isActive;
    private double centerx;
    private double centery;
    public double wide;
    public double high;
    public double rotationAngle;
    public ShapeEnum shapeType;
    private Color colorText;
    private Color colorline;
    /************�϶�����**************/
    public boolean bSelected = false;
    public Point.Double dStartPrevDrag;
    public Point.Double dEndPrevDrag;
    public Point.Double dStartComDrag; //�϶�,��������
    public Point.Double dEndComDrag; //�϶�����������
    public Point.Double dStartRotateDrag; //�϶���ת��ʼ���
    public Point.Double dEndRotateDrag; //�϶���ת��ֹ���
    public java.awt.geom.Area GeomArea = null;// ��ϵͳjava.awt.geom.Area,  Area���ܶ������״
    public java.awt.geom.Area GeomAnchor1 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊleft-top 
    public java.awt.geom.Area GeomAnchor2 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊright-top 
    public java.awt.geom.Area GeomAnchor3 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊleft-bottom   
    public java.awt.geom.Area GeomAnchor4 = null;// �ؼ��ĸ�ê�㣬�϶�mouse���������ţ���Ϊright-bottom   
    public java.awt.geom.Area GeomAnchorRotate = null;// �ؼ���תê�㣬�϶�mouse��������ת 
    public boolean bAnchor1Selected = false;
    public boolean bAnchor2Selected = false;
    public boolean bAnchor3Selected = false;
    public boolean bAnchor4Selected = false;
    public boolean bAnchorRotateSelected = false;
    public java.awt.geom.Area GeomAnchorE1 = null;// �ؼ��ı��е��ê�㣬�϶�mouse���������ţ���Ϊleft-top 
    public java.awt.geom.Area GeomAnchorE2 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊright-top 
    public java.awt.geom.Area GeomAnchorE3 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊleft-bottom   
    public java.awt.geom.Area GeomAnchorE4 = null;// �ؼ��ĸ��е�ê�㣬�϶�mouse���������ţ���Ϊright-bottom   
    public boolean bAnchorE1Selected = false;
    public boolean bAnchorE2Selected = false;
    public boolean bAnchorE3Selected = false;
    public boolean bAnchorE4Selected = false;
    public Area ControlArea1 = null;//Curve,Bezier,PolylineOne,
    public Area ControlArea2 = null;// Bezier, PolylineTwo
    public boolean bControlArea1Selected = false;
    public boolean bControlArea2Selected = false;
    public Area startArea = null;// �������
    public Area endArea = null;//  �յ�����
    public boolean bStartArea1Selected = false;
    public boolean bEndArea2Selected = false;
    //���������
    public PolygonXY pgxy = new PolygonXY();

    public int getIchoosesite() {
        return ichoosesite;
    }

    public void setIchoosesite(int ichoosesite) {
        this.ichoosesite = ichoosesite;
    }

    public java.util.List<SitePosition> getSp() {
        return sp;
    }

    public void setSp(java.util.List<SitePosition> sp) {
        this.sp = sp;
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

    public Double getdEndComDrag() {
        return dEndComDrag;
    }

    public void setdEndComDrag(Double dEndComDrag) {
        this.dEndComDrag = dEndComDrag;
    }

    public Double getdStartComDrag() {
        return dStartComDrag;
    }

    public void setdStartComDrag(Double dStartComDrag) {
        this.dStartComDrag = dStartComDrag;
    }

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

    public Double getdEndRotateDrag() {
        return dEndRotateDrag;
    }

    public void setdEndRotateDrag(Double dEndRotateDrag) {
        this.dEndRotateDrag = dEndRotateDrag;
    }

    public Double getdStartRotateDrag() {
        return dStartRotateDrag;
    }

    public void setdStartRotateDrag(Double dStartRotateDrag) {
        this.dStartRotateDrag = dStartRotateDrag;
    }

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

    public Area getControlArea1() {
        return ControlArea1;
    }

    public void setControlArea1(Area ControlArea1) {
        this.ControlArea1 = ControlArea1;
    }

    public Area getControlArea2() {
        return ControlArea2;
    }

    public void setControlArea2(Area ControlArea2) {
        this.ControlArea2 = ControlArea2;
    }

    public java.awt.geom.Area getGeomArea() {
        return GeomArea;
    }

    public void setGeomArea(java.awt.geom.Area GeomArea) {
        this.GeomArea = GeomArea;
    }

    public boolean isbControlArea1Selected() {
        return bControlArea1Selected;
    }

    public void setbControlArea1Selected(boolean bControlArea1Selected) {
        this.bControlArea1Selected = bControlArea1Selected;
    }

    public boolean isbControlArea2Selected() {
        return bControlArea2Selected;
    }

    public void setbControlArea2Selected(boolean bControlArea2Selected) {
        this.bControlArea2Selected = bControlArea2Selected;
    }

    public boolean isbEndArea2Selected() {
        return bEndArea2Selected;
    }

    public void setbEndArea2Selected(boolean bEndArea2Selected) {
        this.bEndArea2Selected = bEndArea2Selected;
    }

    public boolean isbSelected() {
        return bSelected;
    }

    public void setbSelected(boolean bSelected) {
        this.bSelected = bSelected;
    }

    public boolean isbStartArea1Selected() {
        return bStartArea1Selected;
    }

    public void setbStartArea1Selected(boolean bStartArea1Selected) {
        this.bStartArea1Selected = bStartArea1Selected;
    }

    public Double getdEndPrevDrag() {
        return dEndPrevDrag;
    }

    public void setdEndPrevDrag(Double dEndPrevDrag) {
        this.dEndPrevDrag = dEndPrevDrag;
    }

    public Double getdStartPrevDrag() {
        return dStartPrevDrag;
    }

    public void setdStartPrevDrag(Double dStartPrevDrag) {
        this.dStartPrevDrag = dStartPrevDrag;
    }

    public Area getEndArea() {
        return endArea;
    }

    public void setEndArea(Area endArea) {
        this.endArea = endArea;
    }

    public Area getStartArea() {
        return startArea;
    }

    public void setStartArea(Area startArea) {
        this.startArea = startArea;
    }

    public ShapeEnum getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeEnum shapeType) {
        this.shapeType = shapeType;
    }

    public double getCenterx() {
        return centerx;
    }

    public void setCenterx(double centerx) {
        this.centerx = centerx;
    }

    public double getCentery() {
        return centery;
    }

    public void setCentery(double centery) {
        this.centery = centery;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getRotationAngle() {
        return rotationAngle;
    }

    public void setRotationAngle(double rotationAngle) {
        this.rotationAngle = rotationAngle;
    }

    public double getWide() {
        return wide;
    }

    public void setWide(double wide) {
        this.wide = wide;
    }

    public Component(ComponentEnum type, int centerx, int centery, int wide, int high, int iRotationAngle, String parameter,
            Color color, ShapeEnum shapeType) {
        this.shapeType = shapeType;
        this.type = type;
        this.centerx = centerx;
        this.centery = centery;
        this.wide = wide;
        this.high = high;
        this.rotationAngle = iRotationAngle;
        this.parameter = parameter;
        this.color = color;


    }

    public Component(ComponentEnum type, int centerx, int centery, int wide, int high, int iRotationAngle, String parameter,
            Color color, Color colortext, Color colorline, ShapeEnum shapeType, boolean isdash) {
        // Polygon
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
        this.high = high;
        this.colorText = colortext;
        this.colorline = colorline;
    }

    public Component(ComponentEnum type, int x, int y, int x2, int y2, Integer cx1, Integer cy1, Integer cx2, Integer cy2, String parameter, Color color, ShapeEnum shapeType, boolean bDash) {
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

    public Component(ComponentEnum type, int x, int y, int x2, int y2, Integer cx1, Integer cy1, Integer cx2, Integer cy2, String parameter, Color color, Color colortext, ShapeEnum shapeType, boolean bDash) {
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

    public Component(ComponentEnum type, Color color, int centerx, int centery, int wide, int iRotationAngle, String parameter, ShapeEnum shapeType) {
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

    public Component(ComponentEnum type, int x, int y, String parameter, Color color, Color colortext, Color colorline, boolean isdash) {
        //domain
        this.bDash = isdash;
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = y;
        this.parameter = parameter;
        this.color = color;
        this.colorText = colortext;
        this.colorline = colorline;
        this.bProteinLDash = false;
        this.bProteinRDash = false;
        this.shapeType = ShapeEnum.Rectangle;
        this.rotationAngle = 0;
    }

    public Component(ComponentEnum type, double x, double y, String parameter, Color color, Color colortext, Color colorline,
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
        this.y = (int) y;
        this.parameter = parameter;
        this.color = color;

        this.high = iHigh;
        this.rotationAngle = iRotationAngle;
        //  this.bScale = ibScale;
        this.colorText = colortext;
        this.colorline = colorline;


    }

    public Component(ComponentEnum type, int x, int y, String parameter, Color color, Color colorline, boolean blinedash, int iRotationAngle ) {
//note
        this.type = type;
        this.x = x;
        this.y = y;
        this.parameter = parameter;
        this.color = color;
        this.colorline = colorline;
        this.bDash = blinedash;
        rotationAngle = iRotationAngle ;
    }

    public Component(ComponentEnum type, int x, int y, int x2, int y2, String parameter, Color color) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.parameter = parameter;
        this.color = color;
    }

    public Component(ComponentEnum type, int x, int y, String parameter, Color colortext, ShapeEnum shapeType, String stype,
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

        this.colorText = colortext;
        this.colorline = colorline;
    }

    public Object clone() throws CloneNotSupportedException {
        Component component = (Component) super.clone();
        component.sp = new java.util.LinkedList<SitePosition>();

        return component;
    }

    public java.awt.geom.Area getGeomAnchor1() {
        return GeomAnchor1;
    }

    public void setGeomAnchor1(java.awt.geom.Area GeomAnchor1) {
        this.GeomAnchor1 = GeomAnchor1;
    }

    public java.awt.geom.Area getGeomAnchor2() {
        return GeomAnchor2;
    }

    public void setGeomAnchor2(java.awt.geom.Area GeomAnchor2) {
        this.GeomAnchor2 = GeomAnchor2;
    }

    public java.awt.geom.Area getGeomAnchor3() {
        return GeomAnchor3;
    }

    public void setGeomAnchor3(java.awt.geom.Area GeomAnchor3) {
        this.GeomAnchor3 = GeomAnchor3;
    }

    public java.awt.geom.Area getGeomAnchor4() {
        return GeomAnchor4;
    }

    public void setGeomAnchor4(java.awt.geom.Area GeomAnchor4) {
        this.GeomAnchor4 = GeomAnchor4;
    }

    public java.awt.geom.Area getGeomAnchorE1() {
        return GeomAnchorE1;
    }

    public void setGeomAnchorE1(java.awt.geom.Area GeomAnchorE1) {
        this.GeomAnchorE1 = GeomAnchorE1;
    }

    public java.awt.geom.Area getGeomAnchorE2() {
        return GeomAnchorE2;
    }

    public void setGeomAnchorE2(java.awt.geom.Area GeomAnchorE2) {
        this.GeomAnchorE2 = GeomAnchorE2;
    }

    public java.awt.geom.Area getGeomAnchorE3() {
        return GeomAnchorE3;
    }

    public void setGeomAnchorE3(java.awt.geom.Area GeomAnchorE3) {
        this.GeomAnchorE3 = GeomAnchorE3;
    }

    public java.awt.geom.Area getGeomAnchorE4() {
        return GeomAnchorE4;
    }

    public void setGeomAnchorE4(java.awt.geom.Area GeomAnchorE4) {
        this.GeomAnchorE4 = GeomAnchorE4;
    }

    public java.awt.geom.Area getGeomAnchorRotate() {
        return GeomAnchorRotate;
    }

    public void setGeomAnchorRotate(java.awt.geom.Area GeomAnchorRotate) {
        this.GeomAnchorRotate = GeomAnchorRotate;
    }

    public boolean isbAnchor1Selected() {
        return bAnchor1Selected;
    }

    public void setbAnchor1Selected(boolean bAnchor1Selected) {
        this.bAnchor1Selected = bAnchor1Selected;
    }

    public boolean isbAnchor2Selected() {
        return bAnchor2Selected;
    }

    public void setbAnchor2Selected(boolean bAnchor2Selected) {
        this.bAnchor2Selected = bAnchor2Selected;
    }

    public boolean isbAnchor3Selected() {
        return bAnchor3Selected;
    }

    public void setbAnchor3Selected(boolean bAnchor3Selected) {
        this.bAnchor3Selected = bAnchor3Selected;
    }

    public boolean isbAnchor4Selected() {
        return bAnchor4Selected;
    }

    public void setbAnchor4Selected(boolean bAnchor4Selected) {
        this.bAnchor4Selected = bAnchor4Selected;
    }

    public boolean isbAnchorE1Selected() {
        return bAnchorE1Selected;
    }

    public void setbAnchorE1Selected(boolean bAnchorE1Selected) {
        this.bAnchorE1Selected = bAnchorE1Selected;
    }

    public boolean isbAnchorE2Selected() {
        return bAnchorE2Selected;
    }

    public void setbAnchorE2Selected(boolean bAnchorE2Selected) {
        this.bAnchorE2Selected = bAnchorE2Selected;
    }

    public boolean isbAnchorE3Selected() {
        return bAnchorE3Selected;
    }

    public void setbAnchorE3Selected(boolean bAnchorE3Selected) {
        this.bAnchorE3Selected = bAnchorE3Selected;
    }

    public boolean isbAnchorE4Selected() {
        return bAnchorE4Selected;
    }

    public void setbAnchorE4Selected(boolean bAnchorE4Selected) {
        this.bAnchorE4Selected = bAnchorE4Selected;
    }

    public boolean isbAnchorRotateSelected() {
        return bAnchorRotateSelected;
    }

    public void setbAnchorRotateSelected(boolean bAnchorRotateSelected) {
        this.bAnchorRotateSelected = bAnchorRotateSelected;
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

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String toString() {
        return type + "\t" + parameter + "\t" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + "\t"
                + area;
    }
}
// 
//enum ComponentEnum {
//    Protein, Domain, Site, Note, Line, Polygon, Mark,Bracket
//}
//
//enum ShapeEnum {
//  RoundRectangle,  Rectangle, Circle, Triangle, Rhomboid, Rhombus, Pentagon, Hexagon,Heptagon,  Octagon,Trapezoid,Arrow,DovetailArrow,
//   Brace, SquareBracket, Straight, PolylineOne, PolylineTwo, Curve, Bezier,Null
//    //Բ�Ƿ���,����,Բ,�����,ƽ���ı���,����,�����,�����,�߱���,�˱���,���Σ���ͷ,������,��(��)����,ֱ��,һ������,��������,����,���������
//}
//
//enum GradientEnum {
//    Null, West_Center, North_Center, North_West, West, South_West, South, South_East, East, North_East, North, Center, Border,Default
//}