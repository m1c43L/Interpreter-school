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

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        dvm.executeProgram();
        System.out.println();
        System.out.println(dvm.getCurrentStringFunc());
    }

    @Override
    public void setParameters(String[] inputs) {
    }

    @Override
    public String getStringDefinition() {
        return "continue \t- (Continue Execution) Continue execution until the next break point.";
    }


    
}
