/*
 * this class defines each GOterm details;
 */
package GOannotation;

import java.util.HashMap;

/**
 *
 * @author ZHAO Qi
 * @version jdk 1.7.0
 */
public class Oboterm {
    private String id="";//The unique id of the current term. This can be any string. This tag must always be the first tag in any term description 
    private String name="";//The term name. Any term may only have one name defined. If multiple term names are defined, it is a parse error. 
    private String alt_id="";//Defines an alternate id for this term. A term may have any number of alternate ids.
    private String namespace="";//The namespace in which the term belongs. If this tag is not present, the term will be assigned to the default-namespace specified in the file header stanza.
    private String def="";//The definition of the current term. There must be zero or one instances of this tag per term description. More than one definition for a term generates a parse error. The value of this tag should be the quote enclosed definition text, followed by a dbxref list containing dbxrefs that describe the origin of this definition (see Dbxref Formatting for information on how dbxref lists are encoded). 
    private String comment="";//A comment for this term. There must be zero or one instances of this tag per term description. More than one comment for a term generates a parse error. 
    private String is_a="";//This tag describes a subclassing relationship between one term and another. A term may have any number of is a relationships. Terms with no is a relationships are roots. A term with no is a relationships may not specify any relationship tags. To do so is a parse error. 
    private String subset="";//This tag indicates a term subset to which this term belongs. The value of this tag must be a subset name as defined in a subsetdef tag in the file header. If the value of this tag is not mentioned in a subsetdef tag, a parse error will be generated. A term may belong to any number of subsets. 
    private String sysnonym="";//This tag gives a synonym for the term; whether the synonym is exact, broad, narrow, or otherwise related to the term is not specified. The value of this tag should be the quote enclosed synonym text, followed by an optional dbxref list containing dbxrefs that describe the origin of this synonym (see Dbxref Formatting for information on how dbxref lists are encoded). A term may have any number of synonyms. 
    private String intersection_of="";//This tag indicates that this term is equivalent to the intersection of several other terms. The value is either a term id, or a relationship type id, a space, and a term id.
    private String is_obsolute="";//Whether or not this term is obsolete. Allowable values are "true" and "false" (false is assumed if this tag is not present). Obsolete terms must have no relationships, and no defined is_a, inverse_of, disjoint_from, union_of, or intersection_of tags.
    private String relationship="";//This tag describes a typed relationship between this term and another term. The value of this tag should be the relationship type id, and then the id of the target term. The relationship type name must be a relationship type name as defined in a typedef tag stanza. The [Typedef] must either occur in a document in the current parse batch, or in a file imported via an import header tag. If the relationship type name is undefined, a parse error will be generated. If the id of the target term cannot be resolved by the end of parsing the current batch of files, this tag describes a "dangling reference"; see the parser requirements section for information about how a parser may handle dangling references. If a relationship is specified for a term with an is_obsolete value of true, a parse error will be generated. 
    private String union_of="";//This tag indicates that this term represents the union of several other terms. The value is the id of one of the other terms of which this term is a union. 
    private String disjoint_from="";//This tag indicates that a term is disjoint from another, meaning that the two terms have no instances or subclasses in common. The value is the id of the term from which the current term is disjoint. This tag may not be applied to relationship types. 

    public String getAlt_id() {
        return alt_id;
    }

    public void setAlt_id(String alt_id) {
        this.alt_id = alt_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }

    public String getDisjoint_from() {
        return disjoint_from;
    }

    public void setDisjoint_from(String disjoint_from) {
        this.disjoint_from = disjoint_from;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIntersection_of() {
        return intersection_of;
    }

    public void setIntersection_of(String intersection_of) {
        this.intersection_of = intersection_of;
    }

    public String getIs_a() {
        return is_a;
    }

    public void setIs_a(String is_a) {
        this.is_a = is_a;
    }

    public String  isIs_obsolute() {
        return is_obsolute;
    }

    public void setIs_obsolute(String is_obsolute) {
        this.is_obsolute = is_obsolute;
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

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getSubset() {
        return subset;
    }

    public void setSubset(String subset) {
        this.subset = subset;
    }

    public String getSysnonym() {
        return sysnonym;
    }

    public void setSysnonym(String sysnonym) {
        this.sysnonym = sysnonym;
    }

    public String getUnion_of() {
        return union_of;
    }

    public void setUnion_of(String union_of) {
        this.union_of = union_of;
    }
    
    public String getPureAcenstor(){
        return this.is_a.substring(0, 10);
    }

    public Oboterm getis_aTerm(HashMap goTOtermMap){
          return (Oboterm) goTOtermMap.get(this.getPureAcenstor());//他的父节点
        
    }
    @Override
    public String toString() {
        return  "\t" + id + "\t" + name + "\t" + namespace;
    }
    
}
