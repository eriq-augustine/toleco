package cero;

import javax.swing.ButtonModel;
import org.jdesktop.application.Action;

/**
 * SwingOptionsMenu represents the graphical user interface for
 * a human player to customize the options of the game.
 * 
 * @author Dirk Cummings - Javadocs
 * @author Annabel Hung - Pseudocode, Implementation
 * @version 1.0 - March 3, 2010
 */
public class SwingOptionsMenu extends javax.swing.JDialog {

    I_Options go;
    GameController gc;

   /** Creates new form SwingOptionsMenu
     * @param parent is the main application frame.
     * @param gc a reference to the current game controller
     */
    public SwingOptionsMenu(java.awt.Frame parent, I_Controller gc) {
        // CALL super with parent
        super(parent); 
        // CALL initComponents()
        initComponents();

        // Save the reference of gc
        this.gc = (GameController)gc;
        // Save the reference of the current options from gc
        go = gc.getOptions();
    }

    /**
     * Closes the Options Menu dialog box.
     */
    @Action public void closeOptionsMenu() {
        // CALL dispose()
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content nameof this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();
        comp2name = new javax.swing.JTextField();
        comp1name = new javax.swing.JTextField();
        comp3name = new javax.swing.JTextField();
        playerName = new javax.swing.JTextField();
        c2diffLabel = new javax.swing.JLabel();
        c1diffLabel = new javax.swing.JLabel();
        c3diffLabel = new javax.swing.JLabel();
        c2easy = new javax.swing.JRadioButton();
        c2med = new javax.swing.JRadioButton();
        c2hard = new javax.swing.JRadioButton();
        c1easy = new javax.swing.JRadioButton();
        c1med = new javax.swing.JRadioButton();
        c1hard = new javax.swing.JRadioButton();
        c3easy = new javax.swing.JRadioButton();
        c3med = new javax.swing.JRadioButton();
        c3hard = new javax.swing.JRadioButton();
        extra = new javax.swing.JLabel();
        drawCheckBox = new javax.swing.JCheckBox();
        moreInfoLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(cero.CeroApp.class).getContext().getResourceMap(SwingOptionsMenu.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setResizable(false);

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setToolTipText(resourceMap.getString("okButton.toolTipText")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        comp2name.setText(resourceMap.getString("comp2name.text")); // NOI18N
        comp2name.setName("comp2name"); // NOI18N

        comp1name.setText(resourceMap.getString("comp1name.text")); // NOI18N
        comp1name.setName("comp1name"); // NOI18N

        comp3name.setText(resourceMap.getString("comp3name.text")); // NOI18N
        comp3name.setName("comp3name"); // NOI18N

        playerName.setText(resourceMap.getString("playerName.text")); // NOI18N
        playerName.setName("playerName"); // NOI18N

        c2diffLabel.setText(resourceMap.getString("c2diffLabel.text")); // NOI18N
        c2diffLabel.setName("c2diffLabel"); // NOI18N

        c1diffLabel.setText(resourceMap.getString("c1diffLabel.text")); // NOI18N
        c1diffLabel.setName("c1diffLabel"); // NOI18N

        c3diffLabel.setText(resourceMap.getString("c3diffLabel.text")); // NOI18N
        c3diffLabel.setName("c3diffLabel"); // NOI18N

        buttonGroup2.add(c2easy);
        c2easy.setText(resourceMap.getString("c2easy.text")); // NOI18N
        c2easy.setActionCommand(resourceMap.getString("c2easy.actionCommand")); // NOI18N
        c2easy.setName("c2easy"); // NOI18N

        buttonGroup2.add(c2med);
        c2med.setSelected(true);
        c2med.setText(resourceMap.getString("c2med.text")); // NOI18N
        c2med.setActionCommand(resourceMap.getString("c2med.actionCommand")); // NOI18N
        c2med.setName("c2med"); // NOI18N

        buttonGroup2.add(c2hard);
        c2hard.setText(resourceMap.getString("c2hard.text")); // NOI18N
        c2hard.setActionCommand(resourceMap.getString("c2hard.actionCommand")); // NOI18N
        c2hard.setName("c2hard"); // NOI18N

        buttonGroup1.add(c1easy);
        c1easy.setText(resourceMap.getString("c1easy.text")); // NOI18N
        c1easy.setActionCommand(resourceMap.getString("c1easy.actionCommand")); // NOI18N
        c1easy.setName("c1easy"); // NOI18N

        buttonGroup1.add(c1med);
        c1med.setSelected(true);
        c1med.setText(resourceMap.getString("c1med.text")); // NOI18N
        c1med.setActionCommand(resourceMap.getString("c1med.actionCommand")); // NOI18N
        c1med.setName("c1med"); // NOI18N

        buttonGroup1.add(c1hard);
        c1hard.setText(resourceMap.getString("c1hard.text")); // NOI18N
        c1hard.setActionCommand(resourceMap.getString("c1hard.actionCommand")); // NOI18N
        c1hard.setName("c1hard"); // NOI18N

        buttonGroup3.add(c3easy);
        c3easy.setText(resourceMap.getString("c3easy.text")); // NOI18N
        c3easy.setActionCommand(resourceMap.getString("c3easy.actionCommand")); // NOI18N
        c3easy.setName("c3easy"); // NOI18N

        buttonGroup3.add(c3med);
        c3med.setSelected(true);
        c3med.setText(resourceMap.getString("c3med.text")); // NOI18N
        c3med.setActionCommand(resourceMap.getString("c3med.actionCommand")); // NOI18N
        c3med.setName("c3med"); // NOI18N

        buttonGroup3.add(c3hard);
        c3hard.setText(resourceMap.getString("c3hard.text")); // NOI18N
        c3hard.setActionCommand(resourceMap.getString("c3hard.actionCommand")); // NOI18N
        c3hard.setName("c3hard"); // NOI18N

        extra.setText(resourceMap.getString("extra.text")); // NOI18N
        extra.setName("extra"); // NOI18N

        drawCheckBox.setText(resourceMap.getString("drawCheckBox.text")); // NOI18N
        drawCheckBox.setName("drawCheckBox"); // NOI18N

        moreInfoLabel.setText(resourceMap.getString("moreInfoLabel.text")); // NOI18N
        moreInfoLabel.setName("moreInfoLabel"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(moreInfoLabel)
                            .addComponent(c1easy)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c1med)
                                    .addComponent(c1hard)
                                    .addComponent(comp1name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c1diffLabel))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(c2diffLabel)
                                    .addComponent(c2easy)
                                    .addComponent(c2med)
                                    .addComponent(c2hard)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(extra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(drawCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))
                                    .addComponent(comp2name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(c3hard)
                                .addComponent(c3med)
                                .addComponent(c3diffLabel)
                                .addComponent(c3easy)
                                .addComponent(comp3name, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comp2name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comp3name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c3diffLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comp1name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(c1diffLabel)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c1easy)
                            .addComponent(c3easy)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2diffLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2easy)
                        .addGap(4, 4, 4)
                        .addComponent(c2med)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(c2hard)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(c1med)
                            .addComponent(c3med))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(c1hard)
                            .addComponent(c3hard)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(extra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawCheckBox)))
                .addGap(43, 43, 43)
                .addComponent(playerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(okButton)
                            .addComponent(cancelButton))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(moreInfoLabel)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        closeOptionsMenu();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        go.setComputerLevel(Players.kComputer1, ComputerLevel.valueOf(buttonGroup1.getSelection().getActionCommand()));
        go.setComputerLevel(Players.kComputer2, ComputerLevel.valueOf(buttonGroup2.getSelection().getActionCommand()));
        go.setComputerLevel(Players.kComputer3, ComputerLevel.valueOf(buttonGroup3.getSelection().getActionCommand()));
        go.setName(Players.kComputer1, comp1name.getText());
        go.setName(Players.kComputer2, comp2name.getText());
        go.setName(Players.kComputer3, comp3name.getText());
        go.setName(Players.kHuman, playerName.getText());
        //Fixes defect 269.  Multiple draws allowed when unchecked.
        go.setMultipleDraws(!drawCheckBox.isSelected());

        // set the new set of options
        gc.setOptions(go);
        // reset the game
        gc.resetGame();

        // start the new game
        gc.startGame();

        // close the options menu
        closeOptionsMenu();
    }//GEN-LAST:event_okButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel c1diffLabel;
    private javax.swing.JRadioButton c1easy;
    private javax.swing.JRadioButton c1hard;
    private javax.swing.JRadioButton c1med;
    private javax.swing.JLabel c2diffLabel;
    private javax.swing.JRadioButton c2easy;
    private javax.swing.JRadioButton c2hard;
    private javax.swing.JRadioButton c2med;
    private javax.swing.JLabel c3diffLabel;
    private javax.swing.JRadioButton c3easy;
    private javax.swing.JRadioButton c3hard;
    private javax.swing.JRadioButton c3med;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField comp1name;
    private javax.swing.JTextField comp2name;
    private javax.swing.JTextField comp3name;
    private javax.swing.JCheckBox drawCheckBox;
    private javax.swing.JLabel extra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel moreInfoLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField playerName;
    // End of variables declaration//GEN-END:variables

}
