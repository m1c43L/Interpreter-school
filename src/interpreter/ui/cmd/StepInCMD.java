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

    @Override
    public void execute(DebuggerVirtualMachine dvm) {
        dvm.stepIn();
        if(dvm.getCurrentFuncName() == null){      
            System.out.println(dvm.getCurrentSourceFunc());
        }else if(dvm.getCurrentFuncName().equalsIgnoreCase("Read")){
            System.out.println("*************READ***************");
        }else if(dvm.getCurrentFuncName().equalsIgnoreCase("Write")){
            System.out.println("*************WRITE**************");
        }else
            System.out.println(dvm.getCurrentSourceFunc());
    }

    @Override
    public void setParameters(String[] inputs) {
        
    }

    @Override
    public String getStringDefinition() {
        return "'i' \t- (Step In) Steps into the current function.";
    }
    
}
