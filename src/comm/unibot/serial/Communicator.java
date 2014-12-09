/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.unibot.serial;

import jssc.SerialPortException;

/**
 *
 * @author Miles
 */
public abstract class Communicator {
    
    /**
     * Sets up and opens the serial port on COM4, using the default settings for 
     * Unibot (BAUDRATE_57600,DATABITS_8,STOPBITS_1,PARITY_NONE)
     * @throws SerialPortException if there is a problem with opening the serial
     * port.
     */
    protected abstract void initialise() throws SerialPortException;
    
    /**
     * Pings the robot and waits for a reply.
     * @return true if the robot acknowledges with "#K", false if it replies with
     * anything else.
     */
    public abstract boolean getAck();
    
    /**
     * Sends the command given, appending with a carriage return character \r.
     * @param command Command to send to the robot. Must start with #
     */
    public abstract void sendCommand(String command);

    /**
     * Returns data received from the robot.
     * @return data in String format
     */
    public abstract String getData();
    
    /**
     * Closes the connection with the serial port.
     */
    public abstract void close();
    
    /**
     * Sends an emergency stop signal to the robot.
     */
    public abstract void halt();

    /**
     * Sets 
     * @param paused 
     */
    public abstract void setPauseState(boolean paused);

    
}
