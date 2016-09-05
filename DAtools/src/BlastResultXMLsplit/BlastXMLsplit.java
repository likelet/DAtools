/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BlastResultXMLsplit;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 *
 * @author ZHAO Qi
 * @date 2014-10-15 17:33:05
 * @version 1.6.0
 */
public class BlastXMLsplit {

    public BlastXMLsplit(String filepath, int seqnumber) throws FileNotFoundException, IOException, ParserConfigurationException {
        SAXReader reader = new SAXReader();
        reader.setValidation(false);  
        try {
         System.out.println("Xlmfile reading");   
        Document document = reader.read(new FileInputStream(filepath));//将XML文件读到document里面
            System.out.println("Xlmfile read done!");  
        org.dom4j.DocumentFactory DocumentFactory = new org.dom4j.DocumentFactory();

            ArrayList str = new ArrayList();//用来存储名字 测试用
            
            String fileoutpath ;

            org.dom4j.Element rootElm = document.getRootElement();//获得根节点
            File f = new File(filepath);
            int count = 1;
            List<org.dom4j.Element> blastOutput_iterations = rootElm.element("BlastOutput_iterations").elements();//获得根节点的一个子节点
            org.dom4j.Element BlastOutput_program = rootElm.element("BlastOutput_program");
            org.dom4j.Element BlastOutput_version = rootElm.element("BlastOutput_version");
            org.dom4j.Element BlastOutput_reference = rootElm.element("BlastOutput_reference");
            org.dom4j.Element BlastOutput_db = rootElm.element("BlastOutput_db");
            //从原来的父节点中删除
            BlastOutput_program.getParent().remove(BlastOutput_program);
            BlastOutput_version.getParent().remove(BlastOutput_version);
            BlastOutput_reference.getParent().remove(BlastOutput_reference);
            BlastOutput_db.getParent().remove(BlastOutput_db);
            // org.dom4j.Element BlastOutput_query_def=rootElm.element("BlastOutput_query-def");
            //org.dom4j.Element BBlastOutput_query_len=rootElm.element("BlastOutput_query-len");
            
            org.dom4j.Element BlastOutput_param = rootElm.element("BlastOutput_param");
            rootElm.remove(BlastOutput_param);
            
            String Iteration_queryIDstr, Iteration_queryDefstr, Iteration_query_len;
            List<org.dom4j.Element> Iterationlist = null;
            int size=blastOutput_iterations.size();
            System.out.println("Your query seqcount is "+size+"\r\n Start dividing your file");
            for (int i = 0; i < size; i = i + seqnumber) {
                //文件名
                fileoutpath = f.getParent() + System.getProperty("file.separator") + count + ".xml";
                count++;
                System.out.println("The "+count+" is located in "+fileoutpath);
                FileOutputStream fos = new FileOutputStream(fileoutpath);
                OutputFormat format = OutputFormat.createPrettyPrint();
                XMLWriter writer = new XMLWriter(fos, format);
                // XMLWriter writer = new XMLWriter(new FileOutputStream(fileoutpath));
                //w文本头数据获取
                org.dom4j.Element firstelement = blastOutput_iterations.get(i);
                Iteration_queryIDstr = firstelement.element("Iteration_query-ID").getText();
                Iteration_queryDefstr = firstelement.element("Iteration_query-def").getText();
                Iteration_query_len = firstelement.element("Iteration_query-len").getText();
                
                //org.dom4j.DocumentFactory DocumentFactory = new org.dom4j.DocumentFactory();

                Document document2 = DocumentHelper.createDocument();;
                org.dom4j.Element BlastOutputElement = document2.addElement("BlastOutput");
                //doc.setRootElement(BlastOutputElement);
                //添加文本头;
                BlastOutputElement.add(BlastOutput_program);
                BlastOutputElement.add(BlastOutput_version);
                BlastOutputElement.add(BlastOutput_reference);
                BlastOutputElement.add(BlastOutput_db);
                BlastOutputElement.addElement("BlastOutput_query-ID");
                BlastOutputElement.element("BlastOutput_query-ID").setText(Iteration_queryIDstr);
                BlastOutputElement.addElement("BlastOutput_query-def");
                BlastOutputElement.element("BlastOutput_query-def").setText(Iteration_queryDefstr);
                BlastOutputElement.addElement("BlastOutput_query-len");
                BlastOutputElement.element("BlastOutput_query-len").setText(Iteration_query_len);
//                Element BlastOutput_param_new=DocumentFactory.createElement("BlastOutput_param");
//                for (Iterator it = BlastOutput_param.elementIterator(); it.hasNext();) {
//                    Element tempele=(Element) it.next();
//                    tempele.getParent().remove(tempele);
//                    BlastOutput_param_new.add(tempele);
//                }
                BlastOutputElement.add(BlastOutput_param);
                //BlastOutputElement.add(BlastOutput_param);
                if (i + seqnumber < blastOutput_iterations.size()) {
                    Iterationlist = blastOutput_iterations.subList(i, i + seqnumber);
                } else {
                    Iterationlist = blastOutput_iterations.subList(i, blastOutput_iterations.size() - 1);
                }
                //System.out.println(Iterationlist.size());
                //重置query
                resetIterationlist(Iterationlist);
                //生成BlastOutput_iterations选项
                
                Element BlastOutput_iterations=DocumentFactory.createElement("BlastOutput_iterations");
                //BlastOutputElement.addAttribute("BlastOutput_iterations");
                //org.dom4j.Element BlastOutput_iterations = new org.dom4j.Element("BlastOutput_iterations");
                for (int j = 0; j < Iterationlist.size(); j++) {
                    Iterationlist.get(j).getParent().remove(Iterationlist.get(j));
                    //System.out.println(j);
                    BlastOutput_iterations.add(Iterationlist.get(j));
                }
                BlastOutputElement.add(BlastOutput_iterations);

                //写出文件
                writer.write(document2);
                writer.close();
                BlastOutput_program.getParent().remove(BlastOutput_program);
                BlastOutput_version.getParent().remove(BlastOutput_version);
                BlastOutput_reference.getParent().remove(BlastOutput_reference);
                BlastOutput_db.getParent().remove(BlastOutput_db);
                BlastOutput_param.getParent().remove(BlastOutput_param);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(BlastXMLsplit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //把iterator里面的编号进行重置
    public void resetIterationlist(List<org.dom4j.Element> Iterationlist) {
        for (int i = 0; i < Iterationlist.size(); i++) {
            Iterationlist.get(i).element("Iteration_iter-num").setText(String.valueOf(i + 1));
            Iterationlist.get(i).element("Iteration_query-ID").setText("Query_" + (i + 1));
        }

    }

    public static void main(String[] args) throws IOException, FileNotFoundException, ParserConfigurationException {
        new BlastXMLsplit("G:\\test.xml", 2);
    }

}
