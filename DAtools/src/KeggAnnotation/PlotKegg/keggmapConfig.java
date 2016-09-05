/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KeggAnnotation.PlotKegg;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-30 10:51:58
 * @version 1.6.0
 */
public class keggmapConfig {

    private String shapeType;
    private String posionString; 
    private String url;
    private String keggIdString;

    public keggmapConfig(String shapeType, String posionString, String url, String keggIdString) {
        this.shapeType = shapeType;
        this.posionString = posionString;
        this.url = url;
        this.keggIdString = keggIdString;
    }

    
    
    
    /**
     * Get the value of keggIdString
     *
     * @return the value of keggIdString
     */
    public String getKeggIdString() {
        return keggIdString;
    }

    /**
     * Set the value of keggIdString
     *
     * @param keggIdString new value of keggIdString
     */
    public void setKeggIdString(String keggIdString) {
        this.keggIdString = keggIdString;
    }


    /**
     * Get the value of url
     *
     * @return the value of url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the value of url
     *
     * @param url new value of url
     */
    public void setUrl(String url) {
        this.url = url;
    }


    /**
     * Get the value of posionString
     *
     * @return the value of posionString
     */
    public String getPosionString() {
        return posionString;
    }

    /**
     * Set the value of posionString
     *
     * @param posionString new value of posionString
     */
    public void setPosionString(String posionString) {
        this.posionString = posionString;
    }

    /**
     * Get the value of shapeType
     *
     * @return the value of shapeType
     */
    public String getShapeType() {
        return shapeType;
    }

    /**
     * Set the value of shapeType
     *
     * @param shapeType new value of shapeType
     */
    public void setShapeType(String shapeType) {
        this.shapeType = shapeType;
    }

}
