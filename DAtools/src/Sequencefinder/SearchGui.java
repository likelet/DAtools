/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sequencefinder;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import pub.Fasta;
import pub.FastaReader;
import pub.ProgressDialog;
import pub.Tools;

/**
 *
 * @author Administrator
 */
public class SearchGui extends javax.swing.JFrame {
    private  int kmer=8;
    private String seq=null;
    private LinkedList<Fasta> list;
    private javax.swing.JTable jTable;
    /**
     * Creates new form SearchGui
     */
    public SearchGui() throws IOException {
        
        super("SequenceSearcher");
        this.initComponents();
        this.setResizable(true);
        Dimension scrnSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        int scrnWidth = scrnSize.width;
        int scrnHeight = scrnSize.height;
        int x = (scrnWidth - this.getWidth()) * 1 / 3;
        int y = (scrnHeight - this.getHeight()) * 1 / 3;
        this.setLocation(x, y);//center shown
        scrnSize.setSize(scrnWidth / 2, scrnHeight * 3 / 5);
        this.setSize(scrnSize);
        outtext.setPage(SearchGui.class.getResource("welcome.html"));
        outtext.setEditable(false);
        this.list = new FastaReader("data/db.fasta").getFastaList();
        
    }

    class analysisThread extends Thread {

        private ProgressDialog progressDialog = new ProgressDialog();

        public void run() {
            outtext.setText("The Searching process may take few seconds, please wait patiently");
            jButton2.setEnabled(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Tools.WindowCenter(progressDialog);
            seq = inputText.getText();
            processSearch pc = new processSearch(list, seq, kmer);
            ArrayList<OutputFormat> outlist = pc.getOutlist();
            jTable = new javax.swing.JTable();

            jTable.setModel(new resultTableModel(
                    outlist,
                    new String[]{
                        "Seq", "Count", "Start", "End", "Hit genes"
                    }));
            jTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            jTable.setFillsViewportHeight(true);
            jTable.setName("Seq\nCount\nStart\nEnd\nExpect\nHit enes"); // NOI18N

            jScrollPane1.getViewport().add(jTable);
            jTable.setAutoCreateRowSorter(true);
            jTable.setVisible(true);

            setCursor(Cursor.getPredefinedCursor((java.awt.Cursor.DEFAULT_CURSOR)));
            progressDialog.dispose();
            jButton2.setEnabled(true);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        inputText = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        outtext = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Input", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 0, 10), new java.awt.Color(51, 51, 255))); // NOI18N
        jPanel3.setLayout(new java.awt.BorderLayout());

        inputText.setColumns(20);
        inputText.setRows(5);
        jScrollPane3.setViewportView(inputText);

        jPanel3.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Setting", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 1, 10), new java.awt.Color(0, 204, 0))); // NOI18N

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("宋体", 1, 12)); // NOI18N
        jButton2.setText("Submit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Example");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSlider1.setMaximum(50);
        jSlider1.setMinimum(5);
        jSlider1.setValue(8);
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel2.setText("0");

        jLabel3.setText("50");

        jLabel4.setFont(new java.awt.Font("宋体", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 51));
        jLabel4.setText("Current kmer : 8");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel4))
                    .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))))
                .addGap(7, 7, 7)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel4);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Output", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Courier New", 0, 10), new java.awt.Color(204, 0, 204))); // NOI18N
        jPanel2.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(outtext);

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.inputText.setText("");
        this.outtext.setText("");
        this.seq = null;
        this.kmer = 8;
        this.jSlider1.setValue(8);
        String str = "Current kmer: " + kmer;
        this.jLabel4.setText(str);
        try {
            outtext.setPage(SearchGui.class.getResource("welcome.html"));
        } catch (IOException ex) {
            Logger.getLogger(SearchGui.class.getName()).log(Level.SEVERE, null, ex);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.outtext.setText("The Searching process may take few seconds, please wait patiently");
        this.inputText.setText("CTTCGTCGGGGGAGGGCGCTCCACCCGGGCTGGAGTTGCAGAGCCCAGCAGATCCCTGCG");

// TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged

        int tempkmer=jSlider1.getValue();
        this.kmer=tempkmer;
        String str="Current kmer: "+ kmer;
        this.jLabel4.setText(str);
        // TODO add your handling code here:
    }//GEN-LAST:event_jSlider1StateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(inputText.getText()!=null){
            new analysisThread().start();
        }else{
            JOptionPane.showMessageDialog(null, "Please input sequence");
        }
       
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new SearchGui().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(SearchGui.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea inputText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JEditorPane outtext;
    // End of variables declaration//GEN-END:variables
}
