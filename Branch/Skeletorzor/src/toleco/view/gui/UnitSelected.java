package toleco.view.gui;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import toleco.terrain.Terrain;
import toleco.unit.Unit;

/**
 * Displays information about the currently selected unit.
 * Displayed information is:
 *  unit image
 *  unit name
 *  team name
 *  current health
 *  max health
 *  current move points
 *  max move points
 *  attack value
 *  attack range
 *  attack type
 *  defense value
 *  terrain defense bonus
 *  defense type
 *  current terrain
 *
 * @author Matt Tognetti
 * @version 1.0
 */
public class UnitSelected extends javax.swing.JPanel
{

    /** 
     * Creates new form UnitSelected.
     */
    public UnitSelected()
    {
        initComponents();
    }

    /**
     * Displays information about currently selected unit, including all unit statistics
     * and information about the terrain the unit is standing on.
     *
     * @pre terrain must have a unit on it
     * @param terrain the currently selected terrain
     * @param attackImage image of the unit's attack type
     * @param defenseImage image of the unit's defense type
     * @param terrainImage image of the selected terrain
     * @param unitImage image of the current unit
     */
    public void updateInfo(Terrain terrain, BufferedImage terrainImage,
            BufferedImage unitImage, BufferedImage attackImage,
            BufferedImage defenseImage)
    {

        //Width of the scaled image
        final int kWidth = 80;
        //Height of the scaled image
        final int kHeight = 80;
        //sign of the defense modifier
        String modSign = "";

        //GET unit from terrain
        Unit unit = terrain.getUnit();

        //Scale the image to fit the portrait
        BufferedImage scaledUnit = new BufferedImage(kWidth, kHeight,
                BufferedImage.TYPE_INT_RGB);
        //INIT Graphics2D by calling createGraphics on scaledImage
        Graphics2D graphics2D = scaledUnit.createGraphics();
        //CALL setRenderingHint with hints KEY_INTERPOLATION and
        //VALUE_INTERPOLATION_BILINEAR
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        //draw the iamge
        graphics2D.drawImage(unitImage, 0, 0, kWidth, kHeight, null);

        //SET the unit image
        unitPortrait.setIcon(new ImageIcon(scaledUnit));
        //SET unit name
        unitName.setText(unit.getType());
        //SET health display
        unitHealthValues.setText(""+unit.getCurrentHealth()+"/"+unit.getMaxHealth());
        //SET move points display
        unitMovePoints.setText(""+unit.getCurrentMoves()+"/"+unit.getMaxMoves());
        //SET attack value
        unitAttackValue.setText(""+unit.getAttackValue());
        //SET attack range
        unitRangeValue.setText("" + unit.getAttackRange());
        //SET attack type
        attackPortrait.setIcon(new ImageIcon(attackImage));
        //SET armor value
        unitDefenseValue.setText(""+unit.getArmorValue());
        //IF defense modifier is greater than 0
        if ( terrain.getDefMod() > 0 )
        {
            //SET modifier sign to +
            modSign = "+";
        }
        //END IF
        //SET terrain defense bonus
        terrainDefenseBonus.setText("(" + modSign + terrain.getDefMod() + ")");
        //SET defense type icon
        defensePortrait.setIcon(new ImageIcon(defenseImage));
        //SET terrain image
        terrainPortrait.setIcon(new ImageIcon(terrainImage));
    }

    //CHECKSTYLE:OFF - Ignore generated code.
    // Authorized by Dr. Dalbey.

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        unitHealthLabel = new javax.swing.JLabel();
        unitMoveLabel = new javax.swing.JLabel();
        terrainPortrait = new javax.swing.JLabel();
        defensePortrait = new javax.swing.JLabel();
        unitDefenseLabel = new javax.swing.JLabel();
        unitTerrainLabel = new javax.swing.JLabel();
        unitDefenseValue = new javax.swing.JLabel();
        unitAttackLabel = new javax.swing.JLabel();
        attackPortrait = new javax.swing.JLabel();
        unitName = new javax.swing.JLabel();
        terrainDefenseBonus = new javax.swing.JLabel();
        unitPortrait = new javax.swing.JLabel();
        unitHealthValues = new javax.swing.JLabel();
        unitMovePoints = new javax.swing.JLabel();
        unitAttackValue = new javax.swing.JLabel();
        unitRangeValue = new javax.swing.JLabel();
        unitRangeLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(200, 300));

        unitHealthLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitHealthLabel.setText("Health:");

        unitMoveLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitMoveLabel.setText("Move:");

        terrainPortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        terrainPortrait.setPreferredSize(new java.awt.Dimension(40, 40));

        defensePortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        defensePortrait.setPreferredSize(new java.awt.Dimension(16, 16));

        unitDefenseLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitDefenseLabel.setText("Defense:");

        unitTerrainLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitTerrainLabel.setText("  Terrain:");

        unitDefenseValue.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitDefenseValue.setText("x");

        unitAttackLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitAttackLabel.setText("Attack:");

        attackPortrait.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        attackPortrait.setPreferredSize(new java.awt.Dimension(16, 16));

        unitName.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
        unitName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        unitName.setText("Name");
        unitName.setPreferredSize(new java.awt.Dimension(44, 20));

        terrainDefenseBonus.setFont(new java.awt.Font("DejaVu Sans", 1, 11)); // NOI18N
        terrainDefenseBonus.setText("(+y)");

        unitPortrait.setBackground(new java.awt.Color(254, 254, 254));
        unitPortrait.setBorder(null);
        unitPortrait.setMaximumSize(new java.awt.Dimension(64, 64));
        unitPortrait.setPreferredSize(new java.awt.Dimension(80, 80));

        unitHealthValues.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitHealthValues.setText("x/y");

        unitMovePoints.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitMovePoints.setText("x/y");

        unitAttackValue.setFont(new java.awt.Font("DejaVu Sans", 1, 13));
        unitAttackValue.setText("x");

        unitRangeValue.setFont(new java.awt.Font("DejaVu Sans", 1, 11));
        unitRangeValue.setText("x");

        unitRangeLabel.setFont(new java.awt.Font("DejaVu Sans", 1, 11));
        unitRangeLabel.setText("Range:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addContainerGap(22, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(unitName, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addContainerGap()))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(unitPortrait, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
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

    //CHECKSTYLE:ON
}
