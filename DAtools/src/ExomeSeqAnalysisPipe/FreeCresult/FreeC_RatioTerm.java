/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ExomeSeqAnalysisPipe.FreeCresult;

import htsjdk.samtools.util.CollectionUtil;
import java.util.List;
import java.util.stream.Collectors;
import pub.Tools;

/**
 *
 * @author Administrator
 * @since 2018-12-26
 * @coding time 18:58:31
 * @author Qi Zhao
 */
public class FreeC_RatioTerm {
    private	String	Chromosome;
    private	int	Start;
    private	double	Ratio;
    private	double	MedianRatio;
    private	int	CopyNumber;
    private	double	BAF;
    private	double	estimatedBAF;
    private	String	Genotype;
    private	double	UncertaintyOfGT;
    private	String	Gene;

    public FreeC_RatioTerm(String Chromosome, String Start, String Ratio, String MedianRatio, String CopyNumber, String BAF, String estimatedBAF, String Genotype, String UncertaintyOfGT, String Gene) {
        this.Chromosome = Chromosome;
        this.Start = Integer.parseInt(Start);
        this.Ratio = Double.parseDouble(Ratio);
        this.MedianRatio = Double.parseDouble(MedianRatio);
        this.CopyNumber = Integer.parseInt(CopyNumber);
        this.BAF = Double.parseDouble(BAF);
        this.estimatedBAF = Double.parseDouble(estimatedBAF);
        this.Genotype = Genotype;
        this.UncertaintyOfGT = Double.parseDouble(UncertaintyOfGT);
        this.Gene = Gene;
    }
    

    
    
    public FreeC_RatioTerm() {
    }

     public String toString() {
        final List<?> fields = CollectionUtil.makeList(Chromosome,Start,Ratio,MedianRatio,CopyNumber,BAF,estimatedBAF,Genotype,UncertaintyOfGT);
        String str = fields.stream().map(String::valueOf).collect(Collectors.joining("\t"));
        return str;
    }
    
}
