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
public class DisplayVariablesCMD extends CMD{

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        dvm.displayVariables();
    }

    @Override
    public void setParameters(String[] inputs) {
    }

    @Override
    public String getStringDefinition() {
        return "disp-v \t\t\t- (Display Variable)";
    }

    
    
}
