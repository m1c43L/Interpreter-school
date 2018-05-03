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
public class ContinueExecutionCMD extends CMD{
    private StringBuilder output;

    @Override
    public void executeTo(DebuggerVirtualMachine dvm) {
        dvm.executeProgram();
        output = new StringBuilder();
        output.append(dvm.getCurrentSourceFunc());
    }

    @Override
    public void setParameters(String[] inputs) {
    }

    @Override
    public String definition() {
        return "'c' \t- (Continue Execution) Continue execution until the next break point.";
    }

    @Override
    public StringBuilder output() {
        return output;
    }
    
    


    
}
