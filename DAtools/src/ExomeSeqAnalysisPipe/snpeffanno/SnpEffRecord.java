/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.snpeffanno;

/**
 *
 * @author ZHAO Qi
 * @date 2015-3-31 12:59:45
 * @version 1.6.0
 */
public class SnpEffRecord {
    private String Chromo;
    private int Position;
    private String Ref;
    private String Change;
    private String Changtype;
    private String Homozygous;
    private float Quality;
    private int Coverage;
    private String Warning;
    private String Gene_ID;
    private String Gene_name;
    private String Bio_type;
    private String Transcript_ID;
    private String Exon_ID;
    private String Exon_Rank;
    private String Effect;
    private String old_AA_new_AA;
    private String Old_codon_New_codon;
    private String Codon_Num_CDS;
    private String Codon_Degeneracy;
    private String CDS_size;
    private String Codons_around;
    private String AAs_around;
    private String Custom_interval_ID;
    public SnpEffRecord() {

    }
    public SnpEffRecord(String inline, boolean over20) {
        String[] cols = inline.split("\t");

        if (cols.length > 0) {
            Chromo = cols[0];
        }
        if (cols.length > 1) {
            Position = Integer.parseInt(cols[1]);
        }
        if (cols.length > 2) {
            Ref = cols[2];
        }
        if (cols.length > 3) {
            Change = cols[3];
        }
        if (cols.length > 4) {
            Changtype = cols[4];
        }
        if (cols.length > 5) {
            Homozygous = cols[5];
        }
        if (cols.length > 6) {
            Quality = Float.parseFloat(cols[6]);
        }
        if (cols.length > 7) {
            Coverage = Integer.parseInt(cols[7]);
        }
        if (cols.length > 8) {
            Warning = cols[8];
        }
        if (cols.length > 9) {
            Gene_ID = cols[9];
        }
        if (cols.length > 10) {
            Gene_name = cols[10];
        }
        if (cols.length > 11) {
            Bio_type = cols[11];
        }
        if (cols.length > 12) {
            Transcript_ID = cols[12];
        }
        if (cols.length > 13) {
            Exon_ID = cols[13];
        }
        if (cols.length > 14) {
            Exon_Rank = cols[14];
        }
        if (cols.length > 15) {
            Effect = cols[15];
        }
        if (cols.length > 16) {
            old_AA_new_AA = cols[16];
        }
        if (cols.length > 17) {
            Old_codon_New_codon = cols[17];
        }
        if (cols.length > 18) {
            Codon_Num_CDS = cols[18];
        }
        if (cols.length > 19) {
            Codon_Degeneracy = cols[19];
        }
        if (cols.length > 20) {
            CDS_size = cols[20];
        }
        if (cols.length > 21) {
            Codons_around = cols[21];
        }
        if (cols.length > 22) {
            AAs_around = cols[22];
        }
        if (cols.length > 23) {
            Custom_interval_ID = cols[23];
        }

    }

    public String getChromo() {
        return Chromo;
    }

    public void setChromo(String Chromo) {
        this.Chromo = Chromo;
    }

    public int getPosition() {
        return Position;
    }

    public void setPosition(int Position) {
        this.Position = Position;
    }

    public String getRef() {
        return Ref;
    }

    public void setRef(String Ref) {
        this.Ref = Ref;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String Change) {
        this.Change = Change;
    }

    public String getChangtype() {
        return Changtype;
    }

    public void setChangtype(String Changtype) {
        this.Changtype = Changtype;
    }

    public String getHomozygous() {
        return Homozygous;
    }

    public void setHomozygous(String Homozygous) {
        this.Homozygous = Homozygous;
    }

    public float getQuality() {
        return Quality;
    }

    public void setQuality(float Quality) {
        this.Quality = Quality;
    }

    public int getCoverage() {
        return Coverage;
    }

