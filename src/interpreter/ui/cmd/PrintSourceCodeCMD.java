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

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        System.out.println(dvm.getMarkedSourceCode());
    }

    @Override
    public void setParameters(String[] inputs) {
    }

    @Override
    public String getStringDefinition() {
        return "source \t\t- (Print Source Code) Prints the entire source code.";
    }
    
}
