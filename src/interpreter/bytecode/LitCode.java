/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 *
 * @author Michael
 */
public class LitCode extends ByteCode{

    private int value; 
    private String id = "";
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.pushRunStack(value);
        
    }

    @Override
    public void init(String[] args) {
        value = Integer.parseInt(args[1]);
        id = (args.length > 2)? args[2]: " ";
    }

    @Override
    public void dumpCode() {
        System.out.println("LIT " + value + " " + id 
                + "\t int " + id + " " + value);
    }
    
}
