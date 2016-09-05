package DOGProjectGenerator;

import java.util.LinkedList;

/**
 * <p>Project.java</p>
 * <p>Created on Apr 30, 2011, 10:42:19 AM</p>
 * <p>Copyright (c) 2007-2011. The CUCKOO Workgroup, P.R.China</p>
 * @author Ren Jian
 * @version 4.1
 */
public class NProject {

    private int width;
    private int height;
    private float rate;
    public String version = "DOG 3.0";
    private LinkedList<NProtein> proteinList;

    public NProject(int width, int height, float rate, LinkedList<NProtein> proteinList) {
        this.width = width;
        this.height = height;
        this.rate = rate;
        this.proteinList = proteinList;
    }

    public float getRate() {
        return rate;
    }

    int getHeight() {
        return height;
    }

    public LinkedList<NProtein> getProteinList() {
        return proteinList;
    }

    int getWidth() {
        return width;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
