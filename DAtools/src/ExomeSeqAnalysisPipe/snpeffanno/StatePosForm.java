/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ExomeSeqAnalysisPipe.snpeffanno;

/**
 *
 * @author 媛子
 */
public class StatePosForm {
    private String snpState;
    private int position;
    StatePosForm(String snpState,int position){
        this.snpState=snpState;
        this.position=position;
    }
    
    public String getSnpState(){
        return snpState;
    }
    
    public void setSnpState(String snpState){
        this.snpState=snpState;
    }
    
    public int getPosition(){
        return position;
    }
    
    public void setPosition(int position){
        this.position=position;
    }
    
    public String toString(){
        return snpState+"\t"+position;
    }
}
