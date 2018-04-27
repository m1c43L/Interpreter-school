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
public class DebugReadCode extends interpreter.bytecode.ReadCode{
    
    
    @Override
    public void execute(VirtualMachine VM){
        super.execute(VM);
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine)VM;
        
    }
}
