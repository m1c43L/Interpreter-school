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
public class ArgsCode extends ByteCode {

    private int numArgs;
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.newFrameAtRunStack(numArgs);
    }

    @Override
    public void init(String[] args) {
        numArgs = Integer.parseInt(args[1]);
    }
    
}
