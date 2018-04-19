/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class DebugCallCode extends interpreter.bytecode.CallCode{
    
    
    public void execute(DebuggerVirtualMachine dvm){
        super.execute(dvm);
        dvm.pushFunction(new interpreter.debugger.FunctionEnvironmentRecord());
    }
}
