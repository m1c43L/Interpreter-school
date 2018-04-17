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
public class FormalCode extends ByteCode{

    private String id;
    private int value;
    
    @Override
    public void execute(VirtualMachine VM) {
        ((DebuggerVirtualMachine)VM).pushFormal(id, value);
    }

    @Override
    public void init(String[] args) {
        id = args[1];
        value = Integer.parseInt(args[2]);
    }

    @Override
    public boolean dumpCode() {
        return false;
    }
    
}
