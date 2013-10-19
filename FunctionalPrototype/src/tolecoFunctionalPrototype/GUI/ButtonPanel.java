/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tolecoFunctionalPrototype.GUI;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;

/**
 * Class representing the button panel in the GUI
 * @author Adam
 */
public class ButtonPanel extends JPanel implements ActionListener 
{
    private GUI_Controller controller;
    private JButton actionBtn;
    private JButton endTurnBtn;
    private JButton saveBtn;
    private JButton quitBtn;

    public ButtonPanel(GUI_Controller controller)
    {
        this.controller = controller;

        initializeButtons();
    }

    public ButtonPanel()
    {
        initializeButtons();
    }

    public void setController(GUI_Controller controller)
    {
        this.controller = controller;
    }

    /**
     * Method to disable the action button
     */
    public void gotoStateGrayed()
    {
        actionBtn.setEnabled(false);
    }

    /**
     * Method to set the action button to ok state
     */
    public void gotoStateOk()
    {
        actionBtn.setText("Ok");
        actionBtn.setActionCommand("ok");
        actionBtn.setMnemonic(KeyEvent.VK_O);
        actionBtn.setEnabled(true);
    }

    /**
     * Method to set the action button to attack state
     */
    public void gotoStateAttack()
    {
        actionBtn.setText("Attack");
        actionBtn.setActionCommand("attack");
        actionBtn.setMnemonic(KeyEvent.VK_A);
        actionBtn.setEnabled(true);
    }

    /**
     * Method to set the action button to cancel state
     */
    public void gotoStateCancel()
    {
        actionBtn.setText("Cancel");
        actionBtn.setActionCommand("cancel");
        actionBtn.setMnemonic(KeyEvent.VK_C);
        actionBtn.setEnabled(true);
    }

    /**
     * Method to change the background color of the ButtonPanel
     * @param playerNum int representing the player whose turn it is
     */
    public void changePlayerColor(int playerNum)
    {
        if(playerNum == 1)
        {
            setBackground(Color.MAGENTA);
        }
        else
        {
            setBackground(Color.GREEN);
        }
    }

    /**
     * Method for handling button click events
     * @param event ActionEvent representing the button click
     */
    public void actionPerformed(ActionEvent event)
    {
        if("ok".equals(event.getActionCommand()))
        {
            controller.okClicked();
        }
        if("attack".equals(event.getActionCommand()))
        {
            controller.attackClicked();
        }
        if("cancel".equals(event.getActionCommand()))
        {
            controller.cancelClicked();
        }
        if("endturn".equals(event.getActionCommand()))
        {
            int chosen = JOptionPane.showConfirmDialog(this, "Are you sure you want to end your turn?", "End Turn", JOptionPane.YES_NO_OPTION);
            if(chosen == JOptionPane.YES_OPTION)
            {
                controller.endTurnPressed();
            }
        }
    }

    /**
     * Private method to initialize the buttons and panel
     */
    public void initializeButtons()
    {
        actionBtn = new JButton("Attack");
        actionBtn.setActionCommand("attack");
        actionBtn.setMnemonic(KeyEvent.VK_A);
        actionBtn.addActionListener(this);
        actionBtn.setEnabled(false);

        endTurnBtn = new JButton("End Turn");
        endTurnBtn.setActionCommand("endturn");
        endTurnBtn.setMnemonic(KeyEvent.VK_E);
        endTurnBtn.addActionListener(this);
        
        saveBtn = new JButton("Save");
        saveBtn.addActionListener(this);
        saveBtn.setMnemonic(KeyEvent.VK_S);
        saveBtn.setEnabled(false);

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(this);
        quitBtn.setMnemonic(KeyEvent.VK_Q);
        quitBtn.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(endTurnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                    .addComponent(actionBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(actionBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(endTurnBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
    }
}
