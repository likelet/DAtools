/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pub;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * GTF9</p>
 * <p>
 * Created on 2015-12-24 10:55:58</p>
 * <p>
 * Author Email: zhaoqi3@mail2.sysu.edu.cn</p>
 *
 * @author ZHAO Qi
 * @date 2015-12-24 10:55:58
 * @version java 1.6.0
 * @version
 */
public class GTF9 {

    private String seqname;
    private String source;
    private String feature;
    private int start;
    private int end;
    private String score;
    private String strand;
    private String frame;
    private LinkedHashMap<String, String> attrbutemap = new LinkedHashMap<String, String>();
    public String attseq = "\\s";

    public GTF9(String seqname, String source, String feature, int start, int end, String score, String strand, String frame, String attrbute) {
        this.seqname = seqname;
        this.source = source;
        this.feature = feature;
        this.start = start;
        this.end = end;
        this.score = score;
        this.strand = strand;
        this.frame = frame;
        this.format(attrbute);
    }

    public void format(String attrbute) {
        String[] arr = attrbute.trim().split(";");
        for (int i = 0; i < arr.length; i++) {
            String[] tempa = arr[i].trim().split(attseq);
//            System.out.println(tempa[0]+"\t"+tempa[1]);
            attrbutemap.put(tempa[0], tempa[1]);

        }
    }

    public String getSeqname() {
        return seqname;
    }

    public void setSeqname(String seqname) {
        this.seqname = seqname;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getFrame() {
        return frame;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public String getAttrbute() {
        String str = "";
        for (Iterator iter = attrbutemap.entrySet().iterator(); iter.hasNext();) {
            Map.Entry element = (Map.Entry) iter.next();
            Object strKey = element.getKey();
            Object strObj = element.getValue();
            str += strKey + " " + strObj + ";";
        }
        return str;
    }

    public String getSpecificAttrbute(String key) {
        return this.attrbutemap.get(key).replace("\"", "");
    }

    public void setSpecificAttrbute(String key, String value) {
        this.attrbutemap.put(key, "\""+value+"\"");
    }

    @Override
    public String toString() {
        return seqname + "\t" + source + "\t" + feature + "\t" + start + "\t" + end + "\t" + score + "\t" + strand + "\t" + frame + "\t" + this.getAttrbute();
    }

}
