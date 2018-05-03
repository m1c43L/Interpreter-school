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
public class StepInCMD extends CMD{
    private StringBuilder output;
    
    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        dvm.stepIn();
        output = new StringBuilder(dvm.getCurrentSourceFunc());
    }

    @Override
    public void setParameters(String[] inputs) {
        
    }

    @Override
    public String definition() {
        return "'i' \t- (Step In) Steps into the current function.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
}
