/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comm.unibot.serial;

import comm.unibot.control.UnibotController;
import java.util.Scanner;

/**
 *
 * @author mb459
 */
public class ConsoleCommander {
    
    static Scanner scanner = new Scanner(System.in);
    static SerialCommunicator comm = new SerialCommunicator();
    
    public static void main(String[] args) {
        String command = "";
        while(!command.equalsIgnoreCase("quit")) {
            System.out.print(">");
            command = scanner.nextLine();
            comm.sendCommand(command);
            System.out.println(comm.getData());
        }
    }
    
    
}
