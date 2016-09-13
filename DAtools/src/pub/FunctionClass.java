/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pub;

/**
 *
 * @author zhaoqi
 */
public class FunctionClass {
    //get parameter after one specified name
    public static String getArgsParameter(String[] args, String parameter){
        int marker=0;
        String str="";
        for (int i = 0; i < args.length-1; i++) {
            if(args[i].equalsIgnoreCase(parameter)){
                marker=1;
                str= args[i+1];
                break;
            }
        }
        if(marker==0||str.startsWith("-")){
            return null;
        }else{
            return str;
        }
    } 
    //get the specific str that behind str
    public static String getStringback(String rawstr, String sep,String markstr){
        String[] arr=rawstr.split(sep);
        int marker=0;
        String str="";
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i].equalsIgnoreCase(markstr)){
                marker=1;
                str= arr[i+1];
                break;
            }
        }
        if(marker==0||str.startsWith("-")){
            return null;
        }else{
            return str;
        }
    } 
    
    public static boolean isContainParameter(String[] args, String str){
        boolean is=false;
        for (int i = 0; i < args.length; i++) {
            if(args[i].equalsIgnoreCase(str)){
                is=true;
                break;
            }
            
        }
        return is;
    }
    
    public static void main(String[] args) {
        String[] a= {"A","B"};
        System.out.println(FunctionClass.getArgsParameter(a, "A"));
    }
}