package pub;

import java.awt.*;
import javax.swing.*;

/**
 * <p>ProgressDialog.java</p>
 * <p>Copyright (c) 2007-2009. CUCKOO Workgroup, USTC, P.R.China</p>
 * @author Ren Jian
 * @version 2.0
 */
public class ProgressDialog extends JDialog {

    JPanel mainPanel = new JPanel();
    public JProgressBar processProgressBar = new JProgressBar();
    BorderLayout borderLayout1 = new BorderLayout();
    FlowLayout flowLayout1 = new FlowLayout();

    public ProgressDialog() {
        try {
            getContentPane().setLayout(borderLayout1);
            mainPanel.setLayout(flowLayout1);
//            processProgressBar.setStringPainted(true);
            processProgressBar.setForeground(new Color(39, 125, 255));
            processProgressBar.setBorder(BorderFactory.createLineBorder(Color.black));
            processProgressBar.setPreferredSize(new Dimension(490, 16));
             processProgressBar.setIndeterminate(true);
            mainPanel.setBackground(new Color(85, 202, 240));
            mainPanel.add(processProgressBar, null);
            this.getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

            mainPanel.setPreferredSize(new Dimension(500, 25));
            
            this.setUndecorated(true);
            this.setAlwaysOnTop(true);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

  
    
}
