package unibot;

import comm.unibot.control.UnibotController;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miles
 */
public class main {
    
    // How long to send the robot forward
    final static int trial_length = 10000;

    static UnibotController robot = new UnibotController();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if(!robot.isResponding())
            System.out.println("\nRobot is not responding! maybe pause state not set...");
        else {
            System.out.println("\nRobot is responding, we're good to go.");
        }
        System.out.println("\nAttempting to move robot forward with speed 0.2...\n");
        
        robot.moveDiffEx(0.1, 0.0082);
        
        System.out.println("Waiting...\n");
        
        try {
            Thread.sleep(trial_length);
            robot.stop();
            //Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("\nAttempting to move robot backward with speed 0.2...\n");
        
        //robot.moveDiffEx(-0.5, 0.0082);
        
        System.out.println("Waiting...\n");
        
        try {
            Thread.sleep(trial_length);
        } catch (InterruptedException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Stopping robot...\n");
        robot.stop();        
    }
    
}
