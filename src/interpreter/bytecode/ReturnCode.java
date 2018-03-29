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
public class ReturnCode extends ByteCode{

    String returnComment;
    
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.returnPC();
        VM.popFrameRunStack();
    }

    @Override
    public void init(String[] args) {
        returnComment = (args.length > 1)? args[1] : "";
    }

    @Override
    public void dumpCode() {
        System.out.println("RETURN " + returnComment);
    }
    
}
