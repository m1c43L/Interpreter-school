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
public class StepOverCMD extends CMD{

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        dvm.executeCurrentLine();
        System.out.println(dvm.getCurrentSourceFunc());
    }

    @Override
    public void setParameters(String[] inputs) {
        
    }

    @Override
    public String getStringDefinition() {
        return "so \t- (Step Over) Steps over a line.";
    }
    
}
