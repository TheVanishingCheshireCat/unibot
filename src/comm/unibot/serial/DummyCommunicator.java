package comm.unibot.serial;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPortException;

/**
 * Simulates the Unibot for offline programming and testing.
 * @author Miles
 */
public final class DummyCommunicator extends Communicator{

    public DummyCommunicator() {
        try {
            initialise();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private String dataStr = "";
    
    @Override
    protected void initialise() throws SerialPortException {
        setPauseState(false);
    }
    
    @Override
    public void close() {
    }

    @Override
    public boolean getAck() {
        sendCommand("#?");
        return dataStr.equals("#K");
    }
    

    @Override
    public void sendCommand(String command) {
        if(command.equals("#?"))
            dataStr = "#K";
        if(pauseState)
            throw new IllegalStateException("Unibot is paused! Need to call "
                    + "setPauseState(false) before sending commands");
    }

    @Override
    public String getData() {
        String data = dataStr;
        dataStr = "";
        return data;
    }

    @Override
    public void halt() {
        
    }
    
    private boolean pauseState = true;

    @Override
    public void setPauseState(boolean paused) {
        pauseState = paused;
    }

    
}
