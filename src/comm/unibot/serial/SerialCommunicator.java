package comm.unibot.serial;
import java.io.UnsupportedEncodingException;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Miles
 */
public final class SerialCommunicator extends Communicator {
    
    private SerialPort serialPort;


    public SerialCommunicator() {
        try {
            initialise();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     *
     * @throws SerialPortException
     */
    @Override
    protected void initialise() throws SerialPortException {
        serialPort = new SerialPort("COM4");
        
        try {
            serialPort.openPort();

            // set params as per unibot protocol
            serialPort.setParams(
                    SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8, 
                    SerialPort.STOPBITS_1, 
                    SerialPort.PARITY_NONE
            );
            
            // make sure we're ready for commands
            setPauseState(false);
        
        } catch(SerialPortException ex) {
            System.out.println(ex);
        }
        
        
    }
    
    @Override
    public void sendCommand(String command) {
        if(serialPort == null)
            throw new IllegalStateException("Serial port not open/initialised");
        try {
            command += "\r";
            serialPort.writeBytes(command.getBytes());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
    
    @Override
    public String getData() {
        try {
            if(serialPort == null)
                throw new IllegalStateException("Serial port not open/initialised");
            byte[] buffer = serialPort.readBytes();
            if(buffer == null)
                return "";
            return new String(buffer,"UTF-8");
        } catch (SerialPortException | UnsupportedEncodingException ex) {
            System.out.println(ex);
        }
        return "";
    }
    
    @Override
    public boolean getAck() {
        sendCommand("#?");
        return getData().equals("#K");
    }
    
    @Override
    public void halt() {
        sendCommand("#X");
    }
    
    @Override
    public void setPauseState(boolean paused) {
        sendCommand("#P" + (paused ? "1" : "0"));
    }
    
    
    @Override
    public void close() {
        if(serialPort == null)
            throw new IllegalStateException("Serial port not open/initialised");
        try {
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

}
