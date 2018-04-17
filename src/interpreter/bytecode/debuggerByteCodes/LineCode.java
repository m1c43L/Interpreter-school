/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class LineCode extends ByteCode {
    private int lineNumber;
    
    @Override
    public void execute(VirtualMachine VM) {
        ((DebuggerVirtualMachine) VM).setCurrentLine(lineNumber);
    }

    @Override
    public void init(String[] args) {
        lineNumber = Integer.parseInt(args[1]);
    }

    @Override
    public boolean dumpCode() {
        return false;
    }
    
    public int getLineNumber(){
        return lineNumber;
    }
    
}
