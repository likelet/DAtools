package pub;



import java.io.*;
import java.util.*;
import javax.swing.text.JTextComponent;

/**
 * <p>Sequence2Fasta.java</P>
 * <p>Copyright (c) 2007-2009. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.8
 */
public class FastaReader {

    private BufferedReader br;
    private SequenceVerifier sequenceVerifier;

    public FastaReader(File file) {
        try {
            br = new BufferedReader(new FileReader(file));
            sequenceVerifier = new SequenceVerifier();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } 
    }

    public FastaReader(String innerFilePath) {
        try {
            br = new BufferedReader(new FileReader(new File(innerFilePath)));
            sequenceVerifier = new SequenceVerifier();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public FastaReader(JTextComponent inputComponent) {
        try {
            String sequence = inputComponent.getText().trim();
            DataInputStream input = new DataInputStream(new ByteArrayInputStream(sequence.getBytes()));
            br = new BufferedReader(new InputStreamReader(input));
            sequenceVerifier = new SequenceVerifier();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Analysis the input sequence and translate into FASTA format
     * @return FASTA LinkedList
     */
    
    public LinkedList getNfastaList() throws IOException{
        LinkedList fastaList = new LinkedList();
        Fasta fasta=new Fasta();
        int count=0;
        String tempstr="";
        while(br.ready()){
            count++;
            //System.out.println(count);
            tempstr=br.readLine();
            //String tempa[]=br.readLine().split(" ");
            if(tempstr.startsWith(">")){
                fastaList.add(fasta);
                //fasta.setPureName(tempa[0]);
                fasta.setName(tempstr);
            }else{
                fasta.setSequence(fasta.getSequence()+tempstr);
            }
        }
        fastaList.remove(0);
        
        return fastaList;
    }
    public LinkedList getFastaList() {
        LinkedList fastaList = new LinkedList();
        Fasta fasta;
        String name = "";
        String sequence = "";
        try {
            StringBuffer sb = new StringBuffer();
            while (br.ready()) {
                String lineStr = br.readLine().trim();
                if (!lineStr.equals("")) {
                    if (lineStr.startsWith(">")) {
                        //System.out.println(lineStr);
                        if (!sb.toString().equals("")) {
                            if (sequenceVerifier.verify(sb.toString())) {
                                sequence = sequenceVerifier.getFasta().getSequence();
                            } else {
                                sequence = null;
                            }
                            if (name.equals("")) {
                                fasta = new Fasta(">Unnamed Sequence", sequence);
                            } else {
                                fasta = new Fasta(name, sequence);
                            }
                            fastaList.add(fasta);
                            sb = new StringBuffer("");
                        }
                        name = lineStr.replaceAll("\t", " ");//replace Tab
                    } else {
                        sb.append(lineStr);
                    }
                }
            }
            if (!sb.toString().equals("")) {
                if (sequenceVerifier.verify(sb.toString())) {
                    sequence = sequenceVerifier.getFasta().getSequence();
                } else {
                    sequence = null;
                }
                fasta = new Fasta(name, sequence);
                fastaList.add(fasta);
                sb = new StringBuffer("");
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
            return fastaList;
        }
        return fastaList;
    }

    public HashMap getFastaMap() {
        HashMap map = new HashMap();
        LinkedList fastaList = getFastaList();
        for (Iterator it = fastaList.iterator(); it.hasNext();) {
            Fasta fasta = (Fasta) it.next();
            map.put(fasta.getPureName(), fasta.getSequence());
        }
        return map;
    }

    public LinkedList getMetaFastaList() {
        LinkedList fastaList = new LinkedList();
        Fasta fasta;
        String name = "";
        String sequence = "";
        try {
            StringBuffer sb = new StringBuffer("");
            while (br.ready()) {
                String lineStr = br.readLine().trim();
                if (!lineStr.equals("")) {
                    if (lineStr.startsWith(">")) {
                        if (!sb.toString().equals("")) {
                            sequence = sb.toString();
                            if (name.equals("")) {
                                fasta = new Fasta(">Unnamed Sequence", sequence);
                            } else {
                                fasta = new Fasta(name, sequence);
                            }
                            fastaList.add(fasta);
                            sb = new StringBuffer("");
                        }
                        name = lineStr;
                    } else {
                        sb.append(lineStr);
                    }
                }
            }
            if (!sb.toString().equals("")) {
                sequence = sb.toString();
                fasta = new Fasta(name, sequence);
                fastaList.add(fasta);
                sb = new StringBuffer("");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return fastaList;
    }

    public static void main(String[] args) {
//        new FastaReader(new File("E:/nitro.seq")).getFastaList();
        System.out.println("cacctcggttctatcg".toUpperCase());
    }
}
