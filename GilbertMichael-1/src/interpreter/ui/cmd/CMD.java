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
public abstract class CMD {
    
    
    public abstract void execute(DebuggerVirtualMachine dvm);
    
    /**
     * set all
     * 0 position contains the actual command.
     * eg. [0] = setBreakpoint [1] = 1
     *     command             parameter
     * @param inputs 
     */
    public abstract void setParameters(String [] inputs);
    
    public abstract String getStringDefinition();
    
}
