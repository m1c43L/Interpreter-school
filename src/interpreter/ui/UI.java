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
import java.util.Arrays;
/**
 *
 * @author Michael
 */
public class UI {
    
    private BufferedReader inputReader;
    private CMD currentCommand;
    private String userInput;
    
    public UI(){
        inputReader = new BufferedReader(
                new InputStreamReader(System.in));
    }
    
    public String getNewUserInput(){
        try {
            System.out.print(">");
            userInput = inputReader.readLine();
        } catch (IOException ex) {}
        
        return userInput;
    }
    
    private boolean isValidCommand(String [] command){
        boolean isValid = Commands.contains(command[0]);
        
        try{
            if(!isValid) throw new InvalidCommandException(userInput);
        }catch(InvalidCommandException e){
            
        }finally{
            return isValid;
        }
    }
    
    
    
    private String [] tokenize(String input){
        return input.split("\\s+");
    }
    
    private String [] getValidCommand(){
        String [] command;
        do{
            command = tokenize(getNewUserInput());
        }while(!isValidCommand(command));
        
        
        return command;
    }
    
    public void setUpCommand(){
        String [] command = getValidCommand();
        currentCommand = Commands.get(command[0]);
        currentCommand.setParameters(command);
    }
   
    
    public void  executeCommandTo(DebuggerVirtualMachine dvm){
        currentCommand.execute(dvm);
    }
    
    public void suggestHelp(){
        System.out.println("Type ? for help");
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

class InvalidCommandException extends Exception{
    
    InvalidCommandException(String message){
        System.out.println("*****Invalid Command: " + message +"*******");
        System.out.println("Try '?' for help: ");
    }
}
