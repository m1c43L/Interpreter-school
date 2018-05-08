/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.ui.cmd;

import interpreter.debugger.DebuggerVirtualMachine;

/**
 *
 * @author Michael
 */
public class PrintCallStackCMD extends CMD{
    
    private StringBuilder output;
    
    
    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
       output = new StringBuilder(dvm.getCallStack());
    }

    @Override
    public void setParameters(String[] inputs) {
        
    }

    @Override
    public String definition() {
         return "'p' \t- (Print Call Stack) No parameter required.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
    
}
