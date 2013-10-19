/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cero;

/**
 * Fake Options class for use in JUnit tests.
 * @author ilya
 */
public class FakeOptions implements I_Options{

    public ComputerLevel getComputerLevel(Players player) {
        return ComputerLevel.easy;
    }

    public String getName(Players player) {
        return "I like cheese";
    }

    public boolean multipleDrawsAllowed() {
        return true;
    }

    public void setComputerLevel(Players computer, ComputerLevel level) {        
    }

    public void setMultipleDraws(boolean allow) {        
    }

    public void setName(Players player, String name) {        
    }

}
