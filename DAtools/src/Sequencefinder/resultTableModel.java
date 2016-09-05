/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sequencefinder;



import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
//import statics.MultipleTestingCorrections.BH;

/**
 *
 * @author ZHAO Qi
 * @date 2013-9-27 14:18:59
 * @version 1.6.0
 */ 
public class resultTableModel extends AbstractTableModel {
   
    private Object[][] Tmodel;
    private int matrixheight=0;
    private String[] titlelist;
    
    @Override
    public Class getColumnClass(int columnIndex){
        
            if(columnIndex==1||columnIndex==2||columnIndex==3){
                return Integer.class;
            }else {
                return String.class;
            }
//        return String.class;
//        Object value = getValueAt(0, columnIndex);
//        if(value!=null)
//            return value.toString().;
//        else return super.getClass();
    }
   public resultTableModel(ArrayList<OutputFormat> outputlist, String[] titlelist){
        Tmodel=new Object[outputlist.size()][5];
        this.matrixheight=outputlist.size();
        this.titlelist = titlelist;
       for (int i = 0; i < outputlist.size(); i++) {
           //System.out.println(tempoutput.get(i).toString());
           Tmodel[i][0] = outputlist.get(i).getSeq();
           Tmodel[i][1] = outputlist.get(i).getCount();
           Tmodel[i][2] = outputlist.get(i).getStart();
           Tmodel[i][3] = outputlist.get(i).getEnd();
           Tmodel[i][4] = outputlist.get(i).getgenestring();
           //TextOut.a(tempoutput.get(i).toString());
       }

//         pvaluelist=BH.BH(pvaluelist); 

         
   }
    
    @Override
    public int getRowCount() {
        return matrixheight;
    }

    public String getColumnName(int column ){
        return this.titlelist[column];
    }
    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return Tmodel[rowIndex][columnIndex];
    }
    
}
