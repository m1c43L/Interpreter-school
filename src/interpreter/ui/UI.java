/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Michael
 */
public class UI {
    
    BufferedReader inputReader;
    
    public UI(){
        inputReader = new BufferedReader(new InputStreamReader(System.in));
    }
    
    
    public void run(){

        while(true){
            try {
                System.out.println("Type '?' for help: ");
                String [] tokenizedInput = tokenizeString(inputReader.readLine());
                fetchAndExecute(tokenizedInput);         
            } catch (IOException ex) {
                System.out.println("Incorrect Input");
            }
        }     
    }
    
    private String [] tokenizeString(String input){
        return input.split("\\s+");
    }
    
    private void fetchAndExecute(String [] commandWithParam){
        Commands.get(commandWithParam[0]).execute(commandWithParam);
    }
    
    public static void main(String args[]){
        UI test = new UI();
        
        test.run();
    }
}
