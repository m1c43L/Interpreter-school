/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui;

import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.ui.cmd.CMD;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Michael
 */
public class UI {
    
    private BufferedReader inputReader;
    private CMD currentCommand;
    
    public UI(){
        inputReader = new BufferedReader(
                new InputStreamReader(System.in));
    }
    
    public String getUserInput(){
        String userInput = "";
        try {
            userInput = inputReader.readLine();
        } catch (IOException ex) {}
        
        return userInput;
    }
    
    private String [] tokenize(String input){
        return input.split("\\s+");
    }
    
    public void setUpCommand(){
        String [] command = tokenize(getUserInput());
        currentCommand = Commands.get(command[0]);
        currentCommand.setParameters(command);
    }
   
    
    public void  executeCommandTo(DebuggerVirtualMachine dvm){
        currentCommand.execute(dvm);
    }
    
    
    public static void main(String args[]){
        UI test = new UI();
        DebuggerVirtualMachine dvm = null;
        while(true){
            test.setUpCommand();
            test.executeCommandTo(dvm);
        }
    }
}
