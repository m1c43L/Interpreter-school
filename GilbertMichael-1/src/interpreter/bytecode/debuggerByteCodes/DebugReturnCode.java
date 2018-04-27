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
        DVM.popFunction();
        DVM.decremSpace();
        if(DVM.isTraceOn()){
            int indx = (super.returnComment.contains("<"))?
                super.returnComment.indexOf('<') : super.returnComment.length();
            String trace = DVM.getSpace() + "exit: " +
               super.returnComment.substring(0, indx) + ": "
               + VM.peekRunStack() ;
       
        System.out.println(trace);
        }
        
    }
}
