/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

/**
 *
 * @author Michael
 */
public class DebugLitCode extends interpreter.bytecode.LitCode{
   
    
    @Override
    public void execute(interpreter.VirtualMachine VM){
        super.execute(VM);
        if(id != null)
        ((interpreter.debugger.DebuggerVirtualMachine)VM).pushFormal(id, 
               ((interpreter.debugger.DebuggerVirtualMachine)VM).getCurrentOffset());
        
    }
}