    public void setCoverage(int Coverage) {
        this.Coverage = Coverage;
    }

    public String getWarning() {
        return Warning;
    }

    public void setWarning(String Warning) {
        this.Warning = Warning;
    }

    public String getGene_ID() {
        return Gene_ID;
    }

    public void setGene_ID(String Gene_ID) {
        this.Gene_ID = Gene_ID;
    }

    public String getGene_name() {
        return Gene_name;
    }

    public void setGene_name(String Gene_name) {
        this.Gene_name = Gene_name;
    }

    public String getBio_type() {
        return Bio_type;
    }

    public void setBio_type(String Bio_type) {
        this.Bio_type = Bio_type;
    }

    public String getTranscript_ID() {
        return Transcript_ID;
    }

    public void setTranscript_ID(String Transcript_ID) {
        this.Transcript_ID = Transcript_ID;
    }

    public String getExon_ID() {
        return Exon_ID;
    }

    public void setExon_ID(String Exon_ID) {
        this.Exon_ID = Exon_ID;
    }

    public String getExon_Rank() {
        return Exon_Rank;
    }

    public void setExon_Rank(String Exon_Rank) {
        this.Exon_Rank = Exon_Rank;
    }

    public String getEffect() {
        return Effect;
    }

    public void setEffect(String Effect) {
        this.Effect = Effect;
    }

    public String getOld_AA_new_AA() {
        return old_AA_new_AA;
    }

    public void setOld_AA_new_AA(String old_AA_new_AA) {
        this.old_AA_new_AA = old_AA_new_AA;
    }

    public String getOld_codon_New_codon() {
        return Old_codon_New_codon;
    }

    public void setOld_codon_New_codon(String Old_codon_New_codon) {
        this.Old_codon_New_codon = Old_codon_New_codon;
    }

    public String getCodon_Num_CDS() {
        return Codon_Num_CDS;
    }

    public void setCodon_Num_CDS(String Codon_Num_CDS) {
        this.Codon_Num_CDS = Codon_Num_CDS;
    }

    public String getCodon_Degeneracy() {
        return Codon_Degeneracy;
    }

    public void setCodon_Degeneracy(String Codon_Degeneracy) {
        this.Codon_Degeneracy = Codon_Degeneracy;
    }

    public String getCDS_size() {
        return CDS_size;
    }

    public void setCDS_size(String CDS_size) {
        this.CDS_size = CDS_size;
    }

    public String getCodons_around() {
        return Codons_around;
    }

    public void setCodons_around(String Codons_around) {
        this.Codons_around = Codons_around;
    }

    public String getAAs_around() {
        return AAs_around;
    }

    public void setAAs_around(String AAs_around) {
        this.AAs_around = AAs_around;
    }

    public String getCustom_interval_ID() {
        return Custom_interval_ID;
    }

    public void setCustom_interval_ID(String Custom_interval_ID) {
        this.Custom_interval_ID = Custom_interval_ID;
    }

    @Override
    public String toString() {
        return Chromo
                + "\t" + Position
                + "\t" + Ref
                + "\t" + Change
                + "\t" + Changtype
                + "\t" + Homozygous
                + "\t" + Quality
                + "\t" + Coverage
                + "\t" + Warning
                + "\t" + Gene_ID
                + "\t" + Gene_name
                + "\t" + Bio_type
                + "\t" + Transcript_ID
                + "\t" + Exon_ID
                + "\t" + Exon_Rank
                + "\t" + Effect
                + "\t" + old_AA_new_AA
                + "\t" + Old_codon_New_codon
                + "\t" + Codon_Num_CDS
                + "\t" + Codon_Degeneracy
                + "\t" + CDS_size
                + "\t" + Codons_around
                + "\t" + AAs_around
                + "\t" + Custom_interval_ID;
    }

    public String tosimple(){
     return Chromo
                + "\t" + Position 
                + "\t\t" + Gene_name;  
    }
}


