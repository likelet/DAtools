/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-24 16:40:05
 * @version 1.6.0
 */
public class MyFilenameFilter implements FilenameFilter {

    private String type;

    public MyFilenameFilter(String tp) {

        this.type = tp;

    }

    @Override
    public boolean accept(File dir, String name) {
        File file=new File(name);

        String filename=file.getName();

        return filename.indexOf(type)!=-1;
    }

}
