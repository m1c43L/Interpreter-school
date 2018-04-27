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
public class DebugCallCode extends interpreter.bytecode.CallCode{
    
    
    @Override
    public void execute(VirtualMachine VM){
        super.execute(VM);
        DebuggerVirtualMachine DVM = (DebuggerVirtualMachine)VM;
        DVM.pushFunction(new interpreter.debugger.FunctionEnvironmentRecord());
        
        if(DVM.isTraceOn()){
        int indx = (super.label.contains("<"))?
                super.label.indexOf('<') : super.label.length();
        
        String trace = DVM.getSpace() +
               super.label.substring(0, indx) + "("
               + VM.peekRunStack() + ")";
       
        System.out.println(trace);
        }
      DVM.incremSpace();
        
    }
}
