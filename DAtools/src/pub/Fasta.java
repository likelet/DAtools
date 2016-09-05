package pub;



/**
 * <p>Fasta.java</p>
 * <p>Copyright (c) 2007-2009. The CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 1.0
 */
public class Fasta {

    private String name;
    private String sequence;
    private String purename;

    public Fasta() {
    }

    
    public Fasta(String name, String sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setPureName(String name){
        this.purename=name;
    }
    public String getPureName() {
        return name.substring(1);
    }

    public String getSequence() {
        return sequence;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String toString() {
        return name + "\n" + sequence;
    }
}
