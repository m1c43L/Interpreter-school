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
public class DebugReturnCode extends interpreter.bytecode.ReturnCode{
    
    @Override
    public void execute(interpreter.VirtualMachine VM){
        super.execute(VM);
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine)VM;
        
        
        if(DVM.isTraceOn()){
            String function = DVM.getCurrentFuncName();

            String trace = DVM.getSpace() + "exit: " + function + ": "+ VM.peekRunStack() ;   
        System.out.println(trace);
        }
        DVM.decremSpace();
        DVM.popFunction();
        
    }
}
