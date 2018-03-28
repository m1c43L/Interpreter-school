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
public class PopCode extends ByteCode{

    private int numPop;
    
    @Override
    public void execute(VirtualMachine VM) {
        for(int i = 0; i < numPop; i++){
            VM.popRunStack();
        }
    }

    @Override
    public void init(String[] args) {
        numPop = Integer.parseInt(args[1]);
    }
    
}
