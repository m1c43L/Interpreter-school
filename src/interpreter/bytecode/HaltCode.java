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
public class HaltCode extends ByteCode{

    @Override
    public void execute(VirtualMachine VM) {
        VM.isRunningToFalse();
    }

    @Override
    public void init(String[] args) {
        
    }
    
}
