/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.Program;
import interpreter.VirtualMachine;

/**
 * Call function preceded by ARGS Bytecode
 * @author Michael
 */
public class CallCode extends ByteCode{
    
    
    private int address;
    protected String label;
    
    @Override
    public void init(String [] args){
        label = args[1];
    }

    @Override
    public void execute(VirtualMachine VM) {
        VM.pushPC();
        VM.jumpPC(address);
    }
    
    
    
    public void setAddress(Program program){
        address = program.getAddress(label);
    }

    @Override
    public boolean dumpCode() {
        System.out.println("CALL " + label);
        return true;
    }
    
    
}
