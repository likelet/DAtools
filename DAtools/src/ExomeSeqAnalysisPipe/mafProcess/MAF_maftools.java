/*
 *this format provide for maftools analysis 
 */

package ExomeSeqAnalysisPipe.mafProcess;

import htsjdk.samtools.util.CollectionUtil;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Administrator
 * @since 2017-11-19
 * @coding time 18:18:14
 * @author Qi Zhao
 */
public class MAF_maftools {
    private String Hugo_Symbol;
    private String Entrez_Gene_Id;
    private String Center;
    private String NCBI_Build;
    private String Chromosome;
    private String Start_Position;
    private String End_position;
    private String Strand;
    private String Variant_Classification;
    private String Variant_Type;
    private String Reference_Allele;
    private String Tumor_Seq_Allele1;
    private String Tumor_Seq_Allele2;
    private String Tumor_Sample_Barcode;
    private String Protein_Change;
    private String i_TumorVAF_WU;
    private String i_transcript_name;

    public MAF_maftools() {
    }

    
    public MAF_maftools(String Hugo_Symbol, String Entrez_Gene_Id, String Center, String NCBI_Build, String Chromosome, String Start_Position, String End_position, String Strand, String Variant_Classification, String Variant_Type, String Reference_Allele, String Tumor_Seq_Allele1, String Tumor_Seq_Allele2, String Tumor_Sample_Barcode, String Protein_Change, String i_TumorVAF_WU, String i_transcript_name) {
        this.Hugo_Symbol = Hugo_Symbol;
        this.Entrez_Gene_Id = Entrez_Gene_Id;
        this.Center = Center;
        this.NCBI_Build = NCBI_Build;
        this.Chromosome = Chromosome;
        this.Start_Position = Start_Position;
        this.End_position = End_position;
        this.Strand = Strand;
        this.Variant_Classification = Variant_Classification;
        this.Variant_Type = Variant_Type;
        this.Reference_Allele = Reference_Allele;
        this.Tumor_Seq_Allele1 = Tumor_Seq_Allele1;
        this.Tumor_Seq_Allele2 = Tumor_Seq_Allele2;
        this.Tumor_Sample_Barcode = Tumor_Sample_Barcode;
        this.Protein_Change = Protein_Change;
        this.i_TumorVAF_WU = i_TumorVAF_WU;
        this.i_transcript_name = i_transcript_name;
    }

    public String getHugo_Symbol() {
        return Hugo_Symbol;
    }

    public void setHugo_Symbol(String Hugo_Symbol) {
        this.Hugo_Symbol = Hugo_Symbol;
    }

    public String getEntrez_Gene_Id() {
        return Entrez_Gene_Id;
    }

    public void setEntrez_Gene_Id(String Entrez_Gene_Id) {
        this.Entrez_Gene_Id = Entrez_Gene_Id;
    }

    public String getCenter() {
        return Center;
    }

    public void setCenter(String Center) {
        this.Center = Center;
    }

    public String getNCBI_Build() {
        return NCBI_Build;
    }

    public void setNCBI_Build(String NCBI_Build) {
        this.NCBI_Build = NCBI_Build;
    }

    public String getChromosome() {
        return Chromosome;
    }

    public void setChromosome(String Chromosome) {
        this.Chromosome = Chromosome;
    }

    public String getStart_Position() {
        return Start_Position;
    }

    public void setStart_Position(String Start_Position) {
        this.Start_Position = Start_Position;
    }

    public String getEnd_position() {
        return End_position;
    }

    public void setEnd_position(String End_position) {
        this.End_position = End_position;
    }

    public String getStrand() {
        return Strand;
    }

    public void setStrand(String Strand) {
        this.Strand = Strand;
    }

    public String getVariant_Classification() {
        return Variant_Classification;
    }

    public void setVariant_Classification(String Variant_Classification) {
        this.Variant_Classification = Variant_Classification;
    }

    public String getVariant_Type() {
        return Variant_Type;
    }

    public void setVariant_Type(String Variant_Type) {
        this.Variant_Type = Variant_Type;
    }

    public String getReference_Allele() {
        return Reference_Allele;
    }

    public void setReference_Allele(String Reference_Allele) {
        this.Reference_Allele = Reference_Allele;
    }

    public String getTumor_Seq_Allele1() {
        return Tumor_Seq_Allele1;
    }

    public void setTumor_Seq_Allele1(String Tumor_Seq_Allele1) {
        this.Tumor_Seq_Allele1 = Tumor_Seq_Allele1;
    }

    public String getTumor_Seq_Allele2() {
        return Tumor_Seq_Allele2;
    }

    public void setTumor_Seq_Allele2(String Tumor_Seq_Allele2) {
        this.Tumor_Seq_Allele2 = Tumor_Seq_Allele2;
    }

    public String getTumor_Sample_Barcode() {
        return Tumor_Sample_Barcode;
    }

    public void setTumor_Sample_Barcode(String Tumor_Sample_Barcode) {
        this.Tumor_Sample_Barcode = Tumor_Sample_Barcode;
    }

    public String getProtein_Change() {
        return Protein_Change;
    }

    public void setProtein_Change(String Protein_Change) {
        this.Protein_Change = Protein_Change;
    }

    public String getI_TumorVAF_WU() {
        return i_TumorVAF_WU;
    }

    public void setI_TumorVAF_WU(String i_TumorVAF_WU) {
        this.i_TumorVAF_WU = i_TumorVAF_WU;
    }

    public String getI_transcript_name() {
        return i_transcript_name;
    }

    public void setI_transcript_name(String i_transcript_name) {
        this.i_transcript_name = i_transcript_name;
    }

     public String toString() {
        final List<?> fields = CollectionUtil.makeList(Hugo_Symbol,Entrez_Gene_Id,Center,NCBI_Build,Chromosome,Start_Position,End_position,Strand,Variant_Classification,Variant_Type,Reference_Allele,Tumor_Seq_Allele1,Tumor_Seq_Allele2,Tumor_Sample_Barcode,Protein_Change,i_TumorVAF_WU,i_transcript_name);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
    
}
