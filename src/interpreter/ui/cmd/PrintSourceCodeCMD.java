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
public class PrintSourceCodeCMD extends CMD{
    private StringBuilder output;

    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        output = new StringBuilder(dvm.getSourceCode());
    }

    @Override
    public void setParameters(String[] inputs) {
    }

    @Override
    public String definition() {
        return "'s' \t- (Print Source Code) Prints the entire source code.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
}
