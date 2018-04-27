/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import interpreter.VirtualMachine;

/**
 * Reads user input
 * @author Michael
 */
public class ReadCode extends ByteCode{

    private int value;
    
    @Override
    public void execute(VirtualMachine VM) {
        
        try{
            System.out.print("Read:  ");
            BufferedReader in = 
                    new BufferedReader(
                            new InputStreamReader(System.in));
            String line = in.readLine();
            value = Integer.parseInt(line);
            VM.pushRunStack(value);
        }catch(java.io.IOException ex){ }
    }

    @Override
    public void init(String[] args) {
        
    }

    @Override
    public boolean dumpCode() {
        System.out.println("READ ");
        return true;
    }
    
    
    
}
