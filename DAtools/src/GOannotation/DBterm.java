/*
 * this class is built to store each term details of the annotation database
 */
package GOannotation;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class DBterm {
    private String DB;//database contributing the file 
    private String DB_Object_ID;//unique identifier for genes
    private String DB_Object_Symbol;//genename
    private String NOT;//(optional)	- 'NOT' qualifier for a GO annotation, when needed
    private String GO_ID;//unique numeric identifier for the GO term
    private String DB_Reference;
    private String Evidence;//the evidence code for the GO annotation
    private String With_Form;// any With or From qualifier for the GO annotation    
    private String Aspect;//which ontology the GO term belongs (Function, Process or Component)
    private String DB_Object_Name;//	(optional)	- a name for the gene product in words, e.g. 'acid phosphatase'
    private String DB_Object_Synonym;// (optional)	- see below
    private String DB_Object_Type;//type of object annotated, e.g. gene, protein, etc.
    private String taxon;//taxonomic identifier of species encoding gene product
    private String date;
    private String Assigned_by;//source of the annotation

    public DBterm() {
    }

    public DBterm(String DB, String DB_Object_ID, String DB_Object_Symbol, String NOT, String GO_ID, String DB_Reference, String Evidence, String With_Form, String Aspect, String DB_Object_Name, String DB_Object_Synonym, String DB_Object_Type, String taxon, String date, String Assigned_by) {
        this.DB = DB;
        this.DB_Object_ID = DB_Object_ID;
        this.DB_Object_Symbol = DB_Object_Symbol;
        this.NOT = NOT;
        this.GO_ID = GO_ID;
        this.DB_Reference = DB_Reference;
        this.Evidence = Evidence;
        this.With_Form = With_Form;
        this.Aspect = Aspect;
        this.DB_Object_Name = DB_Object_Name;
        this.DB_Object_Synonym = DB_Object_Synonym;
        this.DB_Object_Type = DB_Object_Type;
        this.taxon = taxon;
        this.date = date;
        this.Assigned_by = Assigned_by;
    }
     
    public DBterm(String DB_Object_Symbol , String GO_ID){
        this.DB_Object_Symbol=DB_Object_Symbol;
        this.GO_ID=GO_ID;
    }
    
    
    
    //optional
    public String getDB_Object_Name() {
        return DB_Object_Name;
    }

    public void setDB_Object_Name(String DB_Object_Name) {
        this.DB_Object_Name = DB_Object_Name;
    }

    public String getDB_Object_Synonym() {
        return DB_Object_Synonym;
    }

    public void setDB_Object_Synonym(String DB_Object_Synonym) {
        this.DB_Object_Synonym = DB_Object_Synonym;
    }

    public String getNOT() {
        return NOT;
    }

    public void setNOT(String NOT) {
        this.NOT = NOT;
    }

    public String getWith_Form() {
        return With_Form;
    }

    public void setWith_Form(String With_Form) {
        this.With_Form = With_Form;
    }

  //required

    public String getAspect() {
        return Aspect;
    }

    public void setAspect(String Aspect) {
        this.Aspect = Aspect;
    }

    public String getAssigned_by() {
        return Assigned_by;
    }

    public void setAssigned_by(String Assigned_by) {
        this.Assigned_by = Assigned_by;
    }

    public String getDB() {
        return DB;
    }

    public void setDB(String DB) {
        this.DB = DB;
    }

    public String getDB_Object_ID() {
        return DB_Object_ID;
    }

    public void setDB_Object_ID(String DB_Object_ID) {
        this.DB_Object_ID = DB_Object_ID;
    }

    public String getDB_Object_Symbol() {
        return DB_Object_Symbol;
    }

    public void setDB_Object_Symbol(String DB_Object_Symbol) {
        this.DB_Object_Symbol = DB_Object_Symbol;
    }

    public String getDB_Object_Type() {
        return DB_Object_Type;
    }

    public void setDB_Object_Type(String DB_Object_Type) {
        this.DB_Object_Type = DB_Object_Type;
    }

    public String getDB_Reference() {
        return DB_Reference;
    }

    public void setDB_Reference(String DB_Reference) {
        this.DB_Reference = DB_Reference;
    }

    public String getEvidence() {
        return Evidence;
    }

    public void setEvidence(String Evidence) {
        this.Evidence = Evidence;
    }

    public String getGO_ID() {
        return GO_ID;
    }

    public void setGO_ID(String GO_ID) {
        this.GO_ID = GO_ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTaxon() {
        return taxon;
    }

    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }

    @Override
    public String toString() {
        return  DB + "\t" + DB_Object_ID + "\t" + DB_Object_Symbol + "\t" + NOT + "\t" + GO_ID + "\t" + DB_Reference + "\t" + Evidence + "\t" + With_Form + "\t" + Aspect + "\t" + DB_Object_Name + "\t" + DB_Object_Synonym + "\t" + DB_Object_Type + "\t" + taxon + "\t" + date + "\t=" + Assigned_by ;
    }
     public String toStringSimple(){
         return DB + "\t" + DB_Object_ID + "\t" + DB_Object_Symbol + "\t" + GO_ID +"\t"+Aspect;
     }
    
    
}
