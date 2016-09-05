package pub;



import javax.swing.*;

/**
 * <p>SequenceVerifier.java</p>
 * <p>Copyright (c) 2007-2009. The CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.0
 */
public class SequenceVerifier extends InputVerifier {

    private String sequence;
    private String name;
    public String filePath;

    public boolean verify(String fullSeq) {
        try {
            int seqHead = 0;
            if (fullSeq.startsWith(">")) {
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i < fullSeq.length(); i++) {
                    char c = fullSeq.charAt(i);
                    if (c == '\n') {
                        seqHead = i + 1;
                        break;
                    }
                    sb.append(fullSeq.charAt(i));
                }
                name = sb.toString();
            }
            String tempSeq = fullSeq.substring(seqHead);
            tempSeq = tempSeq.toUpperCase();
            StringBuffer stringbuffer = new StringBuffer();
            for (int i = 0; i < tempSeq.length(); i++) {
                char c = tempSeq.charAt(i);
                if (c >= 'A' && c <= 'Z' && c != 'J' && c != 'O' && c != 'U') {
                    stringbuffer.append(c);
                }
            }
            sequence = stringbuffer.toString();
            boolean isFasta = true;
            return isFasta;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean verify(JComponent input) {
        try {
            JTextPane tf = (JTextPane) input;
            String fullSeq = tf.getText().trim();
            int seqHead = 0;
            if (fullSeq.startsWith(">")) {
                StringBuffer sb = new StringBuffer();
                for (int i = 1; i < fullSeq.length(); i++) {
                    char c = fullSeq.charAt(i);
                    if (c == '\n') {
                        seqHead = i + 1;
                        break;
                    }
                    sb.append(fullSeq.charAt(i));
                }
                name = sb.toString();
            }
            String tempSeq = fullSeq.substring(seqHead);
            tempSeq = tempSeq.toUpperCase();
            StringBuffer stringbuffer = new StringBuffer();
            for (int i = 0; i < tempSeq.length(); i++) {
                char c = tempSeq.charAt(i);
                if (c >= 'A' && c <= 'Z' && c != 'J' && c != 'O' && c != 'U') {
                    stringbuffer.append(c);
                }
            }
            sequence = stringbuffer.toString();
            boolean isFasta = true;
            return isFasta;
        } catch (Exception ex) {
            return false;
        }
    }

    public Fasta getFasta() {
        return new Fasta(name, sequence);
    }
}

