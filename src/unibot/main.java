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
    final static int trial_length = 20000;
    final static double vL_R = 0;
    final static double forward_velocity = 0.1;
    

    static UnibotController robot = new UnibotController();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        if(!robot.isResponding())
            System.out.println("\nRobot is not responding! maybe pause state not set...");
        else {
            System.out.println("\nRobot is responding, we're good to go.");
        }
        System.out.println("\nAttempting to move robot forward with speed 0.2...\n");
        
        robot.moveDiffEx(forward_velocity,vL_R);
                
        //System.out.println("\nAttempting to move robot backward with speed 0.2...\n");
        
        //robot.moveDiffEx(-0.4, 0);
        
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
