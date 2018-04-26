/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class DebugArgsCode extends interpreter.bytecode.ArgsCode{

    @Override
    public void execute(VirtualMachine VM) {
        super.execute(VM); 
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine) VM;
        DVM.setNumArgs(super.numArgs);
    }
    
}
