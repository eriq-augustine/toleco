/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BackStory.java
 *
 * Created on Feb 18, 2010, 6:28:39 PM
 */

package tolecoFunctionalPrototype.GUI;
import javax.swing.*;

/**
 *
 * @author eralston
 */
public class BackStory extends JPanel{


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toleco = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        backStory = new javax.swing.JTextArea();

        setPreferredSize(new java.awt.Dimension(200, 300));

        toleco.setFont(new java.awt.Font("DejaVu Sans", 0, 24));
        toleco.setText("Toleco");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(244, 79));

        backStory.setColumns(20);
        backStory.setEditable(false);
        backStory.setLineWrap(true);
        backStory.setRows(5);
        backStory.setWrapStyleWord(true);
        jScrollPane1.setViewportView(backStory);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toleco, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(toleco, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea backStory;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel toleco;
    // End of variables declaration//GEN-END:variables

     

    public BackStory() {
        initComponents();
        backStory.setText("HI THERE!");
        toleco.setHorizontalTextPosition(SwingConstants.CENTER);
    }
    public void setStory(String bs) {
        backStory.setText(bs);
        toleco.setText("Toleco");
        toleco.setHorizontalAlignment(SwingConstants.CENTER);
        backStory.repaint();
        backStory.invalidate();
    }
    public String getText() {
       return backStory.getText();
    }
}
