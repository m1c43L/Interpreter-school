/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;

/**
 * Return to last saved PC
 * Pop the top pointerFrame
 * @author Michael
 */
public class ReturnCode extends ByteCode{

    protected String returnComment;
    protected int returnValue;
    
    @Override
    public void execute(VirtualMachine VM) {
        VM.returnPC();
        VM.popFrameRunStack();
        returnValue = VM.peekRunStack();
    }

    @Override
    public void init(String[] args) {
        returnComment = (args.length > 1)? args[1] : "";
    }

    @Override
    public boolean dumpCode() {
        System.out.println("RETURN " + returnComment + " " + returnValue);
        return true;
    }
    
}
