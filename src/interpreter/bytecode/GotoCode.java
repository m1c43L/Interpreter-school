/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.Program;
import interpreter.VirtualMachine;

/**
 *
 * @author Michael
 */
public class GotoCode extends ByteCode{

    private int address;
    private String label;
    
    @Override
    public void execute(VirtualMachine VM) {
       VM.jumpPC(address);
    }

    @Override
    public void init(String[] args) {
       label = args[1];
    }
    
    
    public void setAddress(Program program){
        address = program.getAddress(label);
    }
    
}
