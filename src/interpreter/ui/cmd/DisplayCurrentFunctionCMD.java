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
public class DisplayCurrentFunctionCMD extends CMD{
    private StringBuilder output;

    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        output = new StringBuilder(dvm.getCurrentSourceFunc());
    
    }

    @Override
    public void setParameters(String[] inputs) {

    }

    @Override
    public String definition() {
        return "'f' \t- (Display Current Function) Displays the current function.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }

    
}
