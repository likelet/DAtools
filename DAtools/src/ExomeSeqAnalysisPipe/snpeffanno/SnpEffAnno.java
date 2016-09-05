/*
 * with snpEff output file dir as input F:\mywork\project\Peng\SamtoolsResult\process2\annotationResult

 * static the snp frequency based on genelevel 
 * summary the intervals between snps with in certain genes
 */
package ExomeSeqAnalysisPipe.snpeffanno;

import java.io.IOException;

/**
 *
 * @author 媛子
 */
public class SnpEffAnno {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        new SnpEffReader("F:\\database\\E.coli\\gene.txt","F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\oceanAnnotation","F:\\mywork\\project\\Peng\\SamtoolsResult\\process2\\oceanAnnotation\\genelevelSnpStatus.xlsx");
    }
}
