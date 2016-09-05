package DOGProjectGenerator;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * <p>Protein.java</p>
 * <p>Created on Dec 19, 2010, 1:33:48 AM</p>
 * <p>Copyright (c) 2007-2010. The CUCKOO Workgroup, P.R.China</p>
 * @author Ren Jian
 * @version 3.9
 */
public class NProtein implements Cloneable {

    private int vertical;
    private int horizontal;
    private boolean isLocked;
    private int end;
    private String parameter;
    private Color color;
    public LinkedList<NComponent> componentList=new LinkedList<NComponent>();;
    private int selectNum;
   // private NArea area;
    private boolean isActive;
//  private boolean bDash ; 
//
//    public boolean isbDash() {
//        return bDash;
//    }
//
//    public void setbDash(boolean bDash) {
//        this.bDash = bDash;
//    }

    public boolean isIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public NProtein() {
    }

    
    public NProtein(int vertical, int horizontal, boolean isLocked, int end, String parameter, Color color) {

        this.vertical = vertical;
        this.horizontal = horizontal;
        this.isLocked = isLocked;
        this.end = end;
        this.parameter = parameter;
        this.color = color;
        

    }

    public Object clone() throws CloneNotSupportedException {
        NProtein protein = (NProtein) super.clone();
        LinkedList<NComponent> clonedComponentList = new LinkedList<NComponent>();
        for (Iterator<NComponent> it = componentList.iterator(); it.hasNext();) {
            NComponent component = it.next();
            clonedComponentList.add((NComponent) component.clone());
        }
        protein.setComponentList(clonedComponentList);
        return protein;
    }

    public int getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(int horizontal) {
        this.horizontal = horizontal;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getSelectNum() {
        return selectNum;
    }

    public void setSelectNum(int selectNum) {
        this.selectNum = selectNum;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public LinkedList<NComponent> getComponentList() {
        return componentList;
    }

    public void addComponent(NComponent component){
        this.componentList.add(component);
    }
    
    public void setComponentList(LinkedList<NComponent> componentList) {
        this.componentList = componentList;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getVertical() {
        return vertical;
    }

    public void setVertical(int vertical) {
        this.vertical = vertical;
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
}
