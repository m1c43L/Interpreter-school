/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Print output for user.
 * @author Michael
 */
public class WriteCode extends ByteCode {

    
    @Override
    public void execute(VirtualMachine VM) {
        System.out.println("OutPut : "+VM.peekRunStack());
    }

    @Override
    public void init(String[] args) {
        
    }
    
    @Override
    public boolean dumpCode() {
        System.out.println("WRITE");
        return false;
    }
    
}
