package comm.unibot.control;

import comm.unibot.serial.Communicator;
import comm.unibot.serial.SerialCommunicator;

/**
 * Provides control over the Unibot, interfacing with the serial port.
 * @author Miles
 */
public class UnibotController {
    
    Communicator comm;
    
    public UnibotController() {
        //for testing purposes, change this to SerialCommunicator when the 
        //serial port is plugged in
        comm = new SerialCommunicator();
    }
    
    /**
     * Moves the robot using external differential drive.
     * @param velocity The forward/reverse velocity in m/s.
     * @param turnVelocity The difference between left/right speed. Clipped to 
     * [-1.5,1.5]. <br> -1.5: Full turn to left. <br> 0.0: Straight movement.
     * <br> 1.5: Full turn to right.
     */
    public void moveDiffEx(double velocity, double turnVelocity) {
        //velocity = 1.07824642033967 * velocity + 0.0505902134326258; //(velocity + 0.096854) / 0.930264;
        velocity = 1.07 * velocity + 0.0469;
        //System.out.printf("New velocity: %.3f",velocity);
        turnVelocity += 0.0082;
        comm.sendCommand(String.format("#D %f %f", velocity, turnVelocity));
    }
    
    public void changeVelocity(double newVelocity) {
        comm.sendCommand(String.format("#D %f", newVelocity));
    }
    
    public void stop() {
        comm.sendCommand("#D 0.0");
    }
    
    public void emergencyStop() {
        comm.halt();
    }
    
    public boolean isResponding() {
        return comm.getAck();
    }
    
    /**
     * We need to close the serial port!
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        try {
            comm.close();
        } finally {
            super.finalize();
        }
    }
}
