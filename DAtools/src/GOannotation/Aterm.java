/*
 * the format of simple output
 */
package GOannotation;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class Aterm {
    private String DB="";//database contributing the file 
    private String DB_Object_ID="";//unique identifier for genes
    private String DB_Object_Symbol="";//genename
    private String GO_ID="";//unique numeric identifier for the GO term
    private String Aspect="";//which ontology the GO term belongs (Function, Process or Component)
    private String name="";//The term name. Any term may only have one name defined. If multiple term names are defined, it is a parse error. 
    private String namespace="";//The namespace in which the term belongs. If this tag is not present, the term will be assigned to the default-namespace specified in the file header stanza.

    public Aterm(String DB, String DB_Object_ID, String DB_Object_Symbol, String GO_ID, String Aspect, String name, String namespace) {
        this.DB = DB;
        this.DB_Object_ID = DB_Object_ID;
        this.DB_Object_Symbol = DB_Object_Symbol;
        this.GO_ID = GO_ID;
        this.Aspect = Aspect;
        this.name = name;
        this.namespace = namespace;
    }
    public Aterm(String DB_Object_Symbol,String GO_ID){
        this.DB_Object_Symbol = DB_Object_Symbol;
        this.GO_ID = GO_ID;
        
    }

    public String getAspect() {
        return Aspect;
    }

    public void setAspect(String Aspect) {
        this.Aspect = Aspect;
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

    public String getGO_ID() {
        return GO_ID;
    }

    public void setGO_ID(String GO_ID) {
        this.GO_ID = GO_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    @Override
    public String toString() {
        return  DB + "\t" + DB_Object_ID + "\t" + DB_Object_Symbol + "\t" + GO_ID + "\t"+ Aspect +"\t" + name + "\t"+ namespace ;
    }
     public String toString2() {
        return   DB_Object_Symbol + "\t" + GO_ID ;
    }

    

    
    
    
    
    
}
