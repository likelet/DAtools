package pub;

import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 * <p>Filter.java</p>
 * <p>Created on Dec 29, 2008, 11:57:58 PM</p>
 * <p>Copyright (c) 2008. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 3.0
 */
public class Filter extends FileFilter {

    private String extention;
    private String discription;

    public Filter(String extention, String discription) {
        this.extention = extention;
        this.discription = discription;
    }

    public Filter(String extention) {
        this.extention = extention;
    }

    
    
    public boolean accept(File file) {
        return (file.getName().toLowerCase().endsWith("." + extention.toLowerCase()) || file.isDirectory());
    }

    public String getDescription() {
        return discription;
    }

    public String getExtention() {
        return extention;
    }
}
