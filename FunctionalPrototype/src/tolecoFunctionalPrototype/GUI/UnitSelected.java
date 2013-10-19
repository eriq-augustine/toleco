/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * UnitSelected.java
 *
 * Created on Feb 18, 2010, 6:03:59 PM
 */

package tolecoFunctionalPrototype.GUI;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import tolecoFunctionalPrototype.Core.*;
import javax.swing.*;

/**
 *
 * @author eralston
 */
public class UnitSelected extends JPanel{

    private static String kPiercePath = "/tolecoFunctionalPrototype/GUI/Images/pierce.gif";
    private BufferedImage pierceImage;
    private static String kHidePath = "/tolecoFunctionalPrototype/GUI/Images/hide.gif";
    private BufferedImage hideImage;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unitPortrait = new javax.swing.JLabel();
        terrainDefenseBonus = new javax.swing.JLabel();
        unitName = new javax.swing.JLabel();
        attackPortrait = new javax.swing.JLabel();
        defensePortrait = new javax.swing.JLabel();
        terrainPortrait = new javax.swing.JLabel();
        unitAttackLabel = new javax.swing.JLabel();
        unitTerrainLabel = new javax.swing.JLabel();
        unitMoveLabel = new javax.swing.JLabel();
        unitHealthLabel = new javax.swing.JLabel();
        unitRangeLabel = new javax.swing.JLabel();
        unitHealthValues = new javax.swing.JLabel();
        unitMovePoints = new javax.swing.JLabel();
        unitAttackValue = new javax.swing.JLabel();
        unitRangeValue = new javax.swing.JLabel();
        unitDefenseValue = new javax.swing.JLabel();
        unitDefenseLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(200, 300));

        unitPortrait.setBackground(new java.awt.Color(254, 254, 254));
        unitPortrait.setBorder(null);
        unitPortrait.setMaximumSize(new java.awt.Dimension(64, 64));
        unitPortrait.setPreferredSize(new java.awt.Dimension(64, 64));

        terrainDefenseBonus.setFont(new java.awt.Font("DejaVu Sans", 1, 11)); // NOI18N
        terrainDefenseBonus.setText("(+y)");

        unitName.setFont(new java.awt.Font("DejaVu Sans", 1, 14)); // NOI18N
        unitName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unitName.setText("Name");
        unitName.setPreferredSize(new java.awt.Dimension(44, 20));

        attackPortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        attackPortrait.setPreferredSize(new java.awt.Dimension(16, 16));

        defensePortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        defensePortrait.setPreferredSize(new java.awt.Dimension(16, 16));

        terrainPortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        terrainPortrait.setPreferredSize(new java.awt.Dimension(40, 40));

        unitAttackLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitAttackLabel.setText("Attack:");

        unitTerrainLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitTerrainLabel.setText("  Terrain:");

        unitMoveLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitMoveLabel.setText("Move:");

        unitHealthLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitHealthLabel.setText("Health:");

        unitRangeLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 11)); // NOI18N
        unitRangeLabel.setText("Range:");

        unitHealthValues.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitHealthValues.setText("x/y");

        unitMovePoints.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitMovePoints.setText("x/y");

        unitAttackValue.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitAttackValue.setText("x");

        unitRangeValue.setFont(new java.awt.Font("DejaVu Sans", 1, 11)); // NOI18N
        unitRangeValue.setText("x");

        unitDefenseValue.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitDefenseValue.setText("x");

        unitDefenseLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13)); // NOI18N
        unitDefenseLabel.setText("Defense:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(unitRangeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(unitAttackLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(unitMoveLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(unitHealthLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(unitDefenseLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(20, 20, 20))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(unitTerrainLabel)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(unitDefenseValue)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(terrainDefenseBonus))
                                    .addComponent(terrainPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(unitAttackValue)
                                            .addComponent(unitRangeValue))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(defensePortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(attackPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(unitHealthValues)
                            .addComponent(unitMovePoints))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(unitPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(unitName, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(unitPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unitName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unitHealthLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitMoveLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unitAttackLabel)
                            .addComponent(unitAttackValue))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitRangeLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(unitHealthValues)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(unitMovePoints)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(attackPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(defensePortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(unitRangeValue)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(unitDefenseValue)
                                    .addComponent(terrainDefenseBonus)
                                    .addComponent(unitDefenseLabel))
                                .addGap(1, 1, 1)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(terrainPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitTerrainLabel))
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel attackPortrait;
    private javax.swing.JLabel defensePortrait;
    private javax.swing.JLabel terrainDefenseBonus;
    private javax.swing.JLabel terrainPortrait;
    private javax.swing.JLabel unitAttackLabel;
    private javax.swing.JLabel unitAttackValue;
    private javax.swing.JLabel unitDefenseLabel;
    private javax.swing.JLabel unitDefenseValue;
    private javax.swing.JLabel unitHealthLabel;
    private javax.swing.JLabel unitHealthValues;
    private javax.swing.JLabel unitMoveLabel;
    private javax.swing.JLabel unitMovePoints;
    private javax.swing.JLabel unitName;
    private javax.swing.JLabel unitPortrait;
    private javax.swing.JLabel unitRangeLabel;
    private javax.swing.JLabel unitRangeValue;
    private javax.swing.JLabel unitTerrainLabel;
    // End of variables declaration//GEN-END:variables
    /** Creates new form UnitSelected */
    public UnitSelected() {
        initComponents();
    }
    public void setLocation(Location loc) {

        try
        {
            pierceImage = ImageIO.read(getClass().getResource(kPiercePath));
            hideImage = ImageIO.read(getClass().getResource(kHidePath));
        }catch(Exception e)
        {
            System.out.println(e);
        }

        unitPortrait.setIcon(new ImageIcon((BufferedImage)loc.myUnit.artPortrait));
        unitName.setText(loc.myUnit.name);
        unitHealthValues.setText(""+loc.myUnit.healthRemaining+"/"+loc.myUnit.healthTotal);
        unitMovePoints.setText(""+loc.myUnit.movePointsRemaining+"/"+loc.myUnit.movePointsTotal);
        unitAttackValue.setText(""+loc.myUnit.atkStrength);
        unitRangeValue.setText("" + loc.myUnit.atkRange);
        if(loc.myUnit.atkType == AttackType.pierce)
        {
            attackPortrait.setIcon(new ImageIcon(pierceImage));
        }
        else if(loc.myUnit.atkType == AttackType.bludgeon)
        {
            attackPortrait.setIcon(new ImageIcon(pierceImage)); //TODO: change to bludgeon art
        }
        else if(loc.myUnit.atkType == AttackType.maul)
        {
            attackPortrait.setIcon(new ImageIcon(pierceImage)); //TODO: change to maul art
        }
        unitDefenseValue.setText(""+loc.myUnit.defStrength);
        terrainDefenseBonus.setText("(+" + loc.myTerrain.defModifier + ")");
        if(loc.myUnit.defType == DefenseType.leather)
        {
            defensePortrait.setIcon(new ImageIcon(hideImage));
        }
        else if(loc.myUnit.defType == DefenseType.bone)
        {
            defensePortrait.setIcon(new ImageIcon(hideImage)); //TODO: change to bone art
        }
        else if(loc.myUnit.defType == DefenseType.padding)
        {
            defensePortrait.setIcon(new ImageIcon(hideImage)); //TODO: change to padded art
        }
        //terrain.setText("Terrain: "+loc.myTerrain.name);
        terrainPortrait.setIcon(new ImageIcon((BufferedImage)loc.myTerrain.artMap));
    }
}