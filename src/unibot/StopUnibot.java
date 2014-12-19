/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unibot;

import comm.unibot.control.UnibotController;

public class StopUnibot {
    protected static UnibotController robot = new UnibotController();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!robot.isResponding()) {
            System.out.println("Unirobot is not responding.");
        }
        robot.stop();
    }
}
