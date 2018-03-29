/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.Program;
import interpreter.VirtualMachine;

/**
 * Branch to label if top of stack is false
 * @author Michael
 */
public class FalseBranchCode extends ByteCode{

    private int address;
    private String label;
    
    @Override
    public void execute(VirtualMachine VM) {
        if(VM.popRunStack() == 0){
           // VM.pushPC();
            VM.jumpPC(address);
        }
    }

    @Override
    public void init(String[] args) {
        label = args[1];
    }
    
    
    public void setAddress(Program program){
        address = program.getAddress(label);
    }

    @Override
    public boolean dumpCode() {
        System.out.println("FALSEBRANCH " + label );
        return false;
    }
    
    
   
    
}
